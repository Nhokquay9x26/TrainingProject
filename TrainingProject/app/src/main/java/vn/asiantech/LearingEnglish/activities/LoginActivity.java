package vn.asiantech.LearingEnglish.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.*;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.InfoLogin;
import vn.asiantech.LearingEnglish.network.*;
import vn.asiantech.LearingEnglish.network.core.Callback;

/**
 * @Author XuanPhu
 * Created by xuanphu on 27/08/2015.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActionBarActivity {
    public static final String TAG = "ACTIVITY_LOGIN";
    private Boolean mIsExit = false;

    private ProgressDialog mProgressLogin;
    @ViewById(R.id.edtUsername)
    EditText edtUsername;
    @ViewById(R.id.edtPassword)
    EditText edtPassword;
    @Override
    void afterView() {
        //getSupportActionBar().hide();
    }
    @Click
    void btnSignIn(){

        if (!edtUsername.getText().toString().equals("")  && !edtPassword.getText().toString().equals("")){
          //  login(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim());
            MainActivity_.intent(this).start();
        }
        else if (edtUsername.getText().toString().equals("")  || edtPassword.getText().toString().equals("")){
            Toast toast = Toast.makeText(this, "Please input full data ...",Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(this, "User or Pass not correct ...", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Click
    void tvSignup() {
        Intent intent = new Intent(this, SignupActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.tvForgotPassword)
    void getNewPassWord() {
        ForgotPassActivity_.intent(LoginActivity.this).start();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (mIsExit) {
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            mIsExit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsExit = false;
                }
            }, 3 * 1000);
        }

    }

    public void login(String email, String password){
        mProgressLogin = new ProgressDialog(this);
        mProgressLogin.setMessage("Login...");
        mProgressLogin.setCanceledOnTouchOutside(false);
        mProgressLogin.show();
        AuthorApi.loginAcount(email, password, new Callback<InfoLogin>() {
            @Override
            public void success(InfoLogin infoLogin) {

                MainActivity_.intent(LoginActivity.this).start();
                mProgressLogin.dismiss();
            }

            @Override
            public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {
                mProgressLogin.dismiss();
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();

            }
        });
    }


}