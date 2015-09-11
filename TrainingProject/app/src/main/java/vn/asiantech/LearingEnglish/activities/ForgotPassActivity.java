package vn.asiantech.LearingEnglish.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.ForgotPass;
import vn.asiantech.LearingEnglish.network.AuthorApi;
import vn.asiantech.LearingEnglish.network.core.Callback;

/**
 * Created by tranquochoan on 27/08/2015.
 */
@EActivity(R.layout.activity_forgot_pass)
public class ForgotPassActivity extends BaseActionBarActivity {
    @ViewById(R.id.btnForgot_activity_forgot_pass)
    Button mBtnForgot;

    @ViewById(R.id.edtEmail_activity_forgot_pass)
    EditText mEdtEmail;

    private ProgressDialog mProgressDialog;

    @Override
    void afterView() {

    }

    @Click(R.id.btnForgot_activity_forgot_pass)
    void forgotPass() {
        String mEmail = mEdtEmail.getText().toString();
        if (mEmail.equals("")) {
            Toast.makeText(ForgotPassActivity.this, R.string.activity_forgot_pass_Text_Warring, Toast.LENGTH_SHORT).show();
        } else {
            forgotPassWord(mEmail);
        }
    }

    private void forgotPassWord(String email) {
        mProgressDialog = new ProgressDialog(ForgotPassActivity.this);
        mProgressDialog.setMessage(getString(R.string.please_wait));
        mProgressDialog.show();
        AuthorApi.getNewPassWord(email, new Callback<ForgotPass>() {
            @Override
            public void success(ForgotPass forgotPass) {
                if (!forgotPass.getError()) {
                    showDialog(getString(R.string.alert_forgot_pass_success), getString(R.string.alert_new_pass) + forgotPass.getPassword());
                } else {
                    showDialog(getString(R.string.alert_get_new_pass_fail), getString(R.string.alert_email_not_exist));
                }
            }

            @Override
            public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {
                showDialog(getString(R.string.error), error.toString());
            }
        });
    }

    private void showDialog(String title, String message) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
