package com.osahub.rachit.navdrawertemplate.Fragments.EventsActivities;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices.NewsFragments;
import com.osahub.rachit.navdrawertemplate.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sushil on 1/28/16.
 */
public class EventsDetailFragment extends android.support.v4.app.Fragment {

    String title;
    String description;
    String imageUrl;
    String date;
    TextView textView;
    TextView date_text;
    ImageView imageView;
    Button backBtn;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.tab_fragment_8, container, false);

        title = (String) this.getArguments().get("title");
        description = (String) this.getArguments().get("description");
        imageUrl = (String) this.getArguments().get("attach");
        date = (String) this.getArguments().get("date");


        textView = (TextView) v.findViewById(R.id.textView);
        date_text = (TextView) v.findViewById(R.id.date);
        imageView = (ImageView) v.findViewById(R.id.attach);

        textView.setText(title);
        date_text.setText(date);
        Picasso.with(getActivity()).load(imageUrl).resize(500, 500).into(imageView);

        WebView webView = (WebView) v.findViewById(R.id.webView1);
        webView.loadDataWithBaseURL("file:///android_asset/","<p style=\"text-align: justify\">"+ description +"</p>", "text/html", null, null);
        webView.getSettings().setDomStorageEnabled(true);

        return v;
    }
}
