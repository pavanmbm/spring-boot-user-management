package com.dhl.usermanagement.util;

import java.util.List;

public class ServiceResponse {

	private String message;
	private List<?> dataList;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public ServiceResponse(List<?> dataList, String message) {
		super();
		this.message = message;
		this.dataList=dataList;
	}


	public ServiceResponse(String message) {
		this.message = message;
	}

	
}
