package com.example.korisnik.mpipproject.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.korisnik.mpipproject.HomelessActivity;
import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.R;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;
import com.example.korisnik.mpipproject.ViewHolders.HomelessViewHolder;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<HomelessViewHolder>  {


    private List<Homeless> homelessList;
    private Context context;

    public RecyclerViewAdapter(List<Homeless> homelessList, Context context) {
        this.homelessList = homelessList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomelessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view,viewGroup,false);
        HomelessViewHolder holder = new HomelessViewHolder(view);
        holder.setParent(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomelessViewHolder homelessViewHolder, int i) {
        Homeless homeless = new Homeless();
        if(homelessList != null) {
            homeless = homelessList.get(i);
            homelessViewHolder.bind(homeless);
            final Homeless finalHomeless = homeless;
            homelessViewHolder.getParent().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,HomelessActivity.class);
                    intent.putExtra("Name", finalHomeless.getName());
                    intent.putExtra("Surname", finalHomeless.getSurname());
                    intent.putExtra("ages", finalHomeless.getAge() + "");
                    intent.putExtra("addedOn", finalHomeless.getAddedOn());
                    intent.putExtra("needs", finalHomeless.getNeeds());
                    intent.putExtra("distance", finalHomeless.getDistance()+ "");
                    intent.putExtra("imageUrl", finalHomeless.getImageUrl());
                    Bundle b = new Bundle();
                    b.putDouble("lat", finalHomeless.getLatitude());
                    b.putDouble("lng",finalHomeless.getLongitude());
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(homelessList != null){
            return homelessList.size();
        }
        return 0;
    }
    public List<Homeless> getHomelessList() {
        return homelessList;
    }

    public void setHomelessList(List<Homeless> homelessList) {
        this.homelessList = homelessList;
        notifyDataSetChanged();
    }

}
