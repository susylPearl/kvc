package com.osahub.rachit.navdrawertemplate.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.osahub.rachit.navdrawertemplate.Activities.MainActivity;
import com.osahub.rachit.navdrawertemplate.Classes.CountDown;
import com.osahub.rachit.navdrawertemplate.R;


public class SplashScreenActivity extends AppCompatActivity {

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        CountDown _tik;
        _tik = new CountDown(5000, 5000, this, MainActivity.class);// It delay the screen for 5 second and after that switch to YourNextActivity
        _tik.start();


        StartAnimations();



    }


    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.rel_lay);
        l.clearAnimation();
        l.startAnimation(anim);

//        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
//        anim.reset();
//        ImageView iv = (ImageView) findViewById(R.id.imageView1);
//        TextView tv1 = (TextView) findViewById(R.id.textView1);
//        TextView tv2 = (TextView) findViewById(R.id.textView2);
//        iv.clearAnimation();
//        iv.startAnimation(anim);
//
//        tv1.clearAnimation();
//        tv1.startAnimation(anim);
//
//        tv2.clearAnimation();
//        tv2.startAnimation(anim);


    }


}