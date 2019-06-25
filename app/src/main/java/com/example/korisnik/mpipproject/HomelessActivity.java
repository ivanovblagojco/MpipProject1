package com.example.korisnik.mpipproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.korisnik.mpipproject.Models.Homeless;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomelessActivity extends AppCompatActivity implements OnMapReadyCallback {
    private Homeless homeless;
    private ImageView imageView;
    private TextView name;
    private TextView ages;
    private TextView needs;
    private TextView addedOn;
    private TextView distance;
    private String imageUrl;
    private double lat;
    private double lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeless);
        Intent intent = getIntent();
        String name = intent.getStringExtra("Name");
        String surname = intent.getStringExtra("Surname");
        String ages = intent.getStringExtra("ages");
        String needs = intent.getStringExtra("needs");
        String addedOn = intent.getStringExtra("addedOn");
        String distance = intent.getStringExtra("distance");
        imageUrl = intent.getStringExtra("imageUrl");
        Bundle b = getIntent().getExtras();
        lat = b.getDouble("lat");
        lng = b.getDouble("lng");

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        bindItems(name,surname,ages,needs,addedOn,distance);


    }

    private void bindItems(String name, String surname, String ages, String needs, String addedOn, String distance) {
        this.name = (TextView)findViewById(R.id.textView);
        this.ages = (TextView)findViewById(R.id.textView2);
        this.distance = (TextView)findViewById(R.id.textView3);
        this.needs = (TextView)findViewById(R.id.textView4);
        this.addedOn = (TextView)findViewById(R.id.textView5);
        this.imageView = (ImageView)findViewById(R.id.imageView);
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
        this.name.setText(String.format("%s %s",name,surname));
        this.distance.setText(String.format("Distance: %.3s",distance));
        this.ages.setText(String.format("%s years old",ages));
        this.needs.setText(String.format("Needs for: %s",needs));
        this.addedOn.setText(String.format("Added on: %s",addedOn));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng homelessLocation = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(homelessLocation)
                .title("Homeless Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(homelessLocation));
    }
}
