package com.osahub.rachit.navdrawertemplate.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import com.osahub.rachit.navdrawertemplate.Fragments.Gallery.GalleryItemShowFragment;
import com.osahub.rachit.navdrawertemplate.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by Su syl on 1/5/2016.
 */
public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {
    private Context mContext;
    private String[] mIcons;
    private String[] mText;
    private List<PhotoAlbum> data;
    public static final int galleryCase = 789;

    public ImageGalleryAdapter(Context context, List<PhotoAlbum> albums) {
        this.mContext = context;
        this.data = albums;

    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_gallery_card_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final PhotoAlbum photo = data.get(position);

        Picasso.with(mContext).load("http://demo.peacenepal.com/kvc/college/uploads/albums/" +photo.getUrl()).placeholder(R.drawable.progress_bar).resize(500, 500).into(holder.image);
        holder.textView1.setText(photo.getTitle());
//        holder.textView2.setText(photo.getDescription());
//        holder.textView3.setText(photo.getUpdate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public void onClick(View v) {
                mIcons = photo.getImagesUrl();

                Intent intent = new Intent(mContext, NavActivity.class);
                intent.putExtra("images", photo.getImagesUrl());
                intent.putExtra("id", photo.getId());
                intent.putExtra("pos", galleryCase);
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
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView textView1, textView2, textView3;


        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.attach);
            textView1 = (TextView) itemView.findViewById(R.id.body_text);

//              textView2 = (TextView) itemView.findViewById(R.id.textView8);
////            textView3 = (TextView) itemView.findViewById(R.id.textView7);


        }
    }


}
