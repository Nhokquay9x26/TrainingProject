package vn.asiantech.LearingEnglish.fragments;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.InitializeActivity_;

/**
 * @author DaoQuocViet
 *         Created by nhokquay9x26 on 10/09/2015.
 */
@EFragment(R.layout.fragment_signup)
public class SignUpFragment extends BaseFragment {
    @FragmentByTag("LoginFragment")
    protected LoginFragment mLoginFragment;

    @ViewById(R.id.edtUserName)
    EditText mEdtUsername;
    @ViewById(R.id.edtEmail)
    EditText mEdtEmail;
    @ViewById(R.id.edtPassword)
    EditText mEdtPassword;
    @ViewById(R.id.edtConfirmPassword)
    EditText mEdtConfirmPassword;
    @ViewById(R.id.edtPromotionCode)
    EditText mEdtPromotionCode;
    @ViewById(R.id.checkBoxEmail)
    CheckBox mCheckBoxEmail;

    @Click(R.id.btnSignUp)
    public void onSignUp() {
        String username = mEdtUsername.getText().toString();
        String email = mEdtEmail.getText().toString();
        String password = mEdtPassword.getText().toString();
        String confirmPassword = mEdtConfirmPassword.getText().toString();
        String promotionCode = mEdtPromotionCode.getText().toString();

        if (username.equals("") || email.equals("") || password.equals("") ||
                confirmPassword.equals("") || promotionCode.equals("")) {
            Toast.makeText(getActivity(), getResources().getString(R.string.textview_text_input_register), Toast.LENGTH_SHORT).show();
        } else if (mCheckBoxEmail.isSelected()) {
            Toast.makeText(getActivity(), getResources().getString(R.string.textview_text_register_successfully), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), getResources().getString(R.string.textview_text_register_successfully), Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.btnSignIn)
    public void onSignIn() {
        if (mLoginFragment == null) {
            mLoginFragment = LoginFragment_.builder().build();
        }
        ((InitializeActivity_) getActivity()).replaceFragment(mLoginFragment);
    }
}
