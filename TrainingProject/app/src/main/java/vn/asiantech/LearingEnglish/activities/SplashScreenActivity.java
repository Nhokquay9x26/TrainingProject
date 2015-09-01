package vn.asiantech.LearingEnglish.activities;

import android.content.Intent;
import android.view.animation.Animation;
import android.widget.ImageView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.AnimationRes;

import vn.asiantech.LearingEnglish.R;

/**
 * Created by sunday on 27/08/2015.
 */
@EActivity(R.layout.activity_splash_screen)
public class SplashScreenActivity extends BaseActionBarActivity implements Animation.AnimationListener{
    public static final int TIME_DELAY = 3000;

    @ViewById(R.id.img_logo)
    ImageView mImgLogo;

    @AnimationRes(R.anim.image_elastic)
    Animation  mAnmElastic;

    @Override
    void afterView() {
        loadAnimation();
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(TIME_DELAY);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(getBaseContext(), LoginActivity_.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        thread.start();
    }

    private void loadAnimation(){
        mAnmElastic.setAnimationListener(this);
        mImgLogo.startAnimation(mAnmElastic);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
