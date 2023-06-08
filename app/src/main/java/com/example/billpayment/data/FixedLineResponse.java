package com.example.billpayment.data;

import com.google.gson.annotations.SerializedName;

public class FixedLineResponse {

	@SerializedName("code")
	private String code;

	@SerializedName("data")
	private Data data;

	@SerializedName("errorMessage")
	private String errorMessage;

	public String getCode(){
		return code;
	}

	public Data getData(){
		return data;
	}

	public String getErrorMessage(){
		return errorMessage;
	}
}