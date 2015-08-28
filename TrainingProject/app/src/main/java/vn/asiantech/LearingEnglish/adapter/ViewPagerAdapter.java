package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.LearingEnglish.fragments.FavoriteFragment_;
import vn.asiantech.LearingEnglish.fragments.SettingFragment_;
import vn.asiantech.LearingEnglish.fragments.TestFragment_;
import vn.asiantech.LearingEnglish.fragments.TopFragment_;

/**
 * Created by hairom on 28/08/2015.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position ==0){
            fragment = new TopFragment_();

        }
        if (position == 1){

            fragment = new FavoriteFragment_();
        }
        if (position == 2){

            fragment = new TestFragment_();
        }
        if (position == 3){

            fragment = new SettingFragment_();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
