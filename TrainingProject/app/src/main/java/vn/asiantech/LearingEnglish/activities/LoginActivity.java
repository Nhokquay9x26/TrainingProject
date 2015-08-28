package vn.asiantech.LearingEnglish.activities;

import org.androidannotations.annotations.EActivity;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by xuanphu on 27/08/2015.
 */
@EActivity(R.layout.fragment_testing)
public class LoginActivity extends BaseActionBarActivity{

    @Override
    void afterView() {
        getSupportActionBar().hide();
    }
}
