package com.osahub.rachit.navdrawertemplate.Classes;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;

import com.osahub.rachit.navdrawertemplate.R;

/**
 * Created by Su syl on 7/14/2015.
 */
public class CountDown extends CountDownTimer {

    private Activity Activity_act;
    private Class _cls;

    public CountDown(long millisInFuture, long countDownInterval,Activity act,Class cls) {
        super(millisInFuture, countDownInterval);
        Activity_act=act;
        _cls=cls;
    }
    @Override
    public void onFinish() {
        Activity_act.startActivity(new Intent(Activity_act,_cls));
        Activity_act.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);

        Activity_act.finish();

    }
    @Override
    public void onTick(long millisUntilFinished) {

    }


}
