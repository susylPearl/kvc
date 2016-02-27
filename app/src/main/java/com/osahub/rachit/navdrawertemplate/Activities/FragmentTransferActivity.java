package com.osahub.rachit.navdrawertemplate.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.osahub.rachit.navdrawertemplate.Fragments.ContactFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.EventsActivities.EventsFragments;
import com.osahub.rachit.navdrawertemplate.Fragments.Gallery.ImageGalleryFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices.NewsFragments;
import com.osahub.rachit.navdrawertemplate.R;

public class FragmentTransferActivity extends AppCompatActivity {

    private int position;
    android.support.v4.app.FragmentManager fragmentManager;
    android.support.v4.app.FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        position = getIntent().getIntExtra("pos", 0);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (position){
            case 0:
                NewsFragments newsFragments = new NewsFragments();
                getSupportActionBar().setTitle("KVC News and Notices");
                fragmentTransaction.replace(R.id.navContainer, newsFragments);
                break;
            case 1:
                EventsFragments eventsFragments = new EventsFragments();
                getSupportActionBar().setTitle("KVC Events and Activities");
                fragmentTransaction.replace(R.id.navContainer, eventsFragments);
                break;
            case 2:
                ImageGalleryFragment imageGalleryFragment = new ImageGalleryFragment();
                getSupportActionBar().setTitle("KVC Gallery");
                fragmentTransaction.replace(R.id.navContainer, imageGalleryFragment);
                break;
            case 3:
                ContactFragment contactFragment = new ContactFragment();
                getSupportActionBar().setTitle("KVC Contact");
                fragmentTransaction.replace(R.id.navContainer, contactFragment);
                break;

        }
        fragmentTransaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
        return (super.onOptionsItemSelected(item));
    }


}
