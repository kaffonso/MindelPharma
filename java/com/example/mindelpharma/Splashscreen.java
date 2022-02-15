package com.example.mindelpharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splashscreen extends AppCompatActivity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        getSupportActionBar().hide();
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent page = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(page);
                finish();
            }
        }, 1500);
    }
}