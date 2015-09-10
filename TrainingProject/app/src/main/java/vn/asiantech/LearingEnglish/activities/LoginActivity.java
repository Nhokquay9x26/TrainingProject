package vn.asiantech.LearingEnglish.activities;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.Login;
import vn.asiantech.LearingEnglish.network.apis.AuthApi;
import vn.asiantech.LearingEnglish.network.core.Callback;
import vn.asiantech.LearingEnglish.utils.Utils;

/**
 * Created by thanhitbk on 31/08/2015.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActionBarActivity {

    @ViewById(R.id.tvForgotPassWord)
    TextView mTvForgotPassWord;

    @ViewById(R.id.btnSignUp)
    Button mBtnSignUp;

    @ViewById(R.id.btnSignIn)
    Button mBtnSignIn;

    @ViewById(R.id.edtUserName)
    EditText mEdtUserName;

    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;

    @Override
    void afterView() {
    }

    @Click(R.id.btnSignIn)
    void clickSignIn() {
        final String email = mEdtUserName.getText().toString();
        final String password = mEdtPassword.getText().toString();
        final String MESSAGE_EMPTY_LOGIN = getResources().getString(R.string.massage_empty_login);
        final String MESSAGE_ERROR_LOGIN = getResources().getString(R.string.massage_error_login);
        if (email.length() > 0 && password.length() > 0) {
            AuthApi.login("huongta1507@gmail.com", "123456", new Callback<Login>() {
                @Override
                public void success(Login login) {
                    if(!login.getError()){
                        MainActivity_.intent(LoginActivity.this).start();
                        finish();
                    }else{
                        //show toast
                    }
                }

                @Override
                public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {

                }
            });
        } else {
            Toast.makeText(getApplicationContext(), MESSAGE_EMPTY_LOGIN, Toast.LENGTH_LONG).show();
        }

    }

    @Click(R.id.btnSignUp)
    void clickSignUp() {
        SignUpActivity_.intent(LoginActivity.this).start();
        finish();
    }

    @Click(R.id.tvForgotPassWord)
    void clickForgotPassword() {
        Utils.callDialogForgetPass(this);
    }
}