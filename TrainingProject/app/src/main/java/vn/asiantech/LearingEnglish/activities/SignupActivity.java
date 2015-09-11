package vn.asiantech.LearingEnglish.activities;

import android.app.ProgressDialog;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.InfoRegister;
import vn.asiantech.LearingEnglish.network.AuthorApi;
import vn.asiantech.LearingEnglish.network.core.Callback;

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
    private ProgressDialog mProgressDialog;

    @Override
    void afterView() {

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
            Toast.makeText(this, " Test  NOt ok", Toast.LENGTH_SHORT).show();
        }
        if (mCheckUser && mCheckPass && mCheckEmail && mCheckPromotionCode) {

            register(mEdtUsername.getText().toString().trim(),
                    mEdtConfirmPass.getText().toString().trim(),
                    mEdtEmail.getText().toString().trim());

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

    public void register(String name, String pass, String email) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Registering .....");
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
        AuthorApi.registerAccount(email, pass, name, new Callback<InfoRegister>() {
            @Override
            public void success(InfoRegister infoRegister) {
                if (infoRegister.getError().toString().equals("false")) {
                    if (mProgressDialog.isShowing()) {
                        Toast.makeText(SignupActivity.this, infoRegister.getMessage(), Toast.LENGTH_SHORT).show();
                        MainActivity_.intent(getBaseContext()).start();
                        mProgressDialog.dismiss();
                    }
                }
            }

            @Override
            public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {
                mProgressDialog.dismiss();
                Log.d("vinhhlb", "error is " + error);
            }
        });
    }
}