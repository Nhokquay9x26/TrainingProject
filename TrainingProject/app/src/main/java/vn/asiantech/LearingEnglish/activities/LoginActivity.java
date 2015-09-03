package vn.asiantech.LearingEnglish.activities;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by thanhitbk on 31/08/2015.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActionBarActivity {
    final static String MESSAGE_ERROR_LOGIN = "Please type your username/email and password!";
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
        if (email.length() > 0 && password.length() > 0) {
            MainActivity_.intent(LoginActivity.this).start();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), MESSAGE_ERROR_LOGIN, Toast.LENGTH_LONG).show();
        }

    }
}
