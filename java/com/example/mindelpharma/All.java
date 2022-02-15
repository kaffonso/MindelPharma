package com.example.mindelpharma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class All extends AppCompatActivity {
    RecyclerView rv;
    DatabaseHelper mydb;

    ArrayList<String> id, name, address, telephone, fax, email, latitude, longitude;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        rv = findViewById(R.id.rv);

        //inicializando a classe
        mydb = new DatabaseHelper(All.this);

        //inicializando arraylists
        id = new ArrayList<>();
        name = new ArrayList<>();
        address = new ArrayList<>();
        telephone = new ArrayList<>();
        fax = new ArrayList<>();
        email = new ArrayList<>();
        latitude = new ArrayList<>();
        longitude = new ArrayList<>();


        customAdapter = new CustomAdapter(All.this, id, name, address, telephone, fax, email, latitude, longitude);
        rv.setAdapter(customAdapter);
        rv.setLayoutManager(new LinearLayoutManager(All.this));

        storeData();
    }

    void storeData(){
        Cursor cursor = mydb.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"Sem Dados", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
                name.add(cursor.getString(1));
                address.add(cursor.getString(2));
                telephone.add(cursor.getString(3));
                fax.add(cursor.getString(4));
                email.add(cursor.getString(5));
                latitude.add(cursor.getString(6));
                longitude.add(cursor.getString(7));
            }
        }
    }
}