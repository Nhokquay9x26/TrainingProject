package vn.asiantech.LearingEnglish.fragments;

import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by chanh on 11/09/2015.
 */

@EFragment(R.layout.fragment_result)
public class ResultFragment extends BaseFragment {

    @ViewById(R.id.imgBackTest)
    ImageView mImgBackTest;
    @FragmentByTag("TestFragment")
    TestFragment mTestFragment;

    @AfterViews
    public void AfterViews() {
    }

    @Click(R.id.btnSignIn)
    public void onResult() {

    }
}
