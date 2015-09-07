package vn.asiantech.LearingEnglish.fragments;

import android.os.Handler;
import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;
import vn.asiantech.LearingEnglish.network.core.ApiClient;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/26/15.
 */
@EFragment(R.layout.fragment_splash)
public class SplashFragment extends BaseFragment {

    @FragmentByTag("TutorialFragment")
    protected TutorialFragment mTutorialFragment;

    @AfterViews
    public void afterViews() {
        if (mTutorialFragment == null){
            mTutorialFragment = TutorialFragment_.builder().build();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ApiClient.getInstance().isAuthenticated()) {
                    MainActivity_.intent(getActivity()).start();
                } else {
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.frameLayout, mTutorialFragment, "TutorialFragment");
                    ft.commit();
                }
            }
        }, 0);
    }

}
