package com.project.api.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.api.data.mapper.FileMapper;
import com.project.api.data.model.file.LandingPageFile;
import com.project.api.data.model.file.MyFile;
import com.project.api.data.service.IFileService;

@Service
public class FileService implements IFileService {
	
	@Autowired
	FileMapper fileMapper;
	
	@Override
	public long saveFile(long userId, int pageType, long pageId, String path) {
		return fileMapper.saveFile(pageType, pageId, userId, path);
	}

	@Override
	public List<MyFile> getFilesByPageId(int pageType, long pageId) {
		return fileMapper.findAllFilesByPageId(pageType, pageId);
	}

	@Override
	public List<LandingPageFile> getFiles() {
		return fileMapper.findAllFiles();
	}

}
