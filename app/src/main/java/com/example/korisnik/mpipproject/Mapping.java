package com.example.korisnik.mpipproject;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Mapping extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION_PERMISSION=1;
    private HomelessRepository homelessRepository;

    LocationListener locationListener;
    double latitude, longitude;
    Marker marker;
    Button Save;
    String url;
    String lat;
    String lon;
    String name;
    String ages;
    String surname;
    String needs;
    Uri imageData;
    String img;
    private StorageReference Folder;
    private FirebaseAuth auth;
    public TextView lati;
    public  TextView longi;
    public ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapping);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        homelessRepository=new HomelessRepository();
        auth = FirebaseAuth.getInstance();
        Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");
        dialog=new ProgressDialog(Mapping.this);
        lati=(TextView) findViewById(R.id.lat);
        longi=(TextView)findViewById(R.id.lon);
        Save=(Button)findViewById(R.id.save);
        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==Save)
                {
                    dialog.setMessage("Saving...");
                    dialog.show();
                    Intent intent=getIntent();
                    name=intent.getExtras().getString("Name");
                    surname=intent.getExtras().getString("Surname");
                    needs=intent.getExtras().getString("Needs");
                    img=intent.getExtras().getString("Imagedata");
                    ages=intent.getExtras().getString("Ages");
                    imageData=Uri.parse(img);

                    final StorageReference ImageName=Folder.child("image"+imageData.getLastPathSegment());

                    ImageName.putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                    ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                     @Override
                    public void onSuccess(Uri uri) {
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

                    //Adding new person to database
                    //Needs neresheno
                    String url=String.valueOf(uri);

                    //Za needs imam edna ideja kje ja sredime na kraj koa kje ja zavrshuvame

                    int ageH=Integer.parseInt(ages);


                    Homeless person = new Homeless(name,surname,url,needs,22,latitude,longitude);
                    homelessRepository.insert(person);
                    dialog.dismiss();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                     }
                    });
                      }
                    });
                }

            }
        });
        locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);

       if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION_PERMISSION);
        }


        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitude=location.getLatitude();
                longitude=location.getLongitude();
                lati.setText(latitude+"");
                longi.setText(longitude+"");
                Geocoder geocoder=new Geocoder(getApplicationContext());

                try {
                    List<Address> address=geocoder.getFromLocation(latitude, longitude,1);
                    String result=address.get(0).getLocality()+":";
                    result+=address.get(0).getCountryName();
                    LatLng latLng=new LatLng(latitude, longitude);

                    if(marker!=null)
                    {
                        marker.remove();
                        marker=mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    }
                    else {
                        marker=mMap.addMarker(new MarkerOptions().position(latLng).title(result));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    }
                   /* StringBuilder stringBuilder=new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                    stringBuilder.append("location="+latitude+","+longitude);
                    stringBuilder.append("&radius="+10000);
                    stringBuilder.append("&keyword="+"restaurant");
                    stringBuilder.append("&key="+"AIzaSyD1qNrBFoW5ZZzxDb6i0Mm5sr-cxYynbXI");

                    url=stringBuilder.toString();

                    Object dataTransfer[]=new Object[2];
                    dataTransfer[0]=mMap;
                    dataTransfer[1]=url;*/

                   // GetNearbyPlaces getNearbyPlaces=new GetNearbyPlaces();
                    //getNearbyPlaces.execute(mMap, url);

                    //MyService myService=new MyService(mMap, url);
                    //myService.startService(new Intent(getApplicationContext(), myService.getClass()));


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        // LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}

//da probam da stavam minTime of update na 5000
//da proveram vo GetNearbyPlaces so direct toString()
//da probam so sleep na tread