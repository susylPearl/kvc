package com.osahub.rachit.navdrawertemplate.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.osahub.rachit.navdrawertemplate.Classes.TouchImageView;
import com.osahub.rachit.navdrawertemplate.R;
import com.squareup.picasso.Picasso;
/**
 * Created by sushil on 1/12/16.
 */
public class GalleryPagerAdapter extends PagerAdapter {

    private Context c;
    String[] images;
    int pos;
    LayoutInflater inflater;



    public GalleryPagerAdapter(Context c, String[] images, int position) {
        this.c = c;
        this.images = images;
        this.pos = position;
        inflater = LayoutInflater.from(c);


    }


    @Override
    public int getCount()
    {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view= inflater.inflate(R.layout.item_pager_photo, container, false);


////        NetworkImageView mNetworkImageView = new NetworkImageView(c);
////            ImageView imageView = new ImageView(c);
//        TouchImageView imageView1 = new TouchImageView(c);
////        imageView.setScaleType(ImageView.ScaleType.FIT_START);
//        imageView1.setScaleType(TouchImageView.ScaleType.CENTER);
//
////        mImageLoader = AppController.getInstance().getImageLoader();
////        mImageLoader.get(imageUrl, ImageLoader.getImageListener(mNetworkImageView, R.drawable.progress_bar, R.drawable.a));
////        mNetworkImageView.setImageUrl(imageUrl, mImageLoader);


        final TouchImageView imageView1= (TouchImageView) view.findViewById(R.id.image);



        Picasso.with(c).load("http://demo.peacenepal.com/kvc/college/uploads/albums/" + images[position]).into(imageView1);

        container.addView(view);



        return view;



    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}
