package vn.asiantech.LearingEnglish.activities;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.ChangePassWord;
import vn.asiantech.LearingEnglish.network.*;
import vn.asiantech.LearingEnglish.network.core.Callback;

/**
 * Created by Tran Quoc Hoan on 11/09/2015.
 */
@EActivity(R.layout.activity_change_password)
public class ChangePassActivity extends BaseActionBarActivity {
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtOldPassWord)
    EditText mEdtOldPassWord;
    @ViewById(R.id.edtNewPassWord)
    EditText mEdtNewPassWord;
    @ViewById(R.id.edtConfirmNewPassword)
    EditText mEdtConfirmNewPassWord;
    @ViewById(R.id.btnChange)
    Button mBtnChange;

    private ProgressDialog mProgressDialog;

    @Override
    void afterView() {

    }

    @Click(R.id.btnChange)
    void changePass() {
        String mEmail = mEdtEmail.getText().toString();
        String mOldPassWord = mEdtOldPassWord.getText().toString();
        String mNewPassWord = mEdtNewPassWord.getText().toString();
        String mConfirmNewPassWord = mEdtConfirmNewPassWord.getText().toString();
        if (mNewPassWord.equals(mConfirmNewPassWord)) {
            changePassWord(mEmail, mOldPassWord, mNewPassWord);
        } else {
            Toast.makeText(ChangePassActivity.this, R.string.toast_change_pass_text_confirm_not_match, Toast.LENGTH_SHORT).show();
        }
    }

    private void changePassWord(String mEmail, String mOldPassWord, String mNewPassWord) {
        mProgressDialog = new ProgressDialog(ChangePassActivity.this);
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.show();
        AuthorApi.changePassWord(mEmail, mOldPassWord, mNewPassWord, new Callback<ChangePassWord>() {
            @Override
            public void success(ChangePassWord changePassWord) {
                if (mProgressDialog != null) {
                    mProgressDialog.dismiss();
                }
                if (!changePassWord.getError()) {
                    Toast.makeText(ChangePassActivity.this, R.string.toast_change_pass_text_success_text, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ChangePassActivity.this, R.string.toast_change_pass_text_faild, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {
                Toast.makeText(ChangePassActivity.this, R.string.toast_change_pass_text_faild, Toast.LENGTH_LONG).show();

            }
        });
    }
}
