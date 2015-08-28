package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.app.FragmentTransaction;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/26/15.
 */
@EFragment(R.layout.fragment_splash)
public class SplashFragment extends BaseFragment {

    @ViewById(R.id.btnMainActivity)
    Button mBtnMainActivity;

    @FragmentByTag("SignUpFragment")
    SignUpFragment mSignUpFragment;

    @AfterViews
    public void afterViews() {
    }

    @Click(R.id.btnMainActivity)
    protected void onClick() {
        if (mSignUpFragment == null) {
            mSignUpFragment = SignupFragment_.builder().build();
        }
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frameLayout, mSignUpFragment, "MainFragment");
        ft.commit();
    }

}
