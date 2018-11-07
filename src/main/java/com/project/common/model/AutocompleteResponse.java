package com.project.common.model;

import java.util.List;

public class AutocompleteResponse {
	private List data;
	private String searchQuery;
	private boolean success;
	
	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public boolean isSuccess() {
	    return success;
	}

	public void setSuccess(boolean success) {
	    this.success = success;
	}

}
