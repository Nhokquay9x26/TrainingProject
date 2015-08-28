package vn.asiantech.LearingEnglish.activities;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.EActivity;

import lombok.NonNull;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.core.fragments.LoginFragment_;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActionBarActivity {

    @Override
    void afterView() {
        addFragment(R.id.framelayout, LoginFragment_.builder().build(), "LoginFragment");
    }

    public void addFragment(@IdRes int containerViewId,
                            @NonNull Fragment fragment,
                            @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

}
