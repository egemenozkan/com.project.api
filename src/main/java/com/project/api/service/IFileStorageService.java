package com.project.api.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {
	String storeFile(MultipartFile file, long userId, int pageType, long pageId);

	Resource loadFileAsResource(String fileName);

}
