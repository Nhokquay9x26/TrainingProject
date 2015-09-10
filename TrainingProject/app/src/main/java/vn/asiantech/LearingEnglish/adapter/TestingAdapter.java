package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.FragmentPageTesting;
import vn.asiantech.LearingEnglish.fragments.FragmentPageTesting_;

/**
 * Created by ThanhITBK on 9/4/2015.
 */
public class TestingAdapter extends FragmentPagerAdapter {
    public static int ITEMS;
    private FragmentPageTesting mFragmentPageTesting;
    private OnChangePager mOnChangePager;

    public TestingAdapter(FragmentManager fragmentManager, FragmentPageTesting_ fragmentPageTesting,
                          OnChangePager onChangePager, int countQuestion) {
        super(fragmentManager);
        this.mFragmentPageTesting = fragmentPageTesting;
        mOnChangePager = onChangePager;
        this.ITEMS = countQuestion;
    }

    @Override
    public int getCount() {
        return ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentPageTesting.init(position, mOnChangePager);
    }

    public interface OnChangePager {
        void onChange(int i);
    }


}
