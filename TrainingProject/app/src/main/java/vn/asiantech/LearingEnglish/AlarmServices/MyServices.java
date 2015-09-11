package vn.asiantech.LearingEnglish.AlarmServices;

import android.app.Dialog;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.SettingFragment;

/**
 * Created by mrson on 09/09/2015.
 */
public class MyServices extends Service {
    private MyReceiver mMyReceiver;
    private MediaPlayer player;
    private CountDownTimer mCountDownTimer;
    private int second = 0;
    private Handler mHandler = new Handler();
    private Runnable mRunable;
    private TextView mTvCancleDialog;
    private Dialog mDialog;

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

        mDialog = new Dialog(getApplicationContext());
        mDialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        mDialog.setContentView(R.layout.custom_dialog_alert_turn_off);
        mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        mDialog.setTitle("Alert");
        mDialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.ic_alert);
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
        stopService();
    }

    public void stopService() {
        try {
            if (player != null && player.isPlaying()) {
                player.pause();
                player.stop();
                player.release();
            }
            if (mCountDownTimer != null)
                mCountDownTimer.cancel();
            second = 0;
            mHandler.removeCallbacks(mRunable);
            if (mMyReceiver != null)
                unregisterReceiver(mMyReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                Log.d("vinhhlb", "" + second);
                if (second == maxTime) {
                    postDelaymer();
                    if (!mDialog.isShowing()) {
                        showDialogAlarmp();
                    }
                    mCountDownTimer.cancel();
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

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                if (intent.getExtras().getInt("KEY_MAX_VALUE") == 0) {
                    // turn off
                    Log.d("vinhhlb", "turn off");
                } else {
                    //turn on
                    Log.d("vinhhlb", "turn on");
                    setUpTimer(intent.getExtras().getInt("KEY_MAX_VALUE"));
                }
            }
        }
    }

    public void showDialogAlarmp() {
        if (!mDialog.isShowing())
            mDialog.show();
        mTvCancleDialog = (TextView) mDialog.findViewById(R.id.tvCancleDialog);
        mTvCancleDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog.isShowing()) {
                    stopSelf();
                    stopService();
                    if (mDialog.isShowing()) {
                        mDialog.dismiss();
                    }
                }
            }
        });
    }
}