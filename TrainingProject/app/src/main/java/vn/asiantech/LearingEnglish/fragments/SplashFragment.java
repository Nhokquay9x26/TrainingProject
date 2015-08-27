package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.PagerAdapterSplash;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/26/15.
 */
@EFragment(R.layout.fragment_splash)
public class SplashFragment extends BaseFragment {

   /* @ViewById(R.id.btnMainActivity)
    Button mBtnMainActivity;*/
    @ViewById(R.id.viewpager_default)
    ViewPager mViewPager;


    @AfterViews
    public void afterViews() {
        PagerAdapterSplash pagerAdapterSplash=new PagerAdapterSplash(getFragmentManager());
        mViewPager.setAdapter(pagerAdapterSplash);
    }

    /*@Click(R.id.btnMainActivity)
    protected void onClick() {
        MainActivity_.intent(getActivity()).start();
        getActivity().finish();
    }*/

}
