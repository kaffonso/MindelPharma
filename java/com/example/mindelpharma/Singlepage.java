package com.example.mindelpharma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;

public class Singlepage extends AppCompatActivity {
    TextView phone_result, fax_result, email_result;
    ImageView iv_images, qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlepage);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));

        phone_result = findViewById(R.id.phone_result);
        fax_result = findViewById(R.id.fax_result);
        email_result = findViewById(R.id.email_result);
        iv_images = findViewById(R.id.iv_images);
        iv_images.setClipToOutline(true);
        qrcode = findViewById(R.id.qrcode);

        phone_result.setText(getIntent().getStringExtra("telephone"));
        fax_result.setText(getIntent().getStringExtra("fax"));
        String email = getIntent().getStringExtra("email");
        if (email.equals("null")){
            email_result.setText("Sem Email");
        } else {
            email_result.setText(email);
        }

        if(getIntent().getStringExtra("id").equals("1")){
            iv_images.setImageResource(R.mipmap.fotonena);
            qrcode.setImageResource(R.mipmap.qrnena);
        } else if(getIntent().getStringExtra("id").equals("2")){
            iv_images.setImageResource(R.mipmap.fotohigiene);
            qrcode.setImageResource(R.mipmap.qrhigiene);
        } else if(getIntent().getStringExtra("id").equals("3")){
            iv_images.setImageResource(R.mipmap.fotojovem);
            qrcode.setImageResource(R.mipmap.qrjovem);
        } else if(getIntent().getStringExtra("id").equals("4")){
            iv_images.setImageResource(R.mipmap.fotomindelo);
            qrcode.setImageResource(R.mipmap.qrmindelo);
        } else if(getIntent().getStringExtra("id").equals("5")){
            iv_images.setImageResource(R.mipmap.avenida);
            qrcode.setImageResource(R.mipmap.qravenida);
        } else {
            iv_images.setImageResource(R.mipmap.fotoleao);
            qrcode.setImageResource(R.mipmap.qrleao);
        }
    }

    public void gotoStock(View view) {
        Intent page = new Intent(this, Stock.class);
        page.putExtra("name",String.valueOf(getIntent().getStringExtra("name")));
        startActivity(page);
    }

    public void openMap(View view) {
        String latiude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");

        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + latiude + "," + longitude);

        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");

        startActivity(mapIntent);
    }


}