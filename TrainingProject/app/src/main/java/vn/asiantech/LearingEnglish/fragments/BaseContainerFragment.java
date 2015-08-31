package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;

public abstract class BaseContainerFragment extends Fragment {
    //
    Activity activity;

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {

        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        if (addToBackStack) {
            transaction.addToBackStack(null);
        } else {
            transaction.addToBackStack(fragment.getTag());
        }
        transaction.replace(R.id.container_framelayout, fragment);

        if (activity != null && !activity.isFinishing()) {
            transaction.commitAllowingStateLoss();
        }
        getChildFragmentManager().executePendingTransactions();
    }

    public abstract android.support.v4.app.Fragment getChildFragment();

    public boolean popFragment() {
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            isPop = true;
            getChildFragmentManager().popBackStack();
        }
        return isPop;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
        // Check if parent activity implements our callback interface
        if (activity != null) {
            try {
                Log.e("test", "pop fragment: " + getChildFragmentManager().getBackStackEntryCount());
            } catch (ClassCastException ignored) {
            }
        }
    }

}