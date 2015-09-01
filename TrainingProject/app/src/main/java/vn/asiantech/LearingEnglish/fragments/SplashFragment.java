package vn.asiantech.LearingEnglish.fragments;

import android.util.Log;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
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

    @AfterViews
    public void afterViews() {
    }

    @Click(R.id.btnMainActivity)
    protected void onClick() {
//        MainActivity_.intent(getActivity()).start();
//        getActivity().finish();
        // TODO: 9/1/15
        Log.d("xxx",""+getView().getId());
        addChildFragment(TestFragment_.builder().build());
    }

}
