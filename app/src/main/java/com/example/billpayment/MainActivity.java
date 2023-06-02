package com.example.billpayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    MaterialCardView bill,charge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bill = findViewById(R.id.fixed_line_bill);
        charge = findViewById(R.id.buy_charge);

        bill.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FixedLineBillActivity.class);
            startActivity(intent);
        });

        charge.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, BuyChargeActivity.class);
            startActivity(intent);
        });
    }
}