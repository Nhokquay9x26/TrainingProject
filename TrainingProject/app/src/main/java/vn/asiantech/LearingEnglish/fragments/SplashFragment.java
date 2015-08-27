package vn.asiantech.LearingEnglish.fragments;

import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/26/15.
 */
@EFragment(R.layout.fragment_signup)
public class SplashFragment extends BaseFragment {

    @ViewById(R.id.btnMainActivity)
    Button mBtnMainActivity;

    @AfterViews
    public void afterViews() {
    }

    @Click(R.id.btnSignup)
    protected void onClick() {
        MainActivity_.intent(getActivity()).start();
        //getActivity().finish();
    }

}
