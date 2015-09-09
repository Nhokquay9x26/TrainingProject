package vn.asiantech.LearingEnglish.activities;

import android.content.Intent;

import org.androidannotations.annotations.EActivity;

import java.util.Timer;
import java.util.TimerTask;
import vn.asiantech.LearingEnglish.R;

/**
 * @Author TanTv
 * Created by tantv on 27/08/2015.
 */

@SuppressWarnings("ALL")
@EActivity(R.layout.activity_splashscreen)
public class SplashScreenActivity extends BaseActionBarActivity {
    private Timer mTimer;
    private Intent mIntent;
    private boolean mIsExit;

    @Override
    void afterView() {

        //Call Method set Timer
        setTimer();
    }

    /**
     * set Timer Activity Timer = 3s
     */
    private void setTimer() {
        mTimer = new Timer();
        mIntent = new Intent(this, LoginActivity_.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mIntent.putExtra("Exit me", true);
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(mIntent);
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsExit){
            finish();
        }
        mIsExit = true;
    }
}
