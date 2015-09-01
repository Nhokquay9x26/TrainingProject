package vn.asiantech.LearingEnglish.activities;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * @Author MrSon
 * Created by mrson on 28/08/2015.
 */
@EActivity(R.layout.activity_signup)
public class SignupActivity extends BaseActionBarActivity {
    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.edtConfirmPass)
    EditText mEdtConfirmPass;
    @ViewById(R.id.edtPromotion)
    EditText mEdtPromotion;
    private boolean mCheckUser;
    private boolean mCheckPass;
    private boolean mCheckPromotionCode;
    private boolean mCheckEmail;
    @Override
    void afterView() {
        getSupportActionBar().hide();
    }

    @Click(R.id.tvButtonSignUp)
    void eventSignUp() {
        createAccount();
    }

    private void createAccount() {
        checkUserName();
        checkEmail();
        checkPassWord();
        checkPromotionCode();
        if (!mCheckUser || !mCheckPass || !mCheckEmail || !mCheckPromotionCode) {
            Toast.makeText(this, " Test not OK", Toast.LENGTH_SHORT).show();
        }
        if (mCheckUser && mCheckPass && mCheckEmail && mCheckPromotionCode) {
            Toast.makeText(this, " Test  OK", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkUserName() {
        String UserName = mEdtUsername.getText().toString();
        if (TextUtils.isEmpty(UserName)) {
            mEdtUsername.setError(getString(R.string.error_field_required));
            mCheckUser = false;
        } else {
            mCheckUser = true;
        }
    }

    private void checkPassWord() {
        String ConfirmPassWord = mEdtConfirmPass.getText().toString();
        String PassWord = mEdtPassword.getText().toString();
        if (TextUtils.isEmpty(PassWord) || (PassWord.length() < 4) || !(PassWord.equals(ConfirmPassWord))) {
            mEdtPassword.setError(getString(R.string.error_field_required));
            mEdtConfirmPass.setError(getString(R.string.error_field_required));
            mCheckPass = false;
        } else {
            mCheckPass = true;
        }
    }

    private void checkEmail() {
        String Email = mEdtEmail.getText().toString();
        if (TextUtils.isEmpty(Email) || !(Email.contains("@"))) {
            mEdtEmail.setError(getString(R.string.error_field_required));
            mCheckEmail = false;
        } else {
            mCheckEmail = true;
        }
    }

    private void checkPromotionCode() {
        String PromotionCode = mEdtPromotion.getText().toString();
        if (TextUtils.isEmpty(PromotionCode)) {
            mEdtPromotion.setError(getString(R.string.error_field_required));
            mCheckPromotionCode = false;
        } else {
            mCheckPromotionCode = true;
        }
    }
}