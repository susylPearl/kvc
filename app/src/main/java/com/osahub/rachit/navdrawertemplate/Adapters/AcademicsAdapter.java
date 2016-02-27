package com.osahub.rachit.navdrawertemplate.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment1;
import com.osahub.rachit.navdrawertemplate.Fragments.AboutUs.TabFragment2;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.ManagemenntFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.Academics.ScienceFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.FacilitiesFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.StudentClubFragment;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab1Fragment1;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab2Fragment2;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab3Fragment3;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab4Fragment4;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab5Fragment5;
import com.osahub.rachit.navdrawertemplate.Fragments.StudentService.Tab6Fragment6;

/**
 * Created by sushil on 1/26/16.
 */
public class AcademicsAdapter extends FragmentStatePagerAdapter {

    int mNumOfTabs;
    public AcademicsAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ScienceFragment tab1 = new ScienceFragment();
                return tab1;
            case 1:
                ManagemenntFragment tab2 = new ManagemenntFragment();
                return tab2;
//            case 2:
//                Tab3Fragment3 tab3 = new Tab3Fragment3();
//                return tab3;
//            case 3:
//                Tab4Fragment4 tab4 = new Tab4Fragment4();
//                return tab4;
//            case 4:
//                Tab5Fragment5 tab5 = new Tab5Fragment5();
//                return tab5;
//            case 5:
//                Tab6Fragment6 tab6 = new Tab6Fragment6();
//                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
