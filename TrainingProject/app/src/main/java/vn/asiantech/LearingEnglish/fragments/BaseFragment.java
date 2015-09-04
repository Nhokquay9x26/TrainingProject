package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;

import vn.asiantech.LearingEnglish.container.SettingContainer;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void replaceFragment(Fragment fragment){
        android.support.v4.app.Fragment fragmentFarent = getParentFragment();
        if(fragmentFarent instanceof SettingContainer){
            ((SettingContainer) fragmentFarent).replaceFragment(fragment, false);
        }
    }
}