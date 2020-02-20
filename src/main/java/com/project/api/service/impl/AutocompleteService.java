package com.project.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.project.api.data.model.autocomplete.AutocompleteRequest;
import com.project.api.data.model.autocomplete.AutocompleteResponse;
import com.project.api.data.model.autocomplete.Item;
import com.project.api.service.IAutocompleteService;

@Service
public class AutocompleteService implements IAutocompleteService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public AutocompleteResponse autocomplete(AutocompleteRequest autocompleteRequest) {
		AutocompleteResponse autocompleteResponse = new AutocompleteResponse();
		if (autocompleteRequest == null || autocompleteRequest.getQuery().isBlank()) {
			autocompleteResponse.setSuccess(false);
			return autocompleteResponse;
		}


		Map<String,String> uriVariables =new TreeMap<>();
		uriVariables.put("query", autocompleteRequest.getQuery());
		uriVariables.put("languageCode", autocompleteRequest.getLanguage().getCode());
		List<Item> items = restTemplate.getForObject("http://127.0.0.1:8082/v1/elastic/autocomplete/places?query={query}&languageCode={languageCode}", List.class, uriVariables);
		


		
		
//		items = items == null ? new ArrayList<Item>() : items;
		if (CollectionUtils.isEmpty(items)) {
			autocompleteResponse.setSuccess(Boolean.FALSE);
			return autocompleteResponse;
		}
		
		autocompleteResponse.setSuccess(Boolean.TRUE);
		autocompleteResponse.setItems(items);
		
		return autocompleteResponse;
	}

}
