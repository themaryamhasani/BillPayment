package com.example.billpayment;

import com.example.billpayment.data.FixedLineResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface API {

    @POST("Inquiry/FixedLineBillInquiry")
    Call<FixedLineResponse> getBillInquiry(@Field("FixedLineNumber")String number);
}
