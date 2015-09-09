package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;

import vn.asiantech.LearingEnglish.container.SettingContainer;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;
import vn.asiantech.LearingEnglish.views.HeaderBar;

public abstract class BaseFragment extends Fragment {

    protected OnBaseFragmentListener mOnBaseFragmentListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnBaseFragmentListener = (OnBaseFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnBaseFragmentListener");
        }
    }


    public void replaceFragment(Fragment fragment){
        android.support.v4.app.Fragment fragmentFarent = getParentFragment();
        if(fragmentFarent instanceof SettingContainer){
            ((SettingContainer) fragmentFarent).replaceFragment(fragment, false);
        }
    }

    public interface OnBaseFragmentListener {
        void setHeaderTitle(String title);
        void setTypeHeaderBar(HeaderBar.HeaderBarType type);
    }
}