package com.example.korisnik.mpipproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.Models.UserInfo;
import com.example.korisnik.mpipproject.Repository.HomelessRepository;
import com.example.korisnik.mpipproject.Repository.UserRepository;
import com.example.korisnik.mpipproject.ViewHolders.HomelessViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HelpFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecyclerViewAdapter());


        return view;
    }
    private class RecyclerViewAdapter extends RecyclerView.Adapter<HomelessViewHolder>  {
        List<Homeless> homelessList;
        HomelessRepository repository;
        public RecyclerViewAdapter() {
            repository = new HomelessRepository();
            homelessList = new ArrayList<>();
            homelessList = repository.getHomelessList();
            }

        @NonNull
        @Override
        public HomelessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new HomelessViewHolder(inflater, viewGroup);
        }

        @Override
        public void onBindViewHolder(@NonNull HomelessViewHolder homelessViewHolder, int i) {
            homelessViewHolder.mTextView.setText(homelessList.get(i).getName());
        }

        @Override
        public int getItemCount() {
            //return homelessList.size();
            return 5;
        }
    }

}
