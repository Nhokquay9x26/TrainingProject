package vn.asiantech.LearingEnglish.fragments;

import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;

/**
 * Created by nhokquay9x26 on 27/08/2015.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends BaseFragment {
    @ViewById(R.id.btnSignin)
    Button mBtnLogin;

    @AfterViews
    public void AfterViews() {
    }

    @Click(R.id.btnSignin)
    protected void onClick(){
        MainActivity_.intent(getActivity()).start();
        getActivity().finish();
    }
}
