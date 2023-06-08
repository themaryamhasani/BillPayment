package com.example.billpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.billpayment.data.FixedLineResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FixedLineBillActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_line_bill);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://charge.sep.ir/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<FixedLineResponse> call = api.getBillInquiry("02122129043");
        call.enqueue(new Callback<FixedLineResponse>() {
            @Override
            public void onResponse(Call<FixedLineResponse> call, Response<FixedLineResponse> response) {
                String str = response.body().getData().getInquiry().getDescription().toString();
                Toast.makeText(FixedLineBillActivity.this, str, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<FixedLineResponse> call, Throwable t) {

            }
        });
    }

}