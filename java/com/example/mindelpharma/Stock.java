package com.example.mindelpharma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Stock extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
    }
}