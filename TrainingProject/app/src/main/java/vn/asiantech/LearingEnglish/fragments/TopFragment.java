package vn.asiantech.LearingEnglish.fragments;

import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import vn.asiantech.LearingEnglish.R;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
@EFragment(R.layout.fragment_top)
public class TopFragment extends BaseFragment {
    @AfterViews
    public void init() {
        Toast.makeText(getActivity(), "jsdnfkjgnsd", Toast.LENGTH_SHORT).show();
    }

    @Click(R.id.btnTest)
    protected void onClick() {
        addChildFragment(SettingFragment_.builder().build());
    }
}
