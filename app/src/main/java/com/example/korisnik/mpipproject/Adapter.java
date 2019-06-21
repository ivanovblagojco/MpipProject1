package com.example.korisnik.mpipproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    String [] items;
    String [] items2;
    public Adapter(Context context, String [] items, String [] items2)
    {
        this.context=context;
        this.items=items;
        this.items2=items2;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View row=inflater.inflate(R.layout.costume_row, viewGroup, false);
        Item item=new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((Item)viewHolder).textView.setText(items[i]);
        ((Item)viewHolder).textView2.setText(items2[i]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
    public class Item extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        public Item(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.item);
            textView2=itemView.findViewById(R.id.item2);
        }
    }
}
