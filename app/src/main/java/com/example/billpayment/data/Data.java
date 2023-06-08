package com.example.billpayment.data;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("MidTerm")
	private MidTerm midTerm;

	@SerializedName("FinalTerm")
	private FinalTerm finalTerm;

	@SerializedName("Inquiry")
	private Inquiry inquiry;

	public MidTerm getMidTerm(){
		return midTerm;
	}

	public FinalTerm getFinalTerm(){
		return finalTerm;
	}

	public Inquiry getInquiry(){
		return inquiry;
	}
}