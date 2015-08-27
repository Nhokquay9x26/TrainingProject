package vn.asiantech.LearingEnglish.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;

import vn.asiantech.LearingEnglish.core.fragments.Fragment;

/**
 * Created by tientun on 3/5/15.
 */
public class BaseFragment extends Fragment {

    private String mTabTitle = null;
    private int mTabIcon = 0;
    /**
     * Show dialog with OK button
     *
     * @param msg             message to display
     * @param onClickListener listener for OK button
     */
    protected void showDialog(String msg, DialogInterface.OnClickListener onClickListener) {

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

    public BaseFragment setTabIcon(int resDrawable) {
        this.mTabIcon = resDrawable;
        return this;
    }

    public int getTabIconResource() {
        return this.mTabIcon;
    }

    public BaseFragment setTitle(String title) {
        mTabTitle = title;
        return this;
    }

    public String getTitle() {
        return mTabTitle;
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
