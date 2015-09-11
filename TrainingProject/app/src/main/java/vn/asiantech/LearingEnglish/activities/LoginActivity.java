package vn.asiantech.LearingEnglish.activities;

import android.content.Intent;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.*;

import vn.asiantech.LearingEnglish.R;

/**
 * @Author XuanPhu
 * Created by xuanphu on 27/08/2015.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActionBarActivity {
    public static final String TAG = "ACTIVITY_LOGIN";
    private Boolean mIsExit = false;

    @ViewById(R.id.edtUsername)
    EditText edtUsername;
    @ViewById(R.id.edtPassword)
    EditText edtPassword;

    @Override
    void afterView() {
        //getSupportActionBar().hide();
    }

    @Click
    void btnSignIn() {
        MainActivity_.intent(LoginActivity.this).start();
//        String username = "admin";
//        String password = "admin";
//        if (edtUsername.getText().toString().equals(username) && edtPassword.getText().toString().equals(password)) {
//            Toast toast = Toast.makeText(this, "Successful ...", Toast.LENGTH_SHORT);
//            toast.show();
//            MainActivity_.intent(LoginActivity.this).start();
//        } else if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
//            Toast toast = Toast.makeText(this, "Please input full data ...", Toast.LENGTH_SHORT);
//            toast.show();
//        } else {
//            Toast toast = Toast.makeText(this, "User or Pass not correct ...", Toast.LENGTH_SHORT);
//            toast.show();
//        }
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
}