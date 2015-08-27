package vn.asiantech.LearingEnglish.activities;

import android.widget.Button;
import android.widget.TextView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.*;

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
    }
}
