package com.project.enginee.service;

import com.project.common.model.AutocompleteResponse;

public interface IAutocompleteService {
	AutocompleteResponse searchPlace(String query);
}
