package vn.asiantech.LearingEnglish.AlarmServices;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by mrson on 09/09/2015.
 */
public class MyServices extends Service {
    private MediaPlayer mPlayer;
    private CountDownTimer mCountDownTimer;
    private int mSecond = 0;
    private final Handler mHandler = new Handler();
    private Runnable mRunable;
    private Dialog mDialog;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

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
        stopService();
    }

    private void stopService() {
        try {
            if (mPlayer != null && mPlayer.isPlaying()) {
                mPlayer.pause();
                mPlayer.stop();
                mPlayer.release();
            }
            if (mCountDownTimer != null)
                mCountDownTimer.cancel();
            mSecond = 0;
            mHandler.removeCallbacks(mRunable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpTimer(final int maxTime) {
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        mCountDownTimer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (mSecond == maxTime) {
                    postDelaymer();
                    if (!mDialog.isShowing()) {
                        showDialogAlarmp();
                    }
                    mCountDownTimer.cancel();
                } else
                    mSecond++;
            }

            @Override
            public void onFinish() {
            }
        };
        mCountDownTimer.start();
    }


    private void postDelaymer() {
        mRunable = new Runnable() {
            public void run() {
                startAlarm();
            }
        };
        mHandler.postDelayed(mRunable, 1000);
    }


    private void startAlarm() {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mPlayer = MediaPlayer.create(getApplicationContext(), notification);
        if (mPlayer != null && !mPlayer.isPlaying())
            mPlayer.start();
    }


    private void showDialogAlarmp() {
        if (!mDialog.isShowing())
            mDialog.show();
        TextView mTvCancleDialog = (TextView) mDialog.findViewById(R.id.tvCancleDialog);
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