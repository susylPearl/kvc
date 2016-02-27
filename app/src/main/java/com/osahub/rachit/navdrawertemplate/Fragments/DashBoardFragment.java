package com.osahub.rachit.navdrawertemplate.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.osahub.rachit.navdrawertemplate.Activities.FragmentTransferActivity;
import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Activities.NavActivity;
import com.osahub.rachit.navdrawertemplate.Fragments.EventsActivities.EventsFragments;
import com.osahub.rachit.navdrawertemplate.Fragments.Gallery.ImageGalleryFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices.NewsFragments;
import com.osahub.rachit.navdrawertemplate.Interfaces.DashBoardCallbacks;
import com.osahub.rachit.navdrawertemplate.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sushil on 2/10/16.
 */
public class DashBoardFragment extends Fragment implements View.OnClickListener {

    DashBoardCallbacks dashBoardCallbacks;
    View rootView;
    private SliderLayout mDemoSlider;
    NavigationDrawerFragment mNavigationDrawerFragment;
    ImageView mImageView1, mImageView2, mImageView3, mImageView4;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DashBoardCallbacks callbacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        rootView = inflater.inflate(R.layout.dashboard, container, false);

        mDemoSlider = (SliderLayout) rootView.findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("College Environment", R.drawable.college);
        file_maps.put("Student", R.drawable.banner);
        file_maps.put("Tour", R.drawable.banner1);
        file_maps.put("Study", R.drawable.banner2);
        file_maps.put("School", R.drawable.banner3);
        file_maps.put("College", R.drawable.banner4);
        file_maps.put("Management", R.drawable.banner5);
        file_maps.put("Science", R.drawable.banner6);


        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView.description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);

        mImageView1 = (ImageView) rootView.findViewById(R.id.first);
        mImageView2 = (ImageView) rootView.findViewById(R.id.second);
        mImageView3 = (ImageView) rootView.findViewById(R.id.third);
        mImageView4 = (ImageView) rootView.findViewById(R.id.fourth);

        mImageView1.setOnClickListener(this);
        mImageView2.setOnClickListener(this);
        mImageView3.setOnClickListener(this);
        mImageView4.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dashBoardCallbacks = (DashBoardCallbacks) activity;

    }

    @Override
    public void onClick(View view) {
        if (view == mImageView1){
           dashBoardCallbacks.onDashBoardItemSelected(0);
        }else if (view == mImageView2){
            dashBoardCallbacks.onDashBoardItemSelected(1);
        }else if (view == mImageView3){
            dashBoardCallbacks.onDashBoardItemSelected(2);
        }else if (view == mImageView4){
            dashBoardCallbacks.onDashBoardItemSelected(3);
        }
    }




}
