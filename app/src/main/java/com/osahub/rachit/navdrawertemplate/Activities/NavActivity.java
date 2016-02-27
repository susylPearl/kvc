package com.osahub.rachit.navdrawertemplate.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.osahub.rachit.navdrawertemplate.Adapters.EventsFragmentAdapter;
import com.osahub.rachit.navdrawertemplate.Adapters.ImageGalleryAdapter;
import com.osahub.rachit.navdrawertemplate.Adapters.NewsFragmentAdapter;
import com.osahub.rachit.navdrawertemplate.Adapters.PhotoAdapter;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.AboutUsFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.AcademicsFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Management1;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Management2;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Management3;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Management4;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Management5;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Science1;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Science2;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Science3;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Science4;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.Science5;
import com.osahub.rachit.navdrawertemplate.Fragments.ContactFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.DashBoardFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.EventsActivities.EventsDetailFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.EventsActivities.EventsFragments;
import com.osahub.rachit.navdrawertemplate.Fragments.Gallery.GalleryDetailsFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.Gallery.GalleryItemShowFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.Gallery.ImageGalleryFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices.NewsDetailFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices.NewsFragments;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.FacilitiesFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.StudentServiceFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab1Fragment1;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab2Fragment2;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab3Fragment3;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab4Fragment4;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab5Fragment5;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab6Fragment6;
import com.osahub.rachit.navdrawertemplate.R;

public class NavActivity extends AppCompatActivity {

    int position;
    String mNewsTitle, mNewsDescription, mNewsDate;
    String mEventsAttach;
    String[] mGalleryPhotos;
    int photoId;

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
        mNewsTitle = getIntent().getStringExtra("title");
        mNewsDescription = getIntent().getStringExtra("description");
        mNewsDate = getIntent().getStringExtra("date");
        mEventsAttach = getIntent().getStringExtra("attach");
        mGalleryPhotos = getIntent().getStringArrayExtra("images");
        photoId = getIntent().getIntExtra("id", 0);



        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (position){


            case 1:
                AboutUsFragment testFragment2 = new AboutUsFragment();
                getSupportActionBar().setTitle("KVC About");
                fragmentTransaction.replace(R.id.navContainer, testFragment2);
                fragmentTransaction.commit();
                break;
            case 2:
                AcademicsFragment academicsFragment = new AcademicsFragment();
                getSupportActionBar().setTitle("KVC Academics");
                fragmentTransaction.replace(R.id.navContainer, academicsFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                StudentServiceFragment studentServiceFragment = new StudentServiceFragment();
                getSupportActionBar().setTitle("KVC Student Service");
                fragmentTransaction.replace(R.id.navContainer, studentServiceFragment);
                fragmentTransaction.commit();
                break;
            case 4:
                NewsFragments newsFragments = new NewsFragments();
                getSupportActionBar().setTitle("KVC News and Notices");
                fragmentTransaction.replace(R.id.navContainer, newsFragments);
                fragmentTransaction.commit();
                break;
            case 5:
                EventsFragments eventsFragments = new EventsFragments();
                getSupportActionBar().setTitle("KVC Events and Activities");
                fragmentTransaction.replace(R.id.navContainer, eventsFragments);
                fragmentTransaction.commit();
                break;
            case 6:
                ImageGalleryFragment imageGalleryFragment = new ImageGalleryFragment();
                getSupportActionBar().setTitle("KVC Gallery");
                fragmentTransaction.replace(R.id.navContainer, imageGalleryFragment);
                fragmentTransaction.commit();
                break;
            case 7:
                ContactFragment contactFragment = new ContactFragment();
                getSupportActionBar().setTitle("KVC Contact");
                fragmentTransaction.replace(R.id.navContainer, contactFragment);
                fragmentTransaction.commit();
                break;

            case 10:
            Tab1Fragment1 tab1Fragment1 = new Tab1Fragment1();
                getSupportActionBar().setTitle("Library");
                fragmentTransaction.replace(R.id.navContainer, tab1Fragment1);
                fragmentTransaction.commit();
                break;

            case 11:
                Tab2Fragment2 tab2Fragment2 = new Tab2Fragment2();
                getSupportActionBar().setTitle("College Cafeteria");
                fragmentTransaction.replace(R.id.navContainer, tab2Fragment2);
                fragmentTransaction.commit();
                break;

            case 12:
                Tab3Fragment3 tab3Fragment3 = new Tab3Fragment3();
                getSupportActionBar().setTitle("Teaching Method");
                fragmentTransaction.replace(R.id.navContainer, tab3Fragment3);
                fragmentTransaction.commit();
                break;

            case 13:
                Tab4Fragment4 tab4Fragment4 = new Tab4Fragment4();
                getSupportActionBar().setTitle("Sports");
                fragmentTransaction.replace(R.id.navContainer, tab4Fragment4);
                fragmentTransaction.commit();
                break;

            case 14:
                Tab5Fragment5 tab5Fragment5 = new Tab5Fragment5();
                getSupportActionBar().setTitle("Computer Lab");
                fragmentTransaction.replace(R.id.navContainer, tab5Fragment5);
                fragmentTransaction.commit();
                break;

            case 15:
                Tab6Fragment6 tab6Fragment6 = new Tab6Fragment6();
                getSupportActionBar().setTitle("Science Lab");
                fragmentTransaction.replace(R.id.navContainer, tab6Fragment6);
                fragmentTransaction.commit();
                break;

            case 16:
                Science1 science1 = new Science1();
                getSupportActionBar().setTitle("About Programs");
                fragmentTransaction.replace(R.id.navContainer, science1);
                fragmentTransaction.commit();
                break;

            case 17:
                Science2 science2 = new Science2();
                getSupportActionBar().setTitle("Admission Procedure");
                fragmentTransaction.replace(R.id.navContainer, science2);
                fragmentTransaction.commit();
                break;

            case 18:
                Science3 science3 = new Science3();
                getSupportActionBar().setTitle("Scholorship");
                fragmentTransaction.replace(R.id.navContainer, science3);
                fragmentTransaction.commit();
                break;

            case 19:
                Science4 science4 = new Science4();
                getSupportActionBar().setTitle("Curriculum");
                fragmentTransaction.replace(R.id.navContainer,science4);
                fragmentTransaction.commit();
                break;

            case 20:
                Science5 science5 = new Science5();
                getSupportActionBar().setTitle("Rules and Regulations");
                fragmentTransaction.replace(R.id.navContainer, science5);
                fragmentTransaction.commit();
                break;


            case 21:
                Management1 management1 = new Management1();
                getSupportActionBar().setTitle("About Programs");
                fragmentTransaction.replace(R.id.navContainer, management1);
                fragmentTransaction.commit();
                break;

            case 22:
                Management2 management2 = new Management2();
                getSupportActionBar().setTitle("Admission Procedure");
                fragmentTransaction.replace(R.id.navContainer, management2);
                fragmentTransaction.commit();
                break;

            case 23:
                Management3 management3 = new Management3();
                getSupportActionBar().setTitle("Scholorship");
                fragmentTransaction.replace(R.id.navContainer, management3);
                fragmentTransaction.commit();
                break;

            case 24:
                Management4 management4 = new Management4();
                getSupportActionBar().setTitle("Curriculum");
                fragmentTransaction.replace(R.id.navContainer, management4);
                fragmentTransaction.commit();
                break;

            case 25:
                Management5 management5 = new Management5();
                getSupportActionBar().setTitle("Rules and Regulations");
                fragmentTransaction.replace(R.id.navContainer, management5);
                fragmentTransaction.commit();
                break;

            case NewsFragmentAdapter.newsCase:

                getSupportActionBar().setTitle("KVC News and Notices");
                NewsDetailFragment detailFragment = new NewsDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", mNewsTitle);
                bundle.putString("description", mNewsDescription);
                bundle.putString("date", mNewsDate);
                detailFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.navContainer, detailFragment);
                fragmentTransaction.commit();
                break;

            case EventsFragmentAdapter.eventsCase:

                getSupportActionBar().setTitle("KVC Events and Activities");
                EventsDetailFragment detailFragment1 = new EventsDetailFragment();

                Bundle bundle1 = new Bundle();
                bundle1.putString("title", mNewsTitle);
                bundle1.putString("description", mNewsDescription);
                bundle1.putString("date", mNewsDate);
                bundle1.putString("attach", mEventsAttach);
                detailFragment1.setArguments(bundle1);

                fragmentTransaction.replace(R.id.navContainer, detailFragment1);
                fragmentTransaction.commit();
                break;

            case ImageGalleryAdapter.galleryCase:

                Bundle bundle2 = new Bundle();
                bundle2.putStringArray("images", mGalleryPhotos);
                bundle2.putInt("id", photoId);

                GalleryItemShowFragment galleryItemShowFragment = new GalleryItemShowFragment();
                galleryItemShowFragment.setArguments(bundle2);
                fragmentTransaction.replace(R.id.navContainer, galleryItemShowFragment).commit();
                break;

            case PhotoAdapter.photoCase:


                Bundle bundle3 = new Bundle();
                bundle3.putStringArray("images", mGalleryPhotos);
                bundle3.putInt("pos", photoId);

                GalleryDetailsFragment galleryDetailsFragment = new GalleryDetailsFragment();
                galleryDetailsFragment.setArguments(bundle3);
                fragmentTransaction.replace(R.id.navContainer, galleryDetailsFragment).commit();
                break;




        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;
        }
    return (super.onOptionsItemSelected(item));
    }
}
