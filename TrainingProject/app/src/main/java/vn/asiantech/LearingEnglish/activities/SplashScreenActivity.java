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

/**
 * Created by tantv on 27/08/2015.
 */

@EActivity(R.layout.activity_splashscreen)
public class SplashScreenActivity extends BaseActionBarActivity {
    private Timer mTimer;
    private Intent mIntent;
    private AnimationDrawable mMyAnimationDrawable1;

    @ViewById(R.id.imgIncrementingBoxView)
    ImageView mImgincrementingBoxView;

    @Override
    void afterView() {
        getSupportActionBar().hide();
        //********* box incrementing ***********
        mImgincrementingBoxView.setVisibility(View.GONE);
        //--------------------------------------
        //Call Method set Timer
        setTimer();

    }

    /**
     * set Timer Activity Timer = 3s
     */

    private void setTimer() {
        mTimer = new Timer();
        mIntent = new Intent(this, TestingActivity_.class);
        mImgincrementingBoxView.setVisibility(View.VISIBLE);
        incrementalHorizontalLoading();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                startActivity(mIntent);
            }
        }, 3000);
    }

    public void incrementalHorizontalLoading() {
        mMyAnimationDrawable1 = (AnimationDrawable) mImgincrementingBoxView.getDrawable();
        mMyAnimationDrawable1.start();
    }

}
