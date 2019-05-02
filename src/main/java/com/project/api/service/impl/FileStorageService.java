package com.project.api.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.project.api.data.service.IFileService;
import com.project.api.exception.FileStorageException;
import com.project.api.exception.MyFileNotFoundException;
import com.project.api.property.FileStorageProperties;
import com.project.api.service.IFileStorageService;

/*
 * Inspiration :
 * https://www.callicoder.com/spring-boot-file-upload-download-rest-api-example/
 * 
 */

@Service
public class FileStorageService implements IFileStorageService {

	private final Path fileStorageLocation;
//	@Value("${storage.location}")
//	private String storageLocation;
	@Autowired
	IFileService fileService;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getLocation()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

	@Override
	public String storeFile(MultipartFile file, long userId, int pageType, long pageId) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {

			StringBuilder subfolderStrBuilder = new StringBuilder();
			if (pageType == 1) {
				subfolderStrBuilder.append("/places/").append(pageId).append("/");
			} else if (pageType == 2) {
				subfolderStrBuilder.append("/events/").append(pageId).append("/");
			}

			StringBuilder uploadDirBuilder = new StringBuilder(this.fileStorageLocation.toString()).append(subfolderStrBuilder);

			StringBuilder newFileNameBuilder = new StringBuilder(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
			newFileNameBuilder.append(".").append(FilenameUtils.getExtension(fileName));

			Path uploadDir = Paths.get(uploadDirBuilder.toString());
			try {
				Files.createDirectories(uploadDir);
			} catch (Exception ex) {
				throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
			}
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			String strTargetLocation = uploadDirBuilder.append(newFileNameBuilder).toString();
			// Copy file to the target location (Replacing existing file with the same name)
			Path targetLocation = uploadDir.resolve(strTargetLocation);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			fileService.saveFile(userId, pageType, pageId, subfolderStrBuilder.append(newFileNameBuilder).toString());
			return strTargetLocation;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	@Override
	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new MyFileNotFoundException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new MyFileNotFoundException("File not found " + fileName, ex);
		}
	}

}