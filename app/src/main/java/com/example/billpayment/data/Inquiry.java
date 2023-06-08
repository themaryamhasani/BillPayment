package com.example.billpayment.data;

import com.google.gson.annotations.SerializedName;

public class Inquiry{

	@SerializedName("Favorite")
	private boolean favorite;

	@SerializedName("Type")
	private Object type;

	@SerializedName("Description")
	private Object description;

	@SerializedName("Value")
	private Object value;

	@SerializedName("Electronic")
	private boolean electronic;

	@SerializedName("ID")
	private int iD;

	public boolean isFavorite(){
		return favorite;
	}

	public Object getType(){
		return type;
	}

	public Object getDescription(){
		return description;
	}

	public Object getValue(){
		return value;
	}

	public boolean isElectronic(){
		return electronic;
	}

	public int getID(){
		return iD;
	}
}