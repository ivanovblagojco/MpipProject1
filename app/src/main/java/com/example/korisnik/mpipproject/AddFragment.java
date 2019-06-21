package com.example.korisnik.mpipproject;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.app.Activity.RESULT_OK;

public class AddFragment extends Fragment {
    private static final int ImageBack = 1;
   // Button buttonSave;
    Button buttonCamera;
    Button buttonNew;
    ImageView imageView;
    EditText editTextName;
    EditText editTextSurname;
    EditText editTextNeeds;
    EditText editTextAge;
    TextView locationTextView;

    Button for_loc;
    Uri uri;
    String userID;
    private static final int CAMERA_REQUEST_CODE = 1;
    private StorageReference Folder;
    private FirebaseAuth auth;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);

        buttonCamera = (Button) rootView.findViewById(R.id.add_photo);
        buttonNew=(Button) rootView.findViewById(R.id.new_photo);
        imageView = (ImageView) rootView.findViewById(R.id.slika);
        //buttonSave = (Button) rootView.findViewById(R.id.save);

        editTextName = (EditText) rootView.findViewById(R.id.name);
        editTextSurname = (EditText) rootView.findViewById(R.id.surname);
        editTextNeeds = (EditText) rootView.findViewById(R.id.needs);
        editTextAge=(EditText)rootView.findViewById(R.id.age);
        //checkFilePermissions();
        for_loc=(Button)rootView.findViewById(R.id.loc);







        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()==null)
        {
            getActivity().finish();
            Intent intent  = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }
        Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");



        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,ImageBack);
            }
        });

        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent, ImageBack);
            }
        });

        return rootView;
    }
    //ne e povikana nikade
    private void checkFilePermissions() {
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP){
            int permissionCheck = getContext().checkSelfPermission("Manifest.permission.READ_EXTERNAL_STORAGE");
            permissionCheck += getContext().checkSelfPermission("Manifest.permission.WRITE_EXTERNAL_STORAGE");
            permissionCheck += getContext().checkSelfPermission("Manifest.permission.CAMERA");
            if (permissionCheck != 0) {
                this.requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1001); //Any number
            }
        }else{

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        if(requestCode==ImageBack)
        {
            if(resultCode==RESULT_OK)
            {

                    //Bundle bundle =data.getExtras();
                    //final Bitmap bitmap=(Bitmap)bundle.get("data");
                    //imageView.setImageBitmap(bitmap);
                final Uri imageData = data.getData();
                imageView.setImageURI(imageData);





                for_loc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // final StorageReference ImageName=Folder.child("image"+imageData.getLastPathSegment());

                        //ImageName.putFile(imageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            //@Override
                            //public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {



                                //ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                  //  @Override
                                    //public void onSuccess(Uri uri) {
                                       // FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                        //DatabaseReference ImageStore=FirebaseDatabase.getInstance().getReference().child(user.getUid()+"/" + "Prijaveni/");

                                        String name=editTextName.getText().toString().trim();
                                        String surname =editTextSurname.getText().toString().trim();
                                        String needs=editTextNeeds.getText().toString().trim();
                                        String age=editTextAge.getText().toString().trim();
                                        String url = String.valueOf(uri);
                                        Intent intent=new Intent(getContext(), Mapping.class);
                                        intent.putExtra("Imagedata" , imageData.toString());
                                        intent.putExtra("Name" , name);
                                        intent.putExtra("Surname" , surname);
                                        intent.putExtra("Needs", needs);
                                        intent.putExtra("Ages", age);
                                        startActivity(intent);
                                        //ImageStore.setValue(userApplicant).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            //@Override
                                          //  public void onSuccess(Void aVoid) {
                                               // Toast.makeText(getContext(), "Saved...", Toast.LENGTH_SHORT).show();
                                           // }
                                        //});



                                   // }
                              //  });
                          //  }
                        //});
                    }
                });
            }
        }
    }

}
