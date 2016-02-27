package com.osahub.rachit.navdrawertemplate.Activities;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.AboutUsFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.ContactFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.DashBoardFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.EventsActivities.EventsFragments;
import com.osahub.rachit.navdrawertemplate.Fragments.HomeFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.Gallery.ImageGalleryFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.NavigationDrawerFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.NewsAndNotices.NewsFragments;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.StudentServiceFragment;
import com.osahub.rachit.navdrawertemplate.Interfaces.DashBoardCallbacks;
import com.osahub.rachit.navdrawertemplate.Interfaces.NavigationDrawerCallbacks;
import com.osahub.rachit.navdrawertemplate.R;

import java.lang.ref.WeakReference;


public class MainActivity extends AppCompatActivity
        implements NavigationDrawerCallbacks, DashBoardCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     *
     */

    private static WeakReference<MainActivity> wrActivity = null;
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    int position;
    private byte backPresCount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wrActivity = new WeakReference<MainActivity>(this);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);

        position = this.getIntent().getIntExtra("pos", 0);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
//        mNavigationDrawerFragment.setUserData("Kathmandu Valley College", "info@kvc.edu.np", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
//
//        switch (position){
//            case 0:
//                NewsFragments newsFragments = new NewsFragments();
//                fragmentTransaction.replace(R.id.container, newsFragments);
//                break;
//            case 1:
//                EventsFragments eventsFragments = new EventsFragments();
//                fragmentTransaction.replace(R.id.container, eventsFragments);
//                break;
//            case 2:
//                ImageGalleryFragment imageGalleryFragment = new ImageGalleryFragment();
//                fragmentTransaction.replace(R.id.container, imageGalleryFragment);
//                break;
//            case 3:
//                ContactFragment contactFragment = new ContactFragment();
//                fragmentTransaction.replace(R.id.container, contactFragment);
//                break;
//
//        }

    }

    public void setActionBarTitle(String title){
        mToolbar.setTitle(title);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Intent intent = new Intent(this, NavActivity.class);


            if (position == 0) {

                    DashBoardFragment dashBoardFragment = new DashBoardFragment();
                    getSupportActionBar().setTitle("KVC Home");
                    fragmentTransaction.replace(R.id.container, dashBoardFragment);

            }
            else {
                intent.putExtra("pos", position);
                startActivity(intent);
            }
        fragmentTransaction.commit();


        }




    @Override
    public void onBackPressed() {

//        if (getSupportFragmentManager().getBackStackEntryCount() == 0){
//
//                          super.onBackPressed();
//
//        }
//        else {
////            if (mNavigationDrawerFragment.isDrawerOpen())
//                mNavigationDrawerFragment.closeDrawer();
//                    getSupportFragmentManager().popBackStack();
//        }
        if(backPresCount<1){

                mNavigationDrawerFragment.closeDrawer();
            backPresCount++;
            Toast.makeText(this, "Press the back button once again to close the application", Toast.LENGTH_SHORT).show();
        }else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
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



    @Override
    public void onDashBoardItemSelected(int position) {


        Intent intent = new Intent(this, FragmentTransferActivity.class);
        intent.putExtra("pos", position);
        startActivity(intent);

//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        if (position == 0 ){
//            NewsFragments newsFragments = new NewsFragments();
//            fragmentTransaction.replace(R.id.container, newsFragments);
//
//        }else if (position == 1){
//            EventsFragments eventsFragments = new EventsFragments();
//            fragmentTransaction.replace(R.id.container, eventsFragments);
//
//        }else if (position == 2){
//            ImageGalleryFragment imageGalleryFragment = new ImageGalleryFragment();
//            fragmentTransaction.replace(R.id.container, imageGalleryFragment);
//
//        }else if (position == 3){
//            ContactFragment contactFragment = new ContactFragment();
//            fragmentTransaction.replace(R.id.container, contactFragment);
//
//        }
//        fragmentTransaction.commit();

    }

}
