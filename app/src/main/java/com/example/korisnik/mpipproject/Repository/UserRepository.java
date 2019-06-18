package com.example.korisnik.mpipproject.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Models.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private DatabaseReference databaseReference;
    private List<UserInfo> usersList;
    private Context mContext;

    public UserRepository() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        usersList = new ArrayList<>();
    }

    public void readData(){
        new AsyncTask<Void,Void,Void>(){

            @Override
            protected Void doInBackground(Void... voids) {

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            UserInfo userInfo = postSnapshot.getValue(UserInfo.class);
                            Log.e("Get Data", userInfo.getName());
                            usersList.add(userInfo);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("The read failed: " ,databaseError.getMessage());
                    }
                };
                databaseReference.child("Users").addValueEventListener(valueEventListener);
                return null;
            }
        }.execute();
    }
    public List<UserInfo> getUsersList() {
        readData();
        return usersList;
    }

    public void insert(final UserInfo user){
        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                databaseReference.child("Users").child(user.getUserID()).setValue(user);
                return null;
            }
        }.execute();
    }
}
