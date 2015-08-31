package vn.asiantech.LearingEnglish.fragments;

import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.InitializeActivity_;
import vn.asiantech.LearingEnglish.activities.MainActivity_;
import vn.asiantech.LearingEnglish.models.HomePageItem;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/26/15.
 */
@EFragment(R.layout.fragment_splash)
public class SplashFragment extends BaseFragment {

    @ViewById(R.id.btnMainActivity)
    Button mBtnMainActivity;

    @FragmentByTag("LoginFragment")
    protected LoginFragment mLoginFragment;

    @AfterViews
    public void afterViews() {
        addChildFragment(LoginFragment_.builder().build());
    }

    @Click(R.id.btnMainActivity)
    protected void onClick() {
        if (mLoginFragment == null){
            mLoginFragment = LoginFragment_.builder().build();
        }
        ((InitializeActivity_)getActivity()).replaceFragment(mLoginFragment);
    }
}
