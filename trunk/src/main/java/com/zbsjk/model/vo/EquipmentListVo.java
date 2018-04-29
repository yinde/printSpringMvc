package com.zbsjk.model.vo;

import com.zbsjk.model.entity.EquipmentInfo;

public class EquipmentListVo extends EquipmentInfo{

	private String startTime;
	
	private String endTime;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
