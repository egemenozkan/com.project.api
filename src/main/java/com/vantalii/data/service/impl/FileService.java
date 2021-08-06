package com.vantalii.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.data.model.file.LandingPageFile;
import com.project.api.data.model.file.MyFile;
import com.vantalii.api.data.mapper.FileMapper;
import com.vantalii.data.service.IFileService;

@Service
public class FileService implements IFileService {
	
	@Autowired
	FileMapper fileMapper;
	
	@Override
	public List<MyFile> getFilesByPageId(int pageType, long pageId) {
		return fileMapper.findAllFilesByPageId(pageType, pageId);
	}

	@Override
	public List<LandingPageFile> getFiles() {
		return fileMapper.findAllFiles();
	}

	@Override
	public long saveFile(long userId, int pageType, long pageId, String uploadDir, String name, String extension) {
		return fileMapper.saveFile(pageType, pageId, userId, uploadDir, name, extension);
	}

}
