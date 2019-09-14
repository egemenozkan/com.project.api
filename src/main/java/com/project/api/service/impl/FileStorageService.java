package com.project.api.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
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
	private static final Logger LOG = LogManager.getLogger(FileStorageService.class);

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getLocation()).toAbsolutePath().normalize();
		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	@Override
	public String storeFile(MultipartFile file, long userId, int pageType, long pageId) {
		LocalDateTime creationDateTime = LocalDateTime.now();
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			String extension = FilenameUtils.getExtension(fileName);
			String fileDirectory = createFileDirectory(fileName, userId, pageType, pageId, false, true);
			// Files.copy(file.getInputStream(), storagePath,
			// StandardCopyOption.REPLACE_EXISTING);
			// Copy file to the target location (Replacing existing file with the same name)
			String mimetype = Files.probeContentType(
					getStoragePath(userId, pageType, pageId, creationDateTime, fileDirectory, fileName, null));
			if (mimetype != null && mimetype.split("/")[0].equals("image")) {
				saveImage(file, userId, pageType, pageId, creationDateTime, fileDirectory, extension, "xs");
				saveImage(file, userId, pageType, pageId, creationDateTime, fileDirectory, extension, "sm");
				saveImage(file, userId, pageType, pageId, creationDateTime, fileDirectory, extension, "md");
				saveImage(file, userId, pageType, pageId, creationDateTime, fileDirectory, extension, "lg");
			} else {
				Files.copy(file.getInputStream(),
						getStoragePath(userId, pageType, pageId, creationDateTime, fileDirectory, fileName, null),
						StandardCopyOption.REPLACE_EXISTING);
			}

			fileService.saveFile(userId, pageType, pageId,  createFileDirectory(fileName, userId, pageType, pageId, true, false),
					createFileName(creationDateTime, userId, pageType, pageId, null, null), extension);

		} catch (Exception e) {
			LOG.error("::storeFile {}", e);
		}

		return createFileName(creationDateTime, userId, pageType, pageId, null, null);
	}

	private void saveImage(MultipartFile file, long userId, int pageType, long pageId, LocalDateTime creationDateTime,
			String fileDirectory, String extension, String size)
			throws IllegalArgumentException, ImagingOpException, IOException {
		if ("xs".equals(size)) {
			BufferedImage xs = Scalr.resize(ImageIO.read(file.getInputStream()), Method.ULTRA_QUALITY, Mode.AUTOMATIC,
					150, 150, Scalr.OP_ANTIALIAS);
			ImageIO.write(xs, "jpg",
					new File(getStoragePath(userId, pageType, pageId, creationDateTime, fileDirectory, extension, size)
							.toString()));
		} else if ("sm".equals(size)) {
			BufferedImage sm = Scalr.resize(ImageIO.read(file.getInputStream()), Method.ULTRA_QUALITY, Mode.AUTOMATIC,
					640, 640, Scalr.OP_ANTIALIAS);
			ImageIO.write(sm, "jpg",
					new File(getStoragePath(userId, pageType, pageId, creationDateTime, fileDirectory, extension, size)
							.toString()));
		} else if ("md".equals(size)) {
			BufferedImage md = Scalr.resize(ImageIO.read(file.getInputStream()), Method.ULTRA_QUALITY, Mode.AUTOMATIC,
					900, 900, Scalr.OP_ANTIALIAS);
			ImageIO.write(md, "jpg",
					new File(getStoragePath(userId, pageType, pageId, creationDateTime, fileDirectory, extension, size)
							.toString()));
		} else if ("lg".equals(size)) {
			BufferedImage md = Scalr.resize(ImageIO.read(file.getInputStream()), Method.ULTRA_QUALITY, Mode.AUTOMATIC,
					1024, 1024, Scalr.OP_ANTIALIAS);
			ImageIO.write(md, "jpg",
					new File(getStoragePath(userId, pageType, pageId, creationDateTime, fileDirectory, extension, size)
							.toString()));
		}

	}

	private Path getStoragePath(long userId, int pageType, long pageId, LocalDateTime creationDateTime,
			String fileDirectory, String extension, String size) {
		return Paths.get(fileDirectory, createFileName(creationDateTime, userId, pageType, pageId, extension, size));
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

	private String createFileDirectory(String fileName, long userId, int pageType, long pageId,
			boolean excludeDirectory, boolean createDirectory) {

//		try {

		StringBuilder subfolderStrBuilder = new StringBuilder();
		if (pageType == 1) {
			subfolderStrBuilder.append("/places/").append(pageId).append("/");
		} else if (pageType == 2) {
			subfolderStrBuilder.append("/events/").append(pageId).append("/");
		}

		StringBuilder uploadDirBuilder = new StringBuilder();
		if (!excludeDirectory) {
			uploadDirBuilder.append(this.fileStorageLocation.toString());
		}
		uploadDirBuilder.append(subfolderStrBuilder);

		Path uploadDir = Paths.get(uploadDirBuilder.toString());
		if (createDirectory) {
			try {
				Files.createDirectories(uploadDir);
			} catch (Exception ex) {
				throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
						ex);
			}
		}
		// Check if the file's name contains invalid characters
		if (fileName.contains("..")) {
			throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
		}

		return uploadDir.toString();

//		} catch (IOException ex) {
//			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
//		}
	}

	private String createFileName(LocalDateTime creationDateTime, long userId, int pageType, long pageId,
			String extension, String size) {
		StringBuilder newFileNameBuilder = new StringBuilder(String.valueOf(userId)).append("_")
				.append(String.valueOf(pageType)).append("_").append(String.valueOf(pageId)).append("_")
				.append(creationDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
		if (size != null) {
			newFileNameBuilder.append("_").append(size);
		}
		if (extension != null) {
			newFileNameBuilder.append('.').append(extension);
		}

		return newFileNameBuilder.toString();
	}

}