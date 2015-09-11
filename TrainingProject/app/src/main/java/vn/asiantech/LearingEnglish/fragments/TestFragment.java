package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.view.ViewPager;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;


import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;
import vn.asiantech.LearingEnglish.adapter.TestAdapter;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
@EFragment(R.layout.fragment_test)
public class TestFragment extends BaseFragment implements TestAdapter.OnChangePager {

    public static final String NEXT = "Next";
    public static final String BACK = "Back";
    static final int ITEMS = 10;

    @FragmentByTag("ResultFragment")
    ResultFragment mResultFragment;
    @ViewById(R.id.btnSubmit)
    Button mBtnSubmit;
    @ViewById(R.id.viewPagerTest)
    ViewPager mViewPager;

    @AfterViews
    public void init() {
        TestPagerFragment_ fragment = new TestPagerFragment_();
        TestAdapter adapter = new TestAdapter(getFragmentManager(), fragment, this);
        mViewPager.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onChange(String direction) {
        if (direction.equals(NEXT)) {
            if (mViewPager.getCurrentItem() != ITEMS - 1) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            } else {
                mViewPager.setCurrentItem(0);
            }
        }
        if (direction.equals(BACK)) {
            if (mViewPager.getCurrentItem() != 0) {
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);

            } else {
                mViewPager.setCurrentItem(ITEMS - 1);
            }
        }
    }

    @Click(R.id.btnSubmit)
    public void onClickSubmit() {
        if (mResultFragment == null) {
            addChildFragment(ResultFragment_.builder().build());
        }
    }
}
