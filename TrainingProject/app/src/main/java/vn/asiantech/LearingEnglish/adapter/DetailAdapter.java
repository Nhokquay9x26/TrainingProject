package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.LearingEnglish.fragments.DetailPagerFragment;

/**
 * @author DaoQuocViet
 * Created by nhokquay9x26 on 11/09/2015.
 */
public class DetailAdapter extends FragmentPagerAdapter {
    DetailPagerFragment fragment;

    public DetailAdapter(FragmentManager fm, DetailPagerFragment fragment) {
        super(fm);
        this.fragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
