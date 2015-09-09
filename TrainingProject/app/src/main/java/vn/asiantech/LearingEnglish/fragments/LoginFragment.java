package vn.asiantech.LearingEnglish.fragments;

import android.widget.EditText;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 9/4/15.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends BaseFragment {
    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.tvError)
    TextView mTvError;

    String mUsername, mPassword;

    @AfterViews
    void initialize() {
    }

    @Click(R.id.btnSignIn)
    public void onSignIn() {
        mUsername = mEdtUsername.getText().toString();
        mPassword = mEdtPassword.getText().toString();
        if (mUsername.equals("") || mPassword.equals("")) {
            mTvError.setText(getResources().getString(R.string.textview_text_error_empty));
        } else if (mUsername.equals("admin") && mPassword.equals("admin")) {
            MainActivity_.intent(getActivity()).start();
            getActivity().finish();
        } else {
            mTvError.setText(getResources().getString(R.string.textview_text_error));
        }
    }

    @Click(R.id.btnSignUp)
    public void onSignUp() {
    }
}
