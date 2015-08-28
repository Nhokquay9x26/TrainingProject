package vn.asiantech.LearingEnglish.core.fragments;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import vn.asiantech.LearingEnglish.R;

@EFragment(R.layout.fragment_login)
public class LoginFragment extends Fragment {


    @Click(R.id.bntSignup)
    void setmBntSignup(){
        replaceFragment(R.id.framelayout, SignupFragment_.builder().build(), "SignupFragment", null);
    }

    @AfterViews
    void afterView(){

    }
    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(null)
                .commit();
    }
}
