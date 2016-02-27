package com.osahub.rachit.navdrawertemplate.Classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.osahub.rachit.navdrawertemplate.R;

/**
 * Created by Su syl on 8/14/2015.
 */
public class CustomDialogClass extends Dialog{

    private Context c;
    public Dialog d;

    public CustomDialogClass(Activity a){
        super(a);
        this.c =a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog_screen);



    }


    }
