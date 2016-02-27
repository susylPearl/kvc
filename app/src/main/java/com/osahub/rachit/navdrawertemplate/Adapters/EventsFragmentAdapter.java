package com.osahub.rachit.navdrawertemplate.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Activities.NavActivity;
import com.osahub.rachit.navdrawertemplate.Fragments.EventsActivities.EventsDetailFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices.NewsDetailFragment;
import com.osahub.rachit.navdrawertemplate.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sushil on 1/27/16.
 */
public class EventsFragmentAdapter extends RecyclerView.Adapter<EventsFragmentAdapter.ViewHolder> {

    private String mTitle;
    private String mShort;
    private String mLong;
    Context context;
    public static final int eventsCase = 456;


    ArrayList<HashMap<String, String>> dataSource = new ArrayList<>();



    public EventsFragmentAdapter(Activity activity, ArrayList<HashMap<String, String>> results) {
        this.dataSource = results;
        this.context = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.tab_fragment_7, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final HashMap<String, String> currentItem = dataSource.get(position);

        holder.title_text.setText(currentItem.get("title"));
        holder.date_text.setText(currentItem.get("date"));
        holder.body_text.setText(currentItem.get("body"));
        Picasso.with(context).load(currentItem.get("attach")).resize(500, 500).into(holder.imageView);


        holder.btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                Intent intent = new Intent(context, NavActivity.class);
                intent.putExtra("title", currentItem.get("title"));
                intent.putExtra("description", currentItem.get("description"));
                intent.putExtra("date", currentItem.get("date"));
                intent.putExtra("pos", eventsCase);
                intent.putExtra("attach", currentItem.get("attach"));

                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_text;
       ImageView imageView;
        Button btn;
        TextView body_text;
        TextView date_text;
        public ViewHolder(View itemView) {
            super(itemView);
            title_text = (TextView) itemView.findViewById(R.id.title_text);
            imageView = (ImageView) itemView.findViewById(R.id.attach);
            body_text = (TextView) itemView.findViewById(R.id.body_text);
            btn = (Button) itemView.findViewById(R.id.btn);
            date_text = (TextView) itemView.findViewById(R.id.date_text);
        }
    }
}
