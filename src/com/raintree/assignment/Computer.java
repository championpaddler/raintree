package com.raintree.assignment;

import java.util.Date;

public class Computer {

	private String name;
	private Date faultDate;
	private Integer faultCount;
	private FaultType faultType;

	@SuppressWarnings("deprecation")
	public Computer(String pcName, String date, FaultType date2) {
		this.name = pcName;
		this.faultDate = new Date(date);
		this.faultType = date2;
		this.faultCount = 1;
	}

	public Integer getFaultCount() {
		return faultCount;
	}

	public void setFaultCount(Integer faultCount) {
		this.faultCount = faultCount;
	}

	public Date getFaultDate() {
		return faultDate;
	}

	public void setFaultDate(Date faultDate) {
		this.faultDate = faultDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FaultType getFaultType() {
		return faultType;
	}

	public void setFaultType(FaultType faultType) {
		this.faultType = faultType;
	}

	public boolean isValid(Date start, Date end) {
		return (this.faultDate.compareTo(start) >= 0 && this.faultDate.compareTo(end) <= 0);
	}

}
