package com.project.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.project.api.data.model.autocomplete.Item;

public interface AutocompleteRepository extends ElasticsearchRepository<Item, String> {
	Page<Item> findByKeyword(String name, Pageable pageable);
}