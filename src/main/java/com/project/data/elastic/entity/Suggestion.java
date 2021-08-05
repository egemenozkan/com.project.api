package com.project.data.elastic.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.project.api.data.enums.PlaceType;
import com.project.api.data.enums.ProductType;
import com.project.api.data.model.event.EventType;
import com.project.api.data.model.place.Localisation;

@Document(indexName = "web")
public class Suggestion {
	@Id
	private String id;
	private long productId;
	private ProductType productType;
	private PlaceType placeType;
	private EventType eventType;
	private List<String> keywords;
	@Field(type = FieldType.Nested, includeInParent = true)
	private List<Localisation> localisations;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public PlaceType getPlaceType() {
		return placeType;
	}

	public void setPlaceType(PlaceType placeType) {
		this.placeType = placeType;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<Localisation> getLocalisations() {
		return localisations;
	}

	public void setLocalisations(List<Localisation> localisations) {
		this.localisations = localisations;
	}

}
