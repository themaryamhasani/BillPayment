package com.example.billpayment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BuyChargeActivity extends AppCompatActivity {
    public String postUrl = "https://topup.pec.ir/";
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    Button button;
    EditText phoneNo, amount;
    RadioGroup radioGroup;
    String operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_charge);

        button = findViewById(R.id.button);
        phoneNo = findViewById(R.id.editText);
        amount = findViewById(R.id.editText2);
        radioGroup = findViewById(R.id.radioGroup);

        button.setOnClickListener(view -> {

            if (radioGroup.getCheckedRadioButtonId() == R.id.irancel) {
                operator = "1";
            } else if (radioGroup.getCheckedRadioButtonId() == R.id.hamrah) {
                operator = "2";
            } else {
                operator = "3";
            }
            try {
                callAPI("{\n" +
                        "    \"MobileNo\": \"" + phoneNo.getText() + "\",\n" +
                        "    \"OperatorType\": " + operator + ",\n" +
                        "    \"AmountPure\": " + amount.getText() + ",\n" +
                        "    \"mid\": \"0\"\n" +
                        "}");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    void callAPI(String data) throws IOException {

        RequestBody body = RequestBody.create(JSON, data);
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
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                try {
                    assert response.body() != null;
                    String jsonData = response.body().string();
                    JSONObject object = new JSONObject(jsonData);
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(object.get("url").toString()));
                    startActivity(browserIntent);
                } catch (IOException | JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }
}
