package com.example.billpayment;

import static android.app.PendingIntent.getActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

public class FixedLineBillActivity extends AppCompatActivity {

    Button button;
    EditText fixedLine;
    TextView billId,midPayId,finalPayId,midTerm,finalTerm;
    ConstraintLayout layout;
    public String postUrl = "https://charge.sep.ir/Inquiry/FixedLineBillInquiry";
    OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_line_bill);

        button = findViewById(R.id.button);
        fixedLine = findViewById(R.id.editText);
        layout = findViewById(R.id.layout);
        billId = findViewById(R.id.bill_id);
        midPayId = findViewById(R.id.mid_payment_id);
        finalPayId = findViewById(R.id.final_payment_id);
        midTerm = findViewById(R.id.midTerm);
        finalTerm = findViewById(R.id.finalTerm);

        button.setOnClickListener(view -> {

            try {
                callAPI("{\n" +
                        "    \"FixedLineNumber\": \"" + fixedLine.getText() + "\"\n" +
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            assert response.body() != null;
                            String jsonData = response.body().string();
                            JSONObject object = new JSONObject(jsonData);
                            if (object.getString("code").equals("-16")) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FixedLineBillActivity.this);
                                builder.setMessage("هیچ قبض قابل پرداختی برای این تلفن ثابت استعلام شده ثبت نشده است")
                                        .setTitle("قبض یافت نشد!")
                                        .setCancelable(true);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            } else {
                                layout.setVisibility(View.VISIBLE);
                                finalTerm.setText(object.getJSONObject("data").getJSONObject("FinalTerm").getString("Amount"));
                                midTerm.setText(object.getJSONObject("data").getJSONObject("MidTerm").getString("Amount"));
                                finalPayId.setText(object.getJSONObject("data").getJSONObject("FinalTerm").getString("PaymentID"));
                                midPayId.setText(object.getJSONObject("data").getJSONObject("MidTerm").getString("PaymentID"));
                               billId.setText(object.getJSONObject("data").getJSONObject("FinalTerm").getString("BillID"));
                            }
                        } catch (JSONException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }
        });
    }
}