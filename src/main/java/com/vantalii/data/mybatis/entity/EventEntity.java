package com.vantalii.data.mybatis.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import com.project.api.data.enums.FeeType;
import com.project.api.data.enums.PeriodType;
import com.project.api.data.model.event.EventStatus;
import com.project.api.data.model.event.EventType;
import com.project.common.enums.Language;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventEntity implements Serializable {


	private static final long serialVersionUID = 1L;

	private long id;
	private long masterId;
	private int placeId;
	private String name;
	private String description;
	private EventType type;
	private PeriodType periodType;
	private Language language;
	private String slug;
	
	
	private EventStatus status;
	private LocalDate startDate;
	private LocalTime startTime;
	private LocalDate endDate;
	private LocalTime endTime;
	private boolean showStartDate;
	private boolean showEndDate;
	private boolean showStartTime;
	private boolean showEndTime;
	private boolean twentyFourSeven;
	
	private FeeType feeType;
	private String biletixId;



}
