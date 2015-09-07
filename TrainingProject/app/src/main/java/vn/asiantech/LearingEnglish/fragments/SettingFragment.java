package vn.asiantech.LearingEnglish.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import vn.asiantech.LearingEnglish.R;


/**
 * @author mrson
 *         Created by mrson on 04/09/2015.
 */

@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment {

    @Click(R.id.imgSettingProfile)
    void imgSettingProfile() {
        Log.d("Test", "test");
    }

    @SuppressLint("ShowToast")
    @Click(R.id.imgTopSetting)
    void setTopSetting() {
        Toast.makeText(getActivity(), "Click top setting", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ShowToast")
    @Click(R.id.imgAlert)
    void alertSettingClick() {
        Toast.makeText(getActivity(), "Click alert setting", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("unused")
    @SuppressLint("ShowToast")
    @Click(R.id.imgLogout)
    void logOutClick() {

    }

    @SuppressLint("ShowToast")
    @Click(R.id.imgTopSettingRight)
    void imgTopSettingRight() {
        Toast.makeText(getActivity(), "Click top setting right", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ShowToast")
    @Click(R.id.imgLogoutSetting)
    void setLogoutSettingRight() {
        logOut();
    }

    public void logOut() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Logout");
        alertDialog.setIcon(R.drawable.ic_logout);
        alertDialog.setMessage("Do you close Application ?");

        alertDialog.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
                Toast.makeText(getActivity(), "Successfully Logged Out", Toast.LENGTH_LONG).show();
            }
        });

        alertDialog.setPositiveButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();

    }
}


