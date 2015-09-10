package vn.asiantech.LearingEnglish.fragments;

import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.InitializeActivity_;
import vn.asiantech.LearingEnglish.activities.MainActivity_;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 9/4/15.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends BaseFragment {
    @FragmentByTag("SignUpFragment")
    protected SignUpFragment mSignUpFragment;

    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.tvError)
    TextView mTvError;

    @Click(R.id.btnSignIn)
    public void onSignIn() {
        String username = mEdtUsername.getText().toString();
        String password = mEdtPassword.getText().toString();
        if (username.equals("") || password.equals("")) {
            mTvError.setText(getResources().getString(R.string.textview_text_error_empty));
        } else if (username.equals("admin") && password.equals("admin")) {
            MainActivity_.intent(getActivity()).start();
            getActivity().finish();
        } else {
            mTvError.setText(getResources().getString(R.string.textview_text_error));
        }
    }

    @Click(R.id.btnSignUp)
    public void onSignUp() {
        if (mSignUpFragment == null) {
            mSignUpFragment = SignUpFragment_.builder().build();
        }
        ((InitializeActivity_) getActivity()).replaceFragment(mSignUpFragment);
    }
}