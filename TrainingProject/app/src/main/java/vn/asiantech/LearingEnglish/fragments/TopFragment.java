package vn.asiantech.LearingEnglish.fragments;

/**
 * @Author xuanphu
 * Created by xuanphu on 31/08/2015.
 */

import org.androidannotations.annotations.*;

import vn.asiantech.LearingEnglish.R;

@EFragment(R.layout.fragment_top)
public class TopFragment extends BaseFragment {
    @AfterViews
    void afterViews() {
        setview();
    }

    private void setview() {
        HomeFragment_ mFragment = new HomeFragment_();
        android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().addToBackStack(null);
        fragmentTransaction.replace(R.id.framlayoutTop, mFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
