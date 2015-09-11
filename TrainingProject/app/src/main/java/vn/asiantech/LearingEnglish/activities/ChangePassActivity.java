package vn.asiantech.LearingEnglish.activities;

import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by Tran Quoc Hoan on 11/09/2015.
 */
@EActivity(R.layout.activity_change_password)
public class ChangePassActivity extends BaseActionBarActivity {
    @ViewById(R.id.edtOldPassWord)
    EditText mEdtOldPassWord;
    @ViewById(R.id.edtNewPassWord)
    EditText mEdtNewPassWord;
    @ViewById(R.id.edtConfirmNewPassword)
    EditText mEdtConfirmNewPassWord;
    @ViewById(R.id.btnChange)
    Button mBtnChange;

    @Override
    void afterView() {

    }

    @Click(R.id.btnForgot_activity_forgot_pass)
    void forgotPass() {
        String mOldPassWord = mEdtOldPassWord.getText().toString();
        String mNewPassWord = mEdtNewPassWord.getText().toString();
        String mConfirmNewPassWord = mEdtConfirmNewPassWord.getText().toString();

    }
}
