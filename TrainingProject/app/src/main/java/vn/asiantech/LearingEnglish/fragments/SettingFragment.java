package vn.asiantech.LearingEnglish.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import vn.asiantech.LearingEnglish.AlarmServices.MyServices;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.ChangePassActivity;
import vn.asiantech.LearingEnglish.activities.ChangePassActivity_;
import vn.asiantech.LearingEnglish.activities.ForgotPassActivity_;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;


/**
 * @author mrson
 *         Created by mrson on 04/09/2015.
 */

@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment {

    public static final String INTENT_FILTER = "INTENT_FILTER";
    private IntentFilter intentFilter;

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
        alarmTime();
    }

    @Click(R.id.imgAlertSettingRight)
    void alertSetting() {
        alarmTime();
    }

    @SuppressWarnings("unused")
    @SuppressLint("ShowToast")
    @Click(R.id.imgLogout)
    void logOutClick() {
    }

    @Click(R.id.rlChangePass)
    void changePassWord() {
        ChangePassActivity_.intent(getActivity()).start();
    }

    @SuppressLint("ShowToast")
    @Click(R.id.imgTopSettingRight)
    void imgTopSettingRight() {
        setContainer(RatingsFragment_.builder().build(), true);
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

    public void alarmTime() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog_alert);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setTitle("Alert");
        dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_alert);
        dialog.show();

        final EditText timeAlarm = (EditText) dialog.findViewById(R.id.edtTime);
        Switch turnOn = (Switch) dialog.findViewById(R.id.switchTurn);

        turnOn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheck) {
                if (isCheck) {
                    if (!("").equals(timeAlarm.getText().toString().trim())) {
                        Bundle mBunddle = new Bundle();
                        Intent intent = new Intent(getActivity(), MyServices.class);
                        mBunddle.putInt("KEY_MAX_VALUE", Integer.parseInt(timeAlarm.getText().toString().trim()));
                        intent.putExtras(mBunddle);
                        getActivity().startService(intent);
                    }
                } else {
                    Intent intent = new Intent(getActivity(), MyServices.class);
                    getActivity().stopService(intent);
                }
            }
        });
    }

    public void setContainer(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contain, fragment);
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }
}