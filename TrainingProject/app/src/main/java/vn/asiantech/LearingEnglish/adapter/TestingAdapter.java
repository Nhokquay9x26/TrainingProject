package vn.asiantech.LearingEnglish.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.FragmentPageTesting;

/**
 * Created by ThanhITBK on 9/4/2015.
 */
public class TestingAdapter extends FragmentPagerAdapter {
    public static int ITEMS ;
    FragmentPageTesting fragment;
    private OnChangePager mOnChangePager;

    public TestingAdapter(FragmentManager fragmentManager, FragmentPageTesting fragment,
                          OnChangePager onChangePager,int countQuestion) {
        super(fragmentManager);
        this.fragment = fragment;
        mOnChangePager = onChangePager;
        this.ITEMS=countQuestion;
    }
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
