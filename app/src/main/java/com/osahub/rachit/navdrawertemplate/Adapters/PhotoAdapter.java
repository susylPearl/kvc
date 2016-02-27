package com.osahub.rachit.navdrawertemplate.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Activities.NavActivity;
import com.osahub.rachit.navdrawertemplate.Classes.PhotoAlbum;
import com.osahub.rachit.navdrawertemplate.Fragments.Gallery.GalleryDetailsFragment;
import com.osahub.rachit.navdrawertemplate.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by Su syl on 1/5/2016.
 */
public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {
    private Context mContext;
    private String[] mIcons;
    private int mId;
    public final static int photoCase = 111;

    public PhotoAdapter(Context context, int id, String[] urls) {
        this.mContext = context;
        this.mIcons = urls;
        this.mId = id;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_card_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        Picasso.with(mContext).load("http://demo.peacenepal.com/kvc/college/uploads/albums/" +mIcons[position]).placeholder(R.drawable.errorimage).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mContext, NavActivity.class);
                intent.putExtra("images", mIcons);
                intent.putExtra("id", position);
                intent.putExtra("pos", photoCase);
                mContext.startActivity(intent);




            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mIcons.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
//            image.setScaleType(ImageView.ScaleType.FIT_CENTER);


        }
    }


}
