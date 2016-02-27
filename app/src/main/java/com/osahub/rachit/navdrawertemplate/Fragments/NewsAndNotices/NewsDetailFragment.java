package com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.R;

/**
 * Created by sushil on 1/28/16.
 */
public class NewsDetailFragment extends android.support.v4.app.Fragment {

    String title;
    String description;
    String date;
   TextView textView1, date_text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        View v = inflater.inflate(R.layout.tab_fragment_6, container, false);

        title = (String) this.getArguments().get("title");
        description = (String) this.getArguments().get("description");
        date = (String) this.getArguments().get("date");


        textView1 = (TextView) v.findViewById(R.id.textView);
        date_text = (TextView) v.findViewById(R.id.date_text);

        textView1.setText(title);
        date_text.setText(date);

        WebView webView = (WebView) v.findViewById(R.id.webView1);
        webView.loadDataWithBaseURL("file:///android_asset/","<p style=\"text-align: justify\">"+ description +"</p>", "text/html", null, null);
        webView.getSettings().setDomStorageEnabled(true);

        return v;
    }


}
