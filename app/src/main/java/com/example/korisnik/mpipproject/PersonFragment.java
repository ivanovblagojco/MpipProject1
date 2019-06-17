package com.example.korisnik.mpipproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PersonFragment extends Fragment {

    Button logout;
    Button updateinfo;
    FirebaseAuth firebaseAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView=  inflater.inflate(R.layout.fragment_person, container, false);
       firebaseAuth=FirebaseAuth.getInstance();
        updateinfo=rootView.findViewById(R.id.updateinfo);
        logout=rootView.findViewById(R.id.logout);
        updateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileActivity();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();

                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });


        return rootView;
    }
    public void openProfileActivity(){
        Intent intent=new Intent(getContext(), ProfileActivity.class);
        startActivity(intent);
    }

}
