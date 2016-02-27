package com.osahub.rachit.navdrawertemplate.Fragments.Gallery;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Activities.NavActivity;
import com.osahub.rachit.navdrawertemplate.Adapters.GalleryPagerAdapter;
import com.osahub.rachit.navdrawertemplate.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sushil on 1/29/16.
 */
public class GalleryDetailsFragment extends Fragment {

    ViewPager pager;
    GalleryPagerAdapter adapter;
    String[] mImages;
    int position;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((NavActivity)getActivity()).setTitle("Slideshow");


        View v = inflater.inflate(R.layout.gallery_details_fragment, container, false);

        mImages = (String[]) this.getArguments().get("images");
        position = (int) this.getArguments().get("pos");


        pager = (ViewPager) v.findViewById(R.id.viewPager);
//        btn = (Button) v.findViewById(R.id.btn);
        adapter = new GalleryPagerAdapter((NavActivity)getActivity(), mImages, position);

        pager.setAdapter(adapter);
        pager.setCurrentItem(position);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                GalleryItemShowFragment fragment = new GalleryItemShowFragment();
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
//            }
//        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return v;
    }
}
