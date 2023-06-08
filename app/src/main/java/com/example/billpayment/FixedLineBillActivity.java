package com.example.billpayment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FixedLineBillActivity extends AppCompatActivity {

    public String postUrl = "https://charge.sep.ir/Inquiry/FixedLineBillInquiry";
    OkHttpClient client = new OkHttpClient();
    String bill;
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_line_bill);
        try {
            callAPI("02122129043");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void callAPI(String number) throws IOException {

        RequestBody body = RequestBody.create(JSON, number);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                call.cancel();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                assert response.body() != null;
                bill = response.body().string();
                runOnUiThread(() -> Toast.makeText(FixedLineBillActivity.this, bill, Toast.LENGTH_SHORT).show());
            }
        });
    }
}