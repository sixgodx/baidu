package com.sto.entity;

public class Area {

	//大区系统编号
	private String areaSysId;
	
	//大区名称
	private String areaName;
	
	//大区代码
	private String areaCode;
	
	//大区等级
	private String areaLev;
	
	//启用状态
	private String enabled;
	
	//同步时间戳
	private String syncTimestamp;

	public String getAreaSysId() {
		return areaSysId;
	}

	public void setAreaSysId(String areaSysId) {
		this.areaSysId = areaSysId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaLev() {
		return areaLev;
	}

	public void setAreaLev(String areaLev) {
		this.areaLev = areaLev;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getSyncTimestamp() {
		return syncTimestamp;
	}

	public void setSyncTimestamp(String syncTimestamp) {
		this.syncTimestamp = syncTimestamp;
	}
	
	
}
