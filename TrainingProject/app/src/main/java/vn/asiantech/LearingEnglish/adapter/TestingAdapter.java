package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.LearingEnglish.fragments.FragmentPageTesting;

/**
 * Created by ThanhITBK on 9/4/2015.
 */
public class TestingAdapter extends FragmentPagerAdapter {
    public TestingAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

   public static final int ITEMS = 10;

    @Override
    public int getCount() {
        return ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentPageTesting.init(position);
    }
}
