package vn.asiantech.LearingEnglish.activities;



import android.content.Intent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;

import vn.asiantech.LearingEnglish.R;
/**
 * Created by tientun on 3/5/15.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActionBarActivity {

    @OptionsItem(android.R.id.home)
    protected void backAction() {
        finish();
    }

    @Override
    void afterView() {
        startActivity(new Intent(this,SplashScreenActivity_.class));
    }
}
