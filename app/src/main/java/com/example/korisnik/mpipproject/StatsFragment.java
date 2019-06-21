package com.example.korisnik.mpipproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StatsFragment extends Fragment {
    //TextView words;
    RecyclerView recyclerView;
    String [] items;
    String [] items2;
    private ProgressDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stats, container, false);

       // words=(TextView)rootView.findViewById(R.id.jsoupText);
        items=new String[13];
        items2=new String[13];
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerview);

        dialog=new ProgressDialog(getContext());
        dialog.setMessage("Loading data");
        dialog.show();
        new doit().execute();

        return rootView;

    }
    public class doit extends AsyncTask<Void, Void, Void> {
        String zborovi;

        StringBuilder builder;
        @Override
        protected Void doInBackground(Void... voids) {
            try {

                org.jsoup.nodes.Document doc=Jsoup.connect("http://ckrm.org.mk/category/glavni-vesti/page/2/").get();
                builder=new StringBuilder();
                String title=doc.title();
                Elements titles=doc.select("h2[class=news-list-title]");
                Elements para=doc.select("a[class=view-article]");
                //builder.append(title).append("\n");

                //builder.append("\n").append("Link : ").append(doc.select("h2[class=news-list-title]"))
                       // .append("\n").append("Text : ").append(doc.select("h3[class=news=list-text]"));
                Boolean vleze=false;

                int j=0;
                for(int i=0; i<titles.size(); i++)
                {

                    Element e1= titles.get(i);
                    Element e2 = para.get(i);

                    org.jsoup.nodes.Document document = Jsoup.connect(e2.attr("href")).get();
                    Element link = document.select("p").get(1);
                    items[i]=e1.text();
                    items2[i]=link.text();


                    //builder.append(e1.text()).append("\n").append("Повеќе на Веб сајтот на црвен крст").append("\n").append("\n");



                }
                dialog.dismiss();


            }
            catch (Exception e){e.printStackTrace();}

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new Adapter(getContext(), items, items2));

            //words.setText(builder);
        }
    }
}
