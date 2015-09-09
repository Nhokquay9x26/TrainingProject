package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.LearingEnglish.fragments.FragmentAnswer;
import vn.asiantech.LearingEnglish.fragments.FragmentPageTesting;

/**
 * Created by ThanhITBK on 9/4/2015.
 */
public class TestingAdapter extends FragmentPagerAdapter {
    FragmentPageTesting fragment;
    private OnChangePager mOnChangePager;

    public TestingAdapter(FragmentManager fragmentManager, FragmentPageTesting fragment, OnChangePager onChangePager) {
        super(fragmentManager);
        this.fragment = fragment;
        mOnChangePager = onChangePager;
    }

    public static final int ITEMS = 5;

    @Override
    public int getCount() {
        return ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.init(position, mOnChangePager);
    }

    public interface OnChangePager {
        void onChange(int i);
    }
}
