package com.example.korisnik.mpipproject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.korisnik.mpipproject.Models.UserInfo;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class PersonFragment extends Fragment {

    ProgressDialog progressDialog;
    Button logout;
    Button updateinfo;
    FirebaseAuth firebaseAuth;
    CircleImageView imageView;
    public TextView textViewIme;
    private StorageReference Folder;
    private static final int ImageBack = 1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=  inflater.inflate(R.layout.fragment_person, container, false);
        firebaseAuth=FirebaseAuth.getInstance();
        updateinfo=rootView.findViewById(R.id.updateinfo);
        logout=rootView.findViewById(R.id.logout);
        imageView=(CircleImageView)rootView.findViewById(R.id.profile_image);
        textViewIme=(TextView)rootView.findViewById(R.id.textViewIme);
        Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(firebaseAuth.getCurrentUser()==null && isLoggedIn==false)
        {
            getActivity().finish();
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }
        else{
            if(isLoggedIn){
                String userId = Profile.getCurrentProfile().getId();
                DatabaseReference firebaseDatabase=FirebaseDatabase.getInstance().getReference();
                firebaseDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        UserInfo userInfo = dataSnapshot.getValue(UserInfo.class);
                        Glide.with(getContext()).load(userInfo.getUserID()).into(imageView);


                        textViewIme.setText((CharSequence) userInfo.getName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("The read failed: " ,databaseError.getMessage());
                    }
                });

            }
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
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent,ImageBack);
                }
            });

            final FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference firebaseDatabase=FirebaseDatabase.getInstance().getReference();
            firebaseDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(isAdded())
                    {
                        if(dataSnapshot.child(user.getUid()).child("/SlikiZaUser").getValue()!=null)
                        {
                            String dURL = dataSnapshot.child(user.getUid()).child("/SlikiZaUser").getValue().toString();
                            Glide.with(getContext()).load(dURL).into(imageView);
                        }
                    }


                    if(dataSnapshot.child("Users").child(user.getUid()).child("/name").getValue()!=null)
                    {
                        String name=dataSnapshot.child("Users").child(user.getUid()).child("/name").getValue().toString();
                        textViewIme.setText(name);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ImageBack)
        {
            if(resultCode==RESULT_OK)
            {
                final Uri imageData = data.getData();
                imageView.setImageURI(imageData);
                progressDialog=new ProgressDialog(getContext());
                progressDialog.setMessage("Saving profile picture");
                progressDialog.show();
                final StorageReference ImageName=Folder.child("image"+imageData.getLastPathSegment());

                ImageName.putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                        ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                String key=user.getUid().toString();
                                DatabaseReference ImageStore=FirebaseDatabase.getInstance().getReference().child(user.getUid()+"/" + "SlikiZaUser/");

                                String url=String.valueOf(uri);

                                ImageStore.setValue(url).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(), "Saved...", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }
    }

    public void openProfileActivity(){
        Intent intent=new Intent(getContext(), ProfileActivity.class);
        startActivity(intent);
    }

}