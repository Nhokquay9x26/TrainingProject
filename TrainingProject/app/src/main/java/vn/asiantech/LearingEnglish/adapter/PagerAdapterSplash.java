package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import vn.asiantech.LearingEnglish.fragments.SplashOneFragment;
import vn.asiantech.LearingEnglish.fragments.SplashTwoFragment;

/**
 * Created by vanteo89 on 27/08/2015.
 */
public class PagerAdapterSplash extends FragmentPagerAdapter {
    public PagerAdapterSplash(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new SplashOneFragment();
            case 1:
                return new SplashTwoFragment();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 2;
    }
}
