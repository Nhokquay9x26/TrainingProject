package vn.asiantech.LearingEnglish.AlarmServices;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import vn.asiantech.LearingEnglish.fragments.SettingFragment;

/**
 * Created by mrson on 09/09/2015.
 */
public class MyServices extends Service {
    private MyReceiver mMyReceiver;
    private int i = 0;
    private int limit = 100;
    private MediaPlayer player;
    private CountDownTimer mCountDownTimer;
    private int second = 0;
    private Handler mHandler = new Handler();
    private Runnable mRunable;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intentFilter = new IntentFilter(SettingFragment.INTENT_FILTER);
        if (mMyReceiver == null) {
            mMyReceiver = new MyReceiver();
            registerReceiver(mMyReceiver, intentFilter);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            setUpTimer(intent.getExtras().getInt("KEY_MAX_VALUE"));
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("vinhhlb", "stop services");


        if (player != null && player.isPlaying()) {
            Log.d("vinhhlb", "cancel player");
            player.pause();
            player.stop();
            player.release();
        }
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();

        mHandler.removeCallbacks(mRunable);
        if (mMyReceiver != null)
            unregisterReceiver(mMyReceiver);


    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private void setUpTimer(final int maxTime) {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        mCountDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (second == maxTime) {
                    postDelaymer();
                } else
                    second++;
            }

            @Override
            public void onFinish() {
            }
        };
        mCountDownTimer.start();
    }


    public void postDelaymer() {
        mRunable = new Runnable() {
            public void run() {
                startAlarm();
            }
        };

        mHandler.postDelayed(mRunable, 1000);
    }


    public void startAlarm() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        player = MediaPlayer.create(getApplicationContext(), notification);
        if (player != null && !player.isPlaying())
            player.start();
    }

    public void stopAlarm() {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        if (player != null && player.isPlaying()) {
            player.stop();
            player.release();
        }
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (intent.getExtras().getInt("KEY_MAX_VALUE") == 0) {
                    // turn off
                    Log.d("vinhhlb", "turn off");
                    stopAlarm();
                } else {
                    //turn on
                    Log.d("vinhhlb", "turn on");
                    setUpTimer(intent.getExtras().getInt("KEY_MAX_VALUE"));
                }
            }
        }
    }

}
