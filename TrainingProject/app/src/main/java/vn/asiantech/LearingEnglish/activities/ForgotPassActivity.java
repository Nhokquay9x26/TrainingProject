package vn.asiantech.LearingEnglish.activities;

import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.*;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by tranquochoan on 27/08/2015.
 */
@EActivity(R.layout.activity_forgot_pass)
public class ForgotPassActivity extends BaseActionBarActivity {
    @ViewById(R.id.btnForgot_activity_forgot_pass)
    Button btnForgot;

    @ViewById(R.id.edtEmail_activity_forgot_pass)
    EditText edtEmail;

    @Override
    void afterView() {

    }

    @Click(R.id.btnForgot_activity_forgot_pass)
    void forgotPass(){
        edtEmail.setText("hello");
    }
}
