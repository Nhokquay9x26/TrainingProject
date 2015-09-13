package vn.asiantech.LearingEnglish.fragments;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;
import vn.asiantech.LearingEnglish.models.AlarmDB;
import vn.asiantech.LearingEnglish.models.AlarmReceiver;


/**
 * @author tantv
 *         Created by tantv on 04/09/2015.
 */

@EFragment(R.layout.fragment_setting)
public class SettingFragment extends BaseFragment {
    private CountDownTimer mNewtimer;
    private ArrayList<Uri> mListUriRingTones;
    private int mPositionRingToneSelection;
    private PendingIntent mPendingIntent;
    private TimePicker mTimePicker;
    private EditText mEdtMessage;
    AlarmManager mAlarmManager;
    String[] listRingTones;//= new String[mListUriRingTones.size()];
    private Dialog mDialog;

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
    void afterView() {

        Intent lauchIntent = new Intent(getActivity(), AlarmReceiver.class);
        mPendingIntent = PendingIntent.getBroadcast(getActivity(), 0, lauchIntent, 0);
        mDialog = new Dialog(getActivity());
        mDialog.setContentView(R.layout.dialog_alarm);
        mDialog.setTitle("Alarm Setting");
    }


    private void playRingTone() {
        if (mListUriRingTones.size() > 0) {
            Log.d("Tag", "CLick Play");
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
        if (getActivity() instanceof MainActivity_) {
            mListUriRingTones = ((MainActivity_) getActivity()).getMListUriRingTones();
        }
        listRingTones = getTitleRingTone(mListUriRingTones);

        final Spinner spinnerListTone = (Spinner) mDialog.findViewById(R.id.spinnerListTone);
        showListOnSpinner(spinnerListTone, listRingTones);
        mEdtMessage = (EditText) mDialog.findViewById(R.id.edtMessage);
        mTimePicker = (TimePicker) mDialog.findViewById(R.id.timePicker);
        mTimePicker.setIs24HourView(true);
        final TextView tvTimeCurrent = (TextView) mDialog.findViewById(R.id.tvTimeCurrent);
        final ImageView mBtnPlayRingTone = (ImageView) mDialog.findViewById(R.id.btnPlayRingTone);
        mBtnPlayRingTone.setOnClickListener(new OnClickImgRingTone());
        mNewtimer = new CountDownTimer(1000000000, 1000) {

            public void onTick(long millisUntilFinished) {
                showTimeCurrentToTextView(tvTimeCurrent);
            }

            public void onFinish() {

            }
        };
        mNewtimer.start();
        final ImageView imgOnOffAlarm = (ImageView) mDialog.findViewById(R.id.imgOnOffAlarm);
        if (AlarmDB.first(AlarmDB.class)!=null) {
            if (AlarmDB.first(AlarmDB.class).getStatus()==0){
                imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_off);
            } else {
                imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_on);
            }
        } else {
            imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_off);

        }
        imgOnOffAlarm.setOnClickListener(new ClickImageViewOnOff());
        if (!mDialog.isShowing()) mDialog.show();

    }

    private void showTimeCurrentToTextView(TextView textView) {
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
            int numberTimePicker = mTimePicker.getCurrentHour() * 60 + mTimePicker.getCurrentMinute();
            Calendar c = Calendar.getInstance();
            int AM_orPM = c.get(Calendar.AM_PM);
            int hourRealTime = c.get(Calendar.HOUR);
            int minuteRealTime = c.get(Calendar.MINUTE);
            int secondRealTime = c.get(Calendar.SECOND);
            if (AM_orPM != 0) {
                hourRealTime += 12;
            }
            int numberTimeCurrent = hourRealTime * 60 + minuteRealTime;
            long interval;
            int argDetect = numberTimePicker - numberTimeCurrent;
            if (argDetect > 0) {
                interval = argDetect * 1000 * 60 - secondRealTime * 1000;
            } else {
                interval = (24 * 60 - argDetect) * 1000 * 60 - secondRealTime * 1000;
            }


            AlarmDB alarmDB;

            mAlarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            if (AlarmDB.first(AlarmDB.class) != null) {
                if (AlarmDB.first(AlarmDB.class).getStatus() == 0) {
                    alarmDB = new AlarmDB(mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute(), mEdtMessage.getText().toString(), 1, mPositionRingToneSelection);
                    //On Alarm
                    AlarmDB.deleteAll(AlarmDB.class);
                    alarmDB.setId(1l);
                    alarmDB.save();
                    Toast.makeText(getActivity(), "Scheduled: " + (interval/1000000)+"s", Toast.LENGTH_SHORT).show();
                    mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + interval, interval, mPendingIntent);
                    imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_on);
                } else {

                    //Off Alarm
                    AlarmDB.deleteAll(AlarmDB.class);
                    Toast.makeText(getActivity(), "Canceled", Toast.LENGTH_SHORT).show();
                    mAlarmManager.cancel(mPendingIntent);
                    imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_off);
                }
            } else {
                alarmDB = new AlarmDB(mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute(), mEdtMessage.getText().toString(), 1, mPositionRingToneSelection);
                //On Alarm
                AlarmDB.deleteAll(AlarmDB.class);
                alarmDB.setId(1l);
                alarmDB.save();
                Toast.makeText(getActivity(), "Scheduled: " + interval, Toast.LENGTH_SHORT).show();
                mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime() + interval, interval, mPendingIntent);
                imgOnOffAlarm.setImageResource(R.drawable.ico_btn_alarm_on);
            }

        }
    }

    private class OnClickImgRingTone implements View.OnClickListener {

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


