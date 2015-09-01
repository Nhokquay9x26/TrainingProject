package vn.asiantech.LearingEnglish.fragments;

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
    }

    @Click(R.id.btnTest)
    protected void onClick() {
        PhuQuyFragment fragment = PhuQuyFragment_.builder().build();
        addChildFragment(fragment);
    }
}
