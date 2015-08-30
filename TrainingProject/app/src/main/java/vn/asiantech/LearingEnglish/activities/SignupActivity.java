package vn.asiantech.LearingEnglish.activities;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by mrson on 28/08/2015.
 */
@EActivity(R.layout.activity_signup)
public class SignupActivity extends BaseActionBarActivity {
    @ViewById(R.id.edt_username)
    EditText mEdtUsername;
    @ViewById(R.id.edt_email)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.edtConfirm_pass)
    EditText mEdtConfirmPass;
    @ViewById(R.id.edtPromotion)
    EditText mEdtPromotion;


    @Override
    void afterView() {

    }

    @Click(R.id.tvButton_signUp)
    void eventSignUp() {
       // Toast.makeText(this,"Test",Toast.LENGTH_LONG).show();
        createAccount();


    }

    private void createAccount() {
        View focusView = null;

        String Username = mEdtUsername.getText().toString();
        String PassWord = mEdtPassword.getText().toString();
        String ConfirmPass = mEdtConfirmPass.getText().toString();
        String Email = mEdtEmail.getText().toString();
        String Promotion = mEdtPromotion.getText().toString();
        if(TextUtils.isEmpty(Username)){
            mEdtUsername.setError("Chưa nhập username");
        }else if(TextUtils.isEmpty(PassWord)){
            mEdtPassword.setError("Chưa nhập password");
        }else if(TextUtils.isEmpty(Email)){
            mEdtEmail.setError("Chưa nhập email");
        }else if(TextUtils.isEmpty(Promotion)){
           mEdtPromotion.setError("Chưa nhập promotion code");
        }
    }


}
