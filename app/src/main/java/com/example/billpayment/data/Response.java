package com.example.billpayment.data;

import com.google.gson.annotations.SerializedName;

public class Response{
	@SerializedName("url")
	private String url;

	public String getUrl(){
		return url;
	}
}