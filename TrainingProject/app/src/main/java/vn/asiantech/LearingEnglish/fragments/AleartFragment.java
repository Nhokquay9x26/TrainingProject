package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.eventbus.AleartReceiver;

/**
 * Created by sunday on 04/09/2015.
 */
@EFragment(R.layout.fragment_aleart)
public class AleartFragment extends BaseFragment{
    Calendar mCal;
    int mHour;
    int mMinute;
    @ViewById(R.id.tvViewTime)
    TextView mTvViewTime;

    @ViewById(R.id.switch_aleart)
    SwitchCompat mSwitchCompat;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @AfterViews
    void setAleart(){
        getDefaultTimeSystem();
        mSwitchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                  /*  Long alearTime = new GregorianCalendar().getTimeInMillis() + 5*1000;
                    Intent aleartIntent = new Intent(getActivity(), AleartReceiver.class);
                    AlarmManager alarmManager = (AlarmManager)
                           getActivity().getSystemService(Context.ALARM_SERVICE);
                    alarmManager.set(AlarmManager.RTC_WAKEUP, alearTime,
                            PendingIntent.getBroadcast(getActivity(), 1, aleartIntent, PendingIntent.FLAG_UPDATE_CURRENT));
*/


                }else {
                    //Toast.makeText(getActivity(), "Turn Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getDefaultTimeSystem(){
        mCal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String strTime = simpleDateFormat.format(mCal.getTime());
        mTvViewTime.setText(strTime);
        simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        mTvViewTime.setTag(simpleDateFormat.format(mCal.getTime()));
    }
    @Click(R.id.btnAlarm)
    void clickBtnAlarm(){
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                String s = hour + ":" + minute;
                mHour = hour;
                mMinute = minute;
                int countHour = hour;
                if(countHour > 12){
                    countHour -= 12;
                }
                mTvViewTime.setText(countHour + ":" + minute + (hour > 12 ?" PM" : " AM"));
                mTvViewTime.setTag(s);

                mCal.set(Calendar.HOUR_OF_DAY, hour);
                mCal.set(Calendar.MINUTE, minute);
            }
        };

        String s=mTvViewTime.getTag()+"";
        String strArr[]=s.split(":");
        int intHour=Integer.parseInt(strArr[0]);
        int intMinute=Integer.parseInt(strArr[1]);
        TimePickerDialog time = new TimePickerDialog(getActivity(), callback, intHour, intMinute, true);
        time.show();

    }


}
