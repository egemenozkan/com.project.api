package com.project.common.model;

import java.util.List;

public class AutocompleteResponse {
	private List<AutocompleteData> data;
	private String searchQuery;
	
	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List<AutocompleteData> getData() {
		return data;
	}

	public void setData(List<AutocompleteData> data) {
		this.data = data;
	}

}
