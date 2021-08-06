package com.vantalii.data.mybatis.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.project.api.data.enums.PlaceType;
import com.project.common.enums.Language;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceEntity implements Serializable {

	private static final long serialVersionUID = 4532775373830146176L;
	
	private long id;
	private String name;
	private Language language;
	private String slug;
	private int mainImageId;
	private PlaceType type;
	private long addressId;
	private long contactId;
	
	
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;


}
