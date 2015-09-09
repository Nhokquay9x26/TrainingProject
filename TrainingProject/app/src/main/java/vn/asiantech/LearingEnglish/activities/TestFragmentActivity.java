package vn.asiantech.LearingEnglish.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.FavoriteFragment_;

/**
 * Created by mrson on 01/09/2015.
 */
@EActivity (R.layout.activity_test)

public class TestFragmentActivity extends BaseActionBarActivity {
    @Override
    void afterView() {

        }
    @Click(R.id.bntFragment)
    void clickB(){
        setContainer(FavoriteFragment_.builder().build(),true);
    }

    public void setContainer(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        // Commit the transaction
        fragmentTransaction.commit();
    }


}
