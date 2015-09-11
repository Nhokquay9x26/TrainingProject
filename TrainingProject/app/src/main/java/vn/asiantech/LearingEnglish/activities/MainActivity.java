package vn.asiantech.LearingEnglish.activities;

import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.OptionsItem;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;
import vn.asiantech.LearingEnglish.fragments.MainFragment;
import vn.asiantech.LearingEnglish.fragments.MainFragment_;

/**
 * Created by tientun on 3/5/15.
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActionBarActivity {

    @Extra
    protected int mSum;

    @FragmentByTag("MainFragment")
    protected MainFragment mMainFragment;

    @OptionsItem(android.R.id.home)
    protected void backAction() {
        finish();
    }

    @Override
    void afterView() {
        if (mMainFragment == null) {
            mMainFragment = MainFragment_.builder().build();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_container, mMainFragment, "MainFragment");
        ft.commit();
    }

    public void addFragmentMain(Fragment layoutID){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.main_container, layoutID);
        ft.commit();
    }
}
