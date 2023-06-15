package com.zexis.sd.domain.dto;

import com.taskadapter.redmineapi.bean.Tracker;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Innsue {
	
	private Tracker tracker;
	
	private String title;
	
	private Integer assignedId;
	
	private Double esTime;
	
	private Double planTime;
	
}
