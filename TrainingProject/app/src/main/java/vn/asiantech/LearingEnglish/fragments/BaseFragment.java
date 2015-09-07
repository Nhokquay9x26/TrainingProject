package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.BaseActionBarActivity;
import vn.asiantech.LearingEnglish.activities.MainActivity;


/**
 * Fragmentの基底クラス
 */
public abstract class BaseFragment extends Fragment {
    private static final String TAG = BaseFragment.class.getSimpleName();
    private final SparseArray<Object> mRequestCodes = new SparseArray<Object>();
    private int mLastPopFragmentID = 0;

    List<WeakReference<Fragment>> fragList = new ArrayList<WeakReference<Fragment>>();

    /**
     * Called when fragment is appeard.
     */
    public void refresh() {
        // If need refresh, override.
    }

    /**
     * ダイアログ表示を行う
     * [OK]ボタンのみ
     *
     * @param msg
     * @param onClickListener
     */
    protected void showDialog(String msg, DialogInterface.OnClickListener onClickListener) {

        if (null == getActivity()) {
            return;
        }
        // ダイアログを表示
        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setMessage(msg)
                .setPositiveButton(android.R.string.ok, onClickListener)
                .setCancelable(false)
                .create();
        alertDialog.show();
    }

    /**
     * ダイアログ表示を行う
     * [OK]ボタンと[cancel]ボタン
     *
     * @param msg
     * @param okClickListener
     * @param cancelClickListener
     */
    protected void showDialog(String msg,
                              DialogInterface.OnClickListener okClickListener,
                              DialogInterface.OnClickListener cancelClickListener) {
        showDialog(null, msg, getString(android.R.string.ok), getString(android.R.string.cancel), okClickListener, cancelClickListener);
    }

    /**
     * show dialog.
     * Positive and Negative position reverse. It's OK.
     */
    protected AlertDialog showDialog(String title, String msg,
                              String txtPositiveButton, String txtNegativeButton,
                              DialogInterface.OnClickListener okClickListener,
                              DialogInterface.OnClickListener cancelClickListener) {

        if (null == getActivity()) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (null != title) {
            builder.setTitle(title);
        }
        if (null != msg) {
            builder.setMessage(msg);
        }
        builder.setPositiveButton(txtNegativeButton, cancelClickListener);
        builder.setNegativeButton(txtPositiveButton, okClickListener);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();
        dialog.show();

        return dialog;
    }



    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (getParentFragment() instanceof vn.asiantech.LearingEnglish.core.fragments.BaseFragment) {
            if (getId() > 0) {
                ((BaseFragment) getParentFragment()).registerRequestCode(requestCode, getId());
            } else if (getTag() != null) {
                ((BaseFragment) getParentFragment()).registerRequestCode(requestCode, getTag());
            }
            getParentFragment().startActivityForResult(intent, requestCode);
        } else {
            super.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!checkNestedFragmentsForResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    protected boolean checkNestedFragmentsForResult(int requestCode, int resultCode, Intent data) {
        Object obj = mRequestCodes.get(requestCode);
        if (obj == null) {
            return false;
        }
        mRequestCodes.remove(requestCode);
        Fragment fragment;
        if (obj instanceof Integer) {
            fragment = getChildFragmentManager().findFragmentById((Integer) obj);
        } else if (obj instanceof String) {
            fragment = getChildFragmentManager().findFragmentByTag((String) obj);
        } else {
            return false;
        }
        fragment.onActivityResult(requestCode, resultCode, data);
        return true;
    }

    public void registerRequestCode(int requestCode, int fragmentId) {
        mRequestCodes.put(requestCode, fragmentId);
    }

    public void registerRequestCode(int requestCode, String fragmentTag) {
        mRequestCodes.put(requestCode, fragmentTag);
    }



    protected MainActivity getMainActivity() {
        Activity activity = getActivity();
        if (activity != null && MainActivity.class.isInstance(activity)) {
            return (MainActivity) activity;
        }
        return null;
    }

    protected BaseActionBarActivity getBaseActionbarActivity() {
        Activity activity = getActivity();
        if (activity != null && BaseActionBarActivity.class.isInstance(activity)) {
            return (BaseActionBarActivity) activity;
        }
        return null;
    }

    public void addChildFragment(Fragment fragment) {
        if (getParentFragment() != null && getParentFragment() instanceof BaseFragment) {
            ((BaseFragment) getParentFragment()).addChildFragment(fragment);
            return;
        }
        if (getView().getId() < 0) {
            Log.e(getClass().getSimpleName(), "Please set ID for fragment layout");
            return;
        }
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right, R.anim.slide_in_right, R.anim.slide_out_right);
        fragmentTransaction.add(getView().getId(), fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragList.add(new WeakReference(fragment));
    }

    public void addChildFragmentNotAnimation(Fragment fragment) {
        if (getParentFragment() != null && getParentFragment() instanceof BaseFragment) {
            ((BaseFragment) getParentFragment()).addChildFragmentNotAnimation(fragment);
            return;
        }
        if (getView().getId() < 0) {
            Log.e(getClass().getSimpleName(), "Please set ID for fragment layout");
            return;
        }
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(getView().getId(), fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragList.add(new WeakReference(fragment));
    }

    public boolean popChildFragment() {
        if (getParentFragment() != null && getParentFragment() instanceof vn.asiantech.LearingEnglish.core.fragments.BaseFragment) {
            return ((vn.asiantech.LearingEnglish.core.fragments.BaseFragment) getParentFragment()).popChildFragment();
        }
        boolean isPop = false;
        if (getChildFragmentManager().getBackStackEntryCount() > 0) {
            isPop = true;
            int lastPopFragmentIDTmp = getChildFragmentManager().getBackStackEntryAt(getChildFragmentManager().getBackStackEntryCount() - 1).hashCode();
            if (lastPopFragmentIDTmp != mLastPopFragmentID) {
                getChildFragmentManager().popBackStack();
                fragList.remove(fragList.size() - 1);
                mLastPopFragmentID = lastPopFragmentIDTmp;

                if (fragList.size() == 0) {
                    // refresh self
                    refresh();
                } else {
                    // refresh next top
                    vn.asiantech.LearingEnglish.core.fragments.BaseFragment frontFragment = (vn.asiantech.LearingEnglish.core.fragments.BaseFragment) fragList.get(fragList.size() - 1).get();
                    frontFragment.refresh();
                }
            }
        }
        return isPop;
    }

    public Fragment getVisibleChildFragment() {
        if (fragList.size() > 0) {
            return fragList.get(fragList.size() - 1).get();
        }
        return this;
    }

    public void popChildFragmentToTop() {
        if (getParentFragment() != null && getParentFragment() instanceof vn.asiantech.LearingEnglish.core.fragments.BaseFragment) {
            ((vn.asiantech.LearingEnglish.core.fragments.BaseFragment) getParentFragment()).popChildFragmentToTop();
            return;
        }
        if (getChildFragmentManager().getBackStackEntryCount() == 0) {
            // refresh self
            refresh();
            return;
        }
        FragmentManager.BackStackEntry backStackEntry = getChildFragmentManager().getBackStackEntryAt(0);
        getChildFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        fragList.clear();
        refresh();
    }

    public BaseFragment getTopFragment() {
        if (getParentFragment() != null && getParentFragment() instanceof vn.asiantech.LearingEnglish.core.fragments.BaseFragment) {
            return ((BaseFragment) getParentFragment()).getTopFragment();
        }
        return this;
    }
}
