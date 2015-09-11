package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;

import java.util.logging.Handler;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;
import vn.asiantech.LearingEnglish.models.Login;
import vn.asiantech.LearingEnglish.network.*;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 9/4/15.
 */
@EFragment(R.layout.fragment_login)
public class LoginFragment extends BaseFragment {
    @FragmentByTag("SignUpFragment")
    protected SignUpFragment mSignUpFragment;
    public static final String TAG = "ACTIVITY_LOGIN";
    private ProgressDialog mProgressLogin;
    private Boolean mIsExit = false;

    public static final String API = "http://172.16.100.115:8080/internship/api/index.php";

    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.tvError)
    TextView mTvError;

    @AfterViews
    public void init() {

    }

    @Click(R.id.btnSignIn)
    public void onSignIn() {
        String username = mEdtUsername.getText().toString();
        String password = mEdtPassword.getText().toString();
        if (username.equals("") || password.equals("")) {
            mTvError.setText(getResources().getString(R.string.textview_text_error_empty));
        } else {
            MainActivity_.intent(getActivity()).start();
//            login(username, password);
        }
    }


    @Click(R.id.btnSignUp)
    public void onSignUp() {
        if (mSignUpFragment == null) {
            mSignUpFragment = SignUpFragment_.builder().build();
        }
        addChildFragment(mSignUpFragment);
    }

    @Click(R.id.tvForgotPassword)
    void onClickForgetPass() {
        addChildFragment(ForgotPasswordFragment_.builder().build());
    }

    public void login(String email, String password) {
        mProgressLogin = new ProgressDialog(getActivity());
        mProgressLogin.setMessage("Login...");
        mProgressLogin.setCanceledOnTouchOutside(false);
        mProgressLogin.show();

        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(API).build();
        Api testRetrofit = adapter.create(Api.class);
        testRetrofit.login(email, password, new vn.asiantech.LearingEnglish.network.core.Callback<Login>() {
            @Override
            public void success(Login login) {
                mProgressLogin.dismiss();
                MainActivity_.intent(getActivity()).start();
            }

            @Override
            public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {
                mProgressLogin.dismiss();
                mTvError.setText(getResources().getString(R.string.textview_text_error));
            }
        });
    }
}