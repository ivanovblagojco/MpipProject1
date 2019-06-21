package com.example.korisnik.mpipproject;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Models.UserInfo;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;
import com.example.korisnik.mpipproject.Repository.UserRepository;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    HomelessRepository repository;
    UserRepository repository1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkFilePermissions();
        BottomNavigationView bottomNav=findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new HomeFragment()).commit();

    }
    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = getApplicationContext().checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += getApplicationContext().checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            permissionCheck += getApplicationContext().checkSelfPermission("Manifest.permission.CAMERA");
            permissionCheck += getApplicationContext().checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA,Manifest.permission.ACCESS_FINE_LOCATION}, 1001); //Any number
            }
        }else{

        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment= null;
                    switch (item.getItemId())
                    {
                        case R.id.nav_home:
                            selectedFragment=new HomeFragment();
                        break;
                        case R.id.nav_help:
                            selectedFragment=new HelpFragment();
                            break;
                        case R.id.nav_add:
                            selectedFragment=new AddFragment();
                            break;
                        case R.id.nav_stats:
                            selectedFragment=new StatsFragment();
                            break;
                        case R.id.nav_person:
                            selectedFragment=new PersonFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, selectedFragment).commit();
                    return true;
                }
            };
}
