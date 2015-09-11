package vn.asiantech.LearingEnglish.activities;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentByTag;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.SplashFragment;
import vn.asiantech.LearingEnglish.fragments.SplashFragment_;
import vn.asiantech.LearingEnglish.fragments.TutorialFragment;
import vn.asiantech.LearingEnglish.fragments.TutorialFragment_;
import vn.asiantech.LearingEnglish.network.core.ApiClient;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/26/15.
 */
@EActivity(R.layout.activity_initialize)
public class InitializeActivity extends BaseActionBarActivity {

    @FragmentByTag("SplashFragment")
    protected SplashFragment mSplashFragment;

    @FragmentByTag("TutorialFragment")
    protected TutorialFragment mTutorialFragment;

    @Override
    void afterView() {
        if (mSplashFragment == null) {
            mSplashFragment = SplashFragment_.builder().build();
        }

        if (mTutorialFragment == null){
            mTutorialFragment = TutorialFragment_.builder().build();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameLayout, mSplashFragment, "SplashFragment");
        ft.commit();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ApiClient.getInstance().isAuthenticated()) {
                    MainActivity_.intent(InitializeActivity.this).start();
                } else {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayout, mTutorialFragment, "TutorialFragment");
                    ft.commit();
                }
            }
        }, 0);
    }

    @Override
    public void onBackPressed() {
        if (mTutorialFragment != null) {
            if (!mTutorialFragment.sendBackPressed()) {
                super.onBackPressed();
            }
        }
    }
}
