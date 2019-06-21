package com.example.korisnik.mpipproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Random;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.fragment_home, container, false);

        RelativeLayout imageView = (RelativeLayout) rootView.findViewById(R.id.background_image);


        Random r = new Random();
        int i = r.nextInt(4);
        switch (i)
        {
            case 0:
                imageView.setBackgroundResource(R.drawable.background1);
                break;
            case 1:
                imageView.setBackgroundResource(R.drawable.background2);
                break;
            case 2:
                imageView.setBackgroundResource(R.drawable.background3);
                break;
            case 3:
                imageView.setBackgroundResource(R.drawable.background4);
                break;
            case 4:
                imageView.setBackgroundResource(R.drawable.background5);
                break;
        }


        return  rootView;

    }


}
