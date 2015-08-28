package vn.asiantech.LearingEnglish.activities;

import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by mrson on 28/08/2015.
 */
@EActivity (R.layout.activity_signup)
public class SignupActivity extends BaseActionBarActivity{
    @ViewById (R.id.edt_username)
    EditText mEdtUsername;
    @ViewById (R.id.edt_email)
    EditText mEdtEmail;
    @ViewById (R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById (R.id.edtConfirm_pass)
    EditText mEdtConfirmPass;
    @ViewById (R.id.edtPromotion)
    EditText mEdtPromotion;

    @Override
    void afterView() {

    }
    @Click (R.id.tvButton_signUp)
    void eventSignUp(){
        Toast.makeText(this,"Test",Toast.LENGTH_LONG).show();
    }

}
