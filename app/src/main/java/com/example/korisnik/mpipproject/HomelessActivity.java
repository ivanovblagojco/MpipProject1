package com.example.korisnik.mpipproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.korisnik.mpipproject.Models.Homeless;

public class HomelessActivity extends AppCompatActivity {
    private Homeless homeless;
    private ImageView imageView;
    private TextView name;
    private TextView ages;
    private TextView needs;
    private TextView addedOn;
    private TextView distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeless);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");

        bindItems();


    }

    private void bindItems() {
        name = findViewById(R.id.textView);
        ages = findViewById(R.id.textView2);
        distance = findViewById(R.id.textView3);
        needs = findViewById(R.id.textView4);
        addedOn = findViewById(R.id.textView5);
    }
}
