package vn.asiantech.LearingEnglish.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import vn.asiantech.LearingEnglish.R;


/**
 * @author tantv
 *         Created by tantv on 04/09/2015.
 */

@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment {
    private static boolean mIsStatusAlarm;
    private CountDownTimer mNewtimer;
    private ArrayList<Uri> mListUriRingTones;
    private int mPositionRingToneSelection;



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
        showDialogAlarm();
    }
    @AfterViews
    void afterView(){

    }

    private void playRingTone(){
        Log.d("Tag","CLick Play");
        MediaPlayer mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mPlayer.setDataSource(getActivity(), mListUriRingTones.get(mPositionRingToneSelection));
        } catch (IllegalArgumentException e) {
            Toast.makeText(getActivity(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(getActivity(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IllegalStateException e) {
            Toast.makeText(getActivity(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mPlayer.prepare();
        } catch (IllegalStateException e) {
            Toast.makeText(getActivity(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getActivity(), "You might not set the URI correctly!", Toast.LENGTH_LONG).show();
        }

        mPlayer.start();
    }


    private ArrayList<Uri> getRingtones() {
        RingtoneManager ringtoneMgr = new RingtoneManager(getActivity());
        ringtoneMgr.setType(RingtoneManager.TYPE_ALARM);
        Cursor alarmsCursor = ringtoneMgr.getCursor();
        int alarmsCount = alarmsCursor.getCount();
        if (alarmsCount == 0 && !alarmsCursor.moveToFirst()) {
            return null;
        }
        ArrayList<Uri> alarms = new ArrayList<>();
        while (!alarmsCursor.isAfterLast() && alarmsCursor.moveToNext()) {
            int currentPosition = alarmsCursor.getPosition();
            alarms.add(ringtoneMgr.getRingtoneUri(currentPosition));
        }
        alarmsCursor.close();
        return alarms;
    }

    private String[] getTitleRingTone(ArrayList<Uri> listTones) {
        String[] listRingTones = new String[listTones.size()];
        for (int i = 0; i < listTones.size(); i++) {
            Ringtone ringtone = RingtoneManager.getRingtone(getActivity(), listTones.get(i));
            String name = ringtone.getTitle(getActivity());
            listRingTones[i] = name;
        }
        return listRingTones;
    }

    private void showListOnSpinner(Spinner spinner, String[] listRingtones) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, listRingtones);
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new OnClickSpinner());
    }

    private void showDialogAlarm() {
        mListUriRingTones = new ArrayList<>();
        mListUriRingTones = getRingtones();
        String[] listRingTones ;//= new String[mListUriRingTones.size()];
        listRingTones = getTitleRingTone(mListUriRingTones);
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_alarm);
        dialog.setTitle("Alarm Setting");
        final Spinner spinnerListTone = (Spinner) dialog.findViewById(R.id.spinnerListTone);
        showListOnSpinner(spinnerListTone, listRingTones);
        final TextView tvTimeCurrent = (TextView) dialog.findViewById(R.id.tvTimeCurrent);
        final ImageView mBtnPlayRingTone = (ImageView)dialog.findViewById(R.id.btnPlayRingTone);
        mBtnPlayRingTone.setOnClickListener(new OnClickImgRingTone());
        mNewtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                showTimeCurrentToTextView(tvTimeCurrent);
            }

            public void onFinish() {

            }
        };
        mNewtimer.start();
        final ImageView imgOnOffAlarm = (ImageView) dialog.findViewById(R.id.imgOnOffAlarm);
        if (!mIsStatusAlarm) {
            imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_off);
        } else {
            imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_on);
        }
        imgOnOffAlarm.setOnClickListener(new ClickImageViewOnOff());
        dialog.show();
    }
    private void showTimeCurrentToTextView(TextView textView){
        Calendar c = Calendar.getInstance();
        int AM_orPM = c.get(Calendar.AM_PM);
        int hour = c.get(Calendar.HOUR);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String hourStr = hour + "";
        if (hour < 10) {
            if (hour == 0) {
                hourStr = "12";
            } else {
                hourStr = "0" + hour;
            }
        }
        String minuteStr = minute + "";
        if (minute < 10) {
            minuteStr = "0" + minute;
        }
        String secondStr = second + "";
        if (second < 10) {
            secondStr = "0" + second;
        }
        if (AM_orPM == 0) {
            textView.setText("AM " + hourStr + ":" + minuteStr + ":" + secondStr);
        } else {
            textView.setText("PM " + hourStr + ":" + minuteStr + ":" + secondStr);
        }
    }
    private class ClickImageViewOnOff implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ImageView imgOnOffAlarm = (ImageView) v.findViewById(R.id.imgOnOffAlarm);
            if (!mIsStatusAlarm) {
                imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_on);
                mIsStatusAlarm = true;
            } else {
                imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_off);
                mIsStatusAlarm = false;
            }

        }
    }

    private class OnClickImgRingTone implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            playRingTone();
        }
    }

    private class OnClickSpinner implements
            AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            Log.d("Pos Int",arg2+"");
            Log.d("Pos Long",arg3+"");
            mPositionRingToneSelection = arg2;
        }

        public void onNothingSelected(AdapterView<?> arg0) {

        }
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


