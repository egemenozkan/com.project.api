package com.project.data.service;

import java.util.List;

import com.project.common.enums.Language;
import com.project.data.elastic.entity.Suggestion;

public interface IDataService {
	List<Suggestion> search(String query, Language language);
	String save(Suggestion suggestion);
	void putPlacesToElasticSearch();
	
}
