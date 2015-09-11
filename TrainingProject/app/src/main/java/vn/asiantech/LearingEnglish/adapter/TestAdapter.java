package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.LearingEnglish.fragments.TestPagerFragment;

/**
 * Created by chanh on 09/09/2015.
 */

public class TestAdapter extends FragmentPagerAdapter {

    TestPagerFragment fragment;
    private OnChangePager mOnChangePager;

    public TestAdapter(FragmentManager fm, TestPagerFragment fragment,
                       OnChangePager onChangePager) {
        super(fm);
        this.fragment = fragment;
        mOnChangePager = onChangePager;
    }

    public static final int ITEMS = 10;

    @Override
    public Fragment getItem(int position) {
        return fragment.init(position, mOnChangePager);
    }

    @Override
    public int getCount() {
        return ITEMS;
    }

    public interface OnChangePager {
        void onChange(String direction);
    }
}
