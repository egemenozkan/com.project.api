package com.project.api.data.service;

import java.util.List;

import com.project.api.data.model.file.LandingPageFile;
import com.project.api.data.model.file.MyFile;

public interface IFileService {
	long saveFile(long userId, int pageType, long pageId, String path);
	List<MyFile> getFilesByPageId(int pageType, long pageId);
	List<LandingPageFile> getFiles();
}
