package com.example.korisnik.mpipproject;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.location.Location;

import com.example.korisnik.mpipproject.Adapters.RecyclerViewAdapter;
import com.example.korisnik.mpipproject.Location.SingleShotLocationProvider;
import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Models.UserInfo;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;
import com.example.korisnik.mpipproject.Repository.UserRepository;
import com.example.korisnik.mpipproject.ViewHolders.HomelessViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HelpFragment extends Fragment {
    private DatabaseReference databaseReference;
    private List<Homeless> data = null;
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private HomelessRepository repository;
    LocationManager locationManager;
    private View view;
    private double lat;
    private double lng;
    private ProgressDialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        view = inflater.inflate(R.layout.fragment_help,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(data,HelpFragment.this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        repository = new HomelessRepository();
        getLocation(getContext());
        dialog=new ProgressDialog(getContext());
        dialog.setMessage("Loading data");
        dialog.show();
        initList();
        return view;
    }

    public void getLocation(Context context) {
        // when you need location
        // if inside activity context = this;

        SingleShotLocationProvider.requestSingleUpdate(context,
                new SingleShotLocationProvider.LocationCallback() {
                    @Override public void onNewLocationAvailable(SingleShotLocationProvider.GPSCoordinates location) {
                        lat = location.latitude;
                        lng = location.longitude;
                    }
                });
    }
    public static float distance(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        float dist = (float) (earthRadius * c);

        return dist/1000;
    }

    public void initList() {
        final List<Homeless> downloadList = new ArrayList<>();

        databaseReference.child("Homeless").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Homeless homeless = postSnapshot.getValue(Homeless.class);
                    Log.e("Get Data", homeless.getName());
                    homeless.setDistance(distance(lat,lng,homeless.getLatitude(),homeless.getLongitude()));
                    downloadList.add(homeless);
                }
                Collections.sort(downloadList);
                dialog.dismiss();
                adapter.setHomelessList(downloadList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }

        });


    }


}
