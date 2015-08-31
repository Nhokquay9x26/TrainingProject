package vn.asiantech.LearingEnglish.activities;

import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentByTag;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;
import vn.asiantech.LearingEnglish.fragments.SplashFragment;
import vn.asiantech.LearingEnglish.fragments.SplashFragment_;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/26/15.
 */
@EActivity(R.layout.activity_initialize)
public class InitializeActivity extends BaseActionBarActivity {

    @FragmentByTag ("SplashFragment")
    protected SplashFragment mSplashFragment;

    @Override
    void afterView() {
        if (mSplashFragment == null){
            mSplashFragment = SplashFragment_.builder().build();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameLayout, mSplashFragment, "SplashFragment");
        ft.commit();
    }

    public void replaceFragment(Fragment layoutID){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, layoutID);
        ft.commit();
    }

}
