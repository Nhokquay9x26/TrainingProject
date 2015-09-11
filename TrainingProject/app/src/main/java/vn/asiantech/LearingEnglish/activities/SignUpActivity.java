package vn.asiantech.LearingEnglish.activities;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.Register;
import vn.asiantech.LearingEnglish.network.apis.AuthApi;
import vn.asiantech.LearingEnglish.network.core.Callback;

/**
 *  Created by hoai on 28/08/2015.
 */
@EActivity(R.layout.fragment_sigunup_screen)
public class SignUpActivity extends Activity {

    private String mName;
    private String mEmail;
    private String mPass;

    @ViewById(R.id.edtUsername)
    EditText mEdtUsername;
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.edtConfirmPass)
    EditText mEdtConfirmPass;
    @ViewById(R.id.edtPromotionCode)
    EditText mEdtPromotion;
    @ViewById(R.id.btnSignUp)
    Button mBtnSignUp;

    @AfterViews
    void doSomething() {

    }

    @Click(R.id.btnSignUp)
    void processSignUp() {
        mName = mEdtUsername.getText().toString().trim();
        mEmail = mEdtEmail.getText().toString().trim();
        mPass = mEdtPassword.getText().toString().trim();
        String confirm = mEdtConfirmPass.getText().toString().trim();
        String promotion = mEdtPromotion.getText().toString().trim();
        if(mName.length() == 0 || mEmail.length() == 0 || mPass.length() == 0
                || confirm.length() == 0 || promotion.length() == 0){
            Toast.makeText(getApplicationContext(), R.string.text_notice_field_sign_up, Toast.LENGTH_SHORT).show();
        }
        else if(!mPass.equals(confirm)){
            mEdtConfirmPass.setText("");
            mEdtConfirmPass.setError(getResources().getString(R.string.text_notice_confirm_pass_sign_up));
        }else{
            startSignUp();
        }
    }

    private  void startSignUp(){

        AuthApi.register(mName, mEmail, mPass, new Callback<Register>() {
            @Override
            public void success(Register register) {
                if(register.getError()){
                    Toast.makeText(getBaseContext(), register.getMessage() + "", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getBaseContext(), register.getMessage() + "", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {
                Toast.makeText(getBaseContext(),myError.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
