package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.LearingEnglish.fragments.DetailPagerFragment;
import vn.asiantech.LearingEnglish.fragments.TestPagerFragment;

/**
 * @author DaoQuocViet
 * Created by nhokquay9x26 on 11/09/2015.
 */

public class DetailAdapter extends FragmentPagerAdapter {

    DetailPagerFragment fragment;
    private OnChangePager mOnChangePager;

    public DetailAdapter(FragmentManager fm, DetailPagerFragment fragment, OnChangePager onChangePager) {
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
