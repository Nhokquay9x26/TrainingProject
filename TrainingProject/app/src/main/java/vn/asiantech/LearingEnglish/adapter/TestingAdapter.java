package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by tantv on 28/08/2015.
 */

public class TestingAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mTestingFragment;
    public TestingAdapter(FragmentManager fm,ArrayList<Fragment> testingFragment) {
        super(fm);
        this.mTestingFragment = testingFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mTestingFragment.get(position);
    }

    @Override
    public int getCount() {
        return mTestingFragment.size();
    }


}
