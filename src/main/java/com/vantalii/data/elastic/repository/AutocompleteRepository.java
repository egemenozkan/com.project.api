package com.vantalii.data.elastic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.vantalii.data.elastic.entity.Suggestion;

public interface AutocompleteRepository extends ElasticsearchRepository<Suggestion, String> {
//	List<Suggestion> findByLabelContainingIgnoreCaseAndLanguage(String label, Language language);	
	List<Suggestion> findByLocalisationsNameInIgnoreCase(Collection<String> names);
//	List<Suggestion> findByLocalisationsNameLikeIgnoreCase(String query);

}
