package vn.asiantech.LearingEnglish.fragments;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import vn.asiantech.LearingEnglish.R;


/**
 * @author mrson
 * Created by mrson on 04/09/2015.
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
        Toast.makeText(getActivity(), "Click logout setting", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ShowToast")
    @Click(R.id.imgTopSettingRight)
    void imgTopSettingRight() {
        Toast.makeText(getActivity(), "Click top setting right", Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("ShowToast")
    @Click(R.id.imgLogoutSetting)
    void setLogoutSettingRight() {
        Toast.makeText(getActivity(), "Click Logout setting", Toast.LENGTH_SHORT).show();
    }


}


