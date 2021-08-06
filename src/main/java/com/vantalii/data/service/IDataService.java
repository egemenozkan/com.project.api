package com.vantalii.data.service;

import java.util.List;

import com.project.common.enums.Language;
import com.vantalii.data.elastic.entity.Suggestion;

public interface IDataService {
	List<Suggestion> search(String query, Language language);
	String save(Suggestion suggestion);
	void putPlacesToElasticSearch();
	
}
