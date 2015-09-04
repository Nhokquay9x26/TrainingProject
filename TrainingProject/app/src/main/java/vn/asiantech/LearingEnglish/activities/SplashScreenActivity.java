package vn.asiantech.LearingEnglish.activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.Timer;
import java.util.TimerTask;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.fragments.TestingFragment_;

/**
 * @Author TanTv
 * Created by tantv on 27/08/2015.
 */

@EActivity(R.layout.activity_splashscreen)
public class SplashScreenActivity extends BaseActionBarActivity {
    private Timer mTimer;
    private Intent mIntent;
    private boolean isExit ;

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
        if (isExit){
            finish();
        }
        isExit = true;
    }
}
