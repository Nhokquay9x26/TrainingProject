package vn.asiantech.LearingEnglish.models;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.WindowManager;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tantv on 12/09/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private MediaPlayer mPlayer;
    private CountDownTimer mCountDownTimer;
    private AlertDialog mAlertDialog;
    private AlarmDB mAlarm;
    private int mPosRingTone;


    @Override
    public void onReceive(final Context context, Intent intent) {
        ArrayList<Uri> alarms = new ArrayList<>();
        RingtoneManager ringtoneMgr = new RingtoneManager(context);
        ringtoneMgr.setType(RingtoneManager.TYPE_ALARM);
        Cursor alarmsCursor = ringtoneMgr.getCursor();
        int alarmsCount = alarmsCursor.getCount();
        if (alarmsCount == 0 && !alarmsCursor.moveToFirst()) {
        } else {
            while (!alarmsCursor.isAfterLast() && alarmsCursor.moveToNext()) {
                int currentPosition = alarmsCursor.getPosition();
                alarms.add(ringtoneMgr.getRingtoneUri(currentPosition));
            }
            alarmsCursor.close();
        }

        if (AlarmDB.first(AlarmDB.class) != null) {
            mAlarm = AlarmDB.first(AlarmDB.class);
            mPosRingTone = mAlarm.getPos();
        }
        mPlayer = new MediaPlayer();
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mPlayer.setDataSource(context, alarms.get(mPosRingTone));
            mPlayer.prepare();
        } catch (IllegalArgumentException e) {
        } catch (SecurityException e) {
        } catch (IllegalStateException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        if ("".equals(mAlarm.getMessage())) mAlarm.setMessage("LEARNING ENGLISH MORE AND MORE WITH ME!");
        mAlertDialog = new AlertDialog.Builder(context)
                .setTitle("Reminder")
                .setMessage(mAlarm.getMessage() + "")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mCountDownTimer.cancel();
                            }
                        }
                )
                .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {


                            public void onClick(DialogInterface dialog, int which) {
                                mAlarm.delete();
                                mPlayer.stop();
                                Intent intent = new Intent(context, AlarmReceiver.class);
                                PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
                                AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                                alarmManager.cancel(sender);
                                mCountDownTimer.cancel();
                                dialog.dismiss();
                            }
                        }
                )
                .create();
        mCountDownTimer = new CountDownTimer(20000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mPlayer.start();
            }

            @Override
            public void onFinish() {
                mPlayer.stop();
                mAlertDialog.dismiss();
            }
        }.start();
        mAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        mAlertDialog.show();
    }

}
