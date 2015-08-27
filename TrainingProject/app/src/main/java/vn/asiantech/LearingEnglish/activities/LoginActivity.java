package vn.asiantech.LearingEnglish.activities;

import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.*;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by xuanphu on 27/08/2015.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActionBarActivity{
    public static final String TAG = "ACTIVITY_LOGIN";
    @ViewById(R.id.edtUsername)
    EditText edtUsername;
    @ViewById(R.id.edtPassword)
    EditText edtPassword;
    @Override
    void afterView() {
    }
    @Click
    void btnSignIn(){
        String username = "admin";
        String password = "admin";
        if (edtUsername.getText().toString().equals(username)  && edtPassword.getText().toString().equals(password)){
            Toast toast = Toast.makeText(this, "thanh Cong ...", Toast.LENGTH_SHORT);
            toast.show();
        }
        else {
            Toast toast = Toast.makeText(this, "User or Pass khong dung ...", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
