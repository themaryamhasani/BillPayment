package com.example.billpayment.data;

import com.google.gson.annotations.SerializedName;

public class MidTerm{

	@SerializedName("USSDCode")
	private Object uSSDCode;

	@SerializedName("Amount")
	private int amount;

	@SerializedName("BillID")
	private Object billID;

	@SerializedName("PaymentID")
	private Object paymentID;

	@SerializedName("ValidForPayment")
	private boolean validForPayment;

	@SerializedName("Cycle")
	private Object cycle;

	public Object getUSSDCode(){
		return uSSDCode;
	}

	public int getAmount(){
		return amount;
	}

	public Object getBillID(){
		return billID;
	}

	public Object getPaymentID(){
		return paymentID;
	}

	public boolean isValidForPayment(){
		return validForPayment;
	}

	public Object getCycle(){
		return cycle;
	}
}