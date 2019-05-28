package com.example.stock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class choice extends AppCompatActivity {
String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Intent i = getIntent();
        email=i.getStringExtra("email");
    }

    public void sell(View view) {
        Intent i = new Intent(this,user_stock_display.class);
        i.putExtra("email",email);
        startActivity(i);
    }

    public void buy(View view) {
        Intent i = new Intent(this,Market.class);
        i.putExtra("email",email);
        startActivity(i);
    }
}
