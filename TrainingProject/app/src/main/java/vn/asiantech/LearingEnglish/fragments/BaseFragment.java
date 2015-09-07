package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;

import vn.asiantech.LearingEnglish.container.SettingContainer;
import android.app.AlertDialog;
import android.content.DialogInterface;

import vn.asiantech.LearingEnglish.core.fragments.Fragment;

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
    /**
     * Show dialog with OK button
     *
     * @param msg             message to display
     * @param onClickListener listener for OK button
     */
    protected void showDialog(String msg, DialogInterface.OnClickListener onClickListener) {

    public void replaceFragment(Fragment fragment){
        android.support.v4.app.Fragment fragmentFarent = getParentFragment();
        if(fragmentFarent instanceof SettingContainer){
            ((SettingContainer) fragmentFarent).replaceFragment(fragment, false);
        }
    }
        if (null == getActivity()) {
            return;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .setCancelable(false)
                .create();
        alertDialog.show();
    }

    /**
     * Show dialog with OK and cancel button
     *
     * @param msg
     * @param okClickListener
     * @param cancelClickListener
     */
    protected void showDialog(String msg,
                              DialogInterface.OnClickListener okClickListener,
                              DialogInterface.OnClickListener cancelClickListener) {

        if (null == getActivity()) {
            return;
        }
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, okClickListener)
                .setNegativeButton(android.R.string.cancel, cancelClickListener)
                .setCancelable(false)
                .create();
        alertDialog.show();
    }
}