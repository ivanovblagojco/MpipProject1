package com.example.korisnik.mpipproject.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.korisnik.mpipproject.R;

import org.w3c.dom.Text;

public class HomelessViewHolder extends RecyclerView.ViewHolder {
    public CardView mCardView;
    public TextView mTextView;
    public HomelessViewHolder(@NonNull View itemView) {
        super(itemView);
    }
    public HomelessViewHolder(LayoutInflater inflater, ViewGroup container){
        super(inflater.inflate(R.layout.card_view,container,false));
        mCardView = itemView.findViewById(R.id.card_container);
        mTextView = itemView.findViewById(R.id.text_holder);
    }
}
