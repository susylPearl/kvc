package com.osahub.rachit.navdrawertemplate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment1;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment2;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment3;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment4;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment5;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment6;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment7;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment8;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment9;

/**
 * Created by sushil on 1/25/16.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
        int mNumOfTabs;


    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1();
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2();
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3();
                return tab3;
            case 3:
                TabFragment4 tab4 = new TabFragment4();
                return  tab4;
            case 4:
                TabFragment5 tab5 = new TabFragment5();
                return  tab5;
            case 5:
                TabFragment6 tab6 = new TabFragment6();
                return tab6;
            case 6:
                TabFragment7 tab7 = new TabFragment7();
                return tab7;
            case 7:
                TabFragment8 tab8 = new TabFragment8();
                return  tab8;
            case 8:
                TabFragment9 tab9 = new TabFragment9();
                return  tab9;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
