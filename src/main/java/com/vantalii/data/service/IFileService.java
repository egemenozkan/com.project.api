package com.vantalii.data.service;

import java.util.List;

import com.project.api.data.model.file.LandingPageFile;
import com.project.api.data.model.file.MyFile;

public interface IFileService {
	long saveFile(long userId, int pageType, long pageId, String uploadDir, String name, String extension);
	List<MyFile> getFilesByPageId(int pageType, long pageId);
	List<LandingPageFile> getFiles();
}
