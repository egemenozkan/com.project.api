package com.project.api.service;

import com.project.api.data.model.autocomplete.AutocompleteRequest;
import com.project.api.data.model.autocomplete.AutocompleteResponse;

public interface IAutocompleteService {
	
	AutocompleteResponse autocomplete(AutocompleteRequest autocompleteRequest);
	void putPlacesToElasticSearch();
}
