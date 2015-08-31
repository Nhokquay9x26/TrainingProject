package vn.asiantech.LearingEnglish.activities;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by hoai on 28/08/2015.
 */
@EActivity(R.layout.fragment_sigunup_screen)
public class SignUpActivity extends Activity {
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
    void checkSignUp() {
        ;
        isRequire("Username", mEdtUsername);
        isValidEmail("Email", mEdtEmail);
        isRequire("Password", mEdtPassword);
        isRequire("Confirm Pass", mEdtConfirmPass);
        isRequire("Promotion Code", mEdtPromotion);


    }

    private void isValidEmail(CharSequence target, EditText edtEmail) {
        if (target == null) {
            edtEmail.setError("Email is required");
        }
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches() == false) {
            edtEmail.setError("Email is failed formation");
        }

    }

    private void isRequire(String target, EditText edtTarget) {

        if (edtTarget.getText().toString().trim().length() == 0) {
            edtTarget.setError(target + " is required");
        }
    }

}
