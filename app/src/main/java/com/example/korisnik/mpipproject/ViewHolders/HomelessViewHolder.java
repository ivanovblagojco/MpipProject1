package com.example.korisnik.mpipproject.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.korisnik.mpipproject.Models.Homeless;
import com.example.korisnik.mpipproject.R;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class HomelessViewHolder extends RecyclerView.ViewHolder {
    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public ImageView imageView;
    public TextView textView;
    public TextView textAges;
    public TextView textDistance;
    private View parent;

    public View getParent() {
        return parent;
    }

    public void setParent(View parent) {
        this.parent = parent;
    }

    public TextView getTextDistance() {
        return textDistance;
    }

    public void setTextDistance(TextView textDistance) {
        this.textDistance = textDistance;
    }

    public TextView getTextAges() {
        return textAges;
    }

    public void setTextAges(TextView textAges) {
        this.textAges = textAges;
    }

    public HomelessViewHolder(@NonNull View itemView) {
        super(itemView);

        this.imageView = (ImageView)itemView.findViewById(R.id.imageView1);
        this.textView = (TextView)itemView.findViewById(R.id.text_holder);
        this.textAges = (TextView)itemView.findViewById(R.id.text_ages);
        this.textDistance = (TextView)itemView.findViewById(R.id.text_distance);
    }

    public void bind(final Homeless item){
        Glide.with(imageView.getContext())
                .load(item.getImageUrl())
                .into(getImageView());
        textView.setText(String.format("%s %s",item.getName(),item.getSurname()));
        textAges.setText(String.format("Age: %s",item.getAge()));
        textDistance.setText(String.format("%.1f",item.getDistance()));
    }
}
