package vn.asiantech.LearingEnglish.activities;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;
import vn.asiantech.LearingEnglish.fragments.TestResultingFragment_;
import vn.asiantech.LearingEnglish.fragments.TestingFragment_;
import vn.asiantech.LearingEnglish.models.ApplicationData;
import vn.asiantech.LearingEnglish.models.FunctionModel;
import vn.asiantech.LearingEnglish.models.Question;

/**
 * Created by tantv on 28/08/2015.
 */

@SuppressWarnings("ALL")
@EActivity(R.layout.activity_testing)
public class TestingActivity extends FragmentActivity {
    private int mPositionCurrent;
    private ArrayList<Fragment> mFragmentTestings;
    private boolean mIsCheckTimerStop = true;
    private long mSaveRemainingTime;
    private TestingAdapter mAdapterTesting;
    private CallbackManager mCallbackManager;
    private LoginManager mLoginManager;

    @AfterViews
    void afterView() {
        mPositionCurrent = mViewPager.getCurrentItem();
        mTvTimeLeftCopy.setVisibility(View.INVISIBLE);
        mTvLeftQuestionCopy.setVisibility(View.INVISIBLE);
        getQuestion();
        getListSelection();
        mFragmentTestings = new ArrayList<Fragment>();
        newFragmentQuestion();
        mAdapterTesting = new TestingAdapter(getSupportFragmentManager(), mFragmentTestings);
        mViewPager.setAdapter(mAdapterTesting);
        creatCountDown();
        setOnClickViewPager();
        mNumberOfQuestion.setText("1 - " + mQuestionDatas.size());
        mTvTotalQuestion.setText("1/" + mQuestionDatas.size());
        mTvLeftQuestion.setText(mQuestionDatas.size() + "");
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    public void shareFaceBook() {
        if (isNetworkAvailable()) {
            final Context context = getBaseContext();
            List<String> permissionNeeds = Arrays.asList("publish_actions");
            FacebookSdk.sdkInitialize(getApplicationContext());
            mCallbackManager = CallbackManager.Factory.create();
            mLoginManager = LoginManager.getInstance();
            mLoginManager.logInWithPublishPermissions(this, permissionNeeds);
            mLoginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d("Success", "success");
                    sharePhotoToFacebook();
                }

                @Override
                public void onCancel() {
                    Log.d("Success", "on Cancel");
                }

                @Override
                public void onError(FacebookException error) {
                    Log.d("Error", "error");
                }
            });
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Warning !")
                    .setMessage("No conecting network .\nPlease ! Turn on mobile data or wifi .")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(R.drawable.ico_warning)
                    .show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void toastMakeText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    private void sharePhotoToFacebook() {
        try {
            Bitmap image = screenShot(mFrViewpager);
            ArrayList<Boolean> resultUser = FunctionModel.resultUser(mQuestionDatas, mListSelections);
            String share = "Your score: " + FunctionModel.countResultsTrue(resultUser) + "/" + resultUser.size();
            SharePhoto photo = new SharePhoto.Builder().setBitmap(image).setCaption(share).build();
            SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
            ShareApi.share(content, null);
            toastMakeText("Congratulation ! You just share your results on Timeline !");
        } catch (Exception e) {
            toastMakeText("Fail");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Click(R.id.btnSubmit)
    void mBtnSubmitClicked() {
        finshTest();
    }

    private void finshTest() {
        if (mIsCheckTimerStop) {
            mSaveRemainingTime = mRemainingTime;
        }
        mIsCheckTimerStop = false;
        mRlButtonBottom.setVisibility(View.INVISIBLE);
        mTvTimeLeft.setVisibility(View.INVISIBLE);
        mTvTimeLeftCopy.setVisibility(View.INVISIBLE);
        mTvTotalQuestion.setVisibility(View.INVISIBLE);
        mTvLeftQuestion.setVisibility(View.INVISIBLE);
        mTvLeftQuestionCopy.setVisibility(View.VISIBLE);
        mTvLeftQuestionCopy.setText("Total Time: " + FunctionModel.convertTimeToString(300000 - mSaveRemainingTime));
        mProgressBarTimer.setVisibility(View.INVISIBLE);
        TestResultingFragment_ fr = new TestResultingFragment_();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frViewpager, fr, "");
        fragmentTransaction.addToBackStack("ResultFragment");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            mViewPager.setCurrentItem(0, false);
            getFragmentManager().popBackStackImmediate();
            mRlButtonBottom.setVisibility(View.VISIBLE);
            mTvTotalQuestion.setVisibility(View.VISIBLE);
            mTvTimeLeftCopy.setVisibility(View.VISIBLE);
            mTvLeftQuestion.setVisibility(View.VISIBLE);
            mTvTimeLeft.setVisibility(View.INVISIBLE);
            mTvLeftQuestionCopy.setVisibility(View.INVISIBLE);
            mTvTimeLeftCopy.setText("00:00");
            mPositionCurrent = mViewPager.getCurrentItem();
            Fragment f;
            for (int i = 0; i < 2; i++) {
                f = mAdapterTesting.getItem(i);
                //set for fragment 0 and 1
                if (f instanceof TestingFragment_) {
                    ((TestingFragment_) f).disableRadioButtonGroup();
                    ((TestingFragment_) f).setDisableRadioButtonGroup();
                    ((TestingFragment_) f).setTextColorFragment(i);
                }
            }

        } else {
            super.onBackPressed();
        }

    }

    /**
     * Event When Click Button Previous
     */
    @Click(R.id.imgPrevious)
    void mImgPreviousClicked() {
        if (mFragmentCurrentDisplay != 0) {
            mViewPager.setCurrentItem(mFragmentCurrentDisplay - 1);
        }
    }

    /**
     * Event When Click Button Next
     */
    @Click(R.id.imgNext)
    void mImgNextClicked() {
        if (mFragmentCurrentDisplay != mQuestionDatas.size() - 1) {
            mViewPager.setCurrentItem(mFragmentCurrentDisplay + 1);
        }
    }

    /**
     * Event when slide or click on viewpager
     */
    private void setOnClickViewPager() {

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 0) {
                    mFragmentCurrentDisplay = mViewPager.getCurrentItem();
                    mNumberOfQuestion.setText((mFragmentCurrentDisplay + 1) + " - " + mQuestionDatas.size());
                    mTvTotalQuestion.setText((mFragmentCurrentDisplay + 1) + "/" + mQuestionDatas.size());
                    if (mFragmentCurrentDisplay > mPositionCurrent) {
                        mLeftRight = 1;
                        mPositionCurrent = mFragmentCurrentDisplay;
                    } else if (mFragmentCurrentDisplay < mPositionCurrent) {
                        mLeftRight = -1;
                        mPositionCurrent = mFragmentCurrentDisplay;
                    }
                }
            }
        });
    }

    /**
     * New Fragment from Number of Question
     */
    private void newFragmentQuestion() {
        TestingFragment_ objectTestingFragment_ = new TestingFragment_();
        for (int i = 0; i < mQuestionDatas.size(); i++) {
            int numberQuestion = i + 1;
            String question = mQuestionDatas.get(i).getQuestion();
            String selectionA = mQuestionDatas.get(i).getSelectionA();
            String selectionB = mQuestionDatas.get(i).getSelectionB();
            String selectionC = mQuestionDatas.get(i).getSelectionC();
            String selectionD = mQuestionDatas.get(i).getSelectionD();
            mFragmentTestings.add(objectTestingFragment_.newInstance(numberQuestion, question, selectionA, selectionB, selectionC, selectionD));
        }
    }

    /**
     * get Question Data
     */
    private void getQuestion() {
        mQuestionDatas = new ArrayList<Question>();
        mQuestionDatas = ApplicationData.getDataQuestion();
    }

    private void getListSelection() {
        mListSelections = new ArrayList<String>();
        mListSelections = ApplicationData.getSelectionTrue();

    }

    /**
     * CountDown Timer
     */
    private void creatCountDown() {
        //10min = 600 sec = 600000ms
        new CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                mRemainingTime = millisUntilFinished;
                if (millisUntilFinished < 10000) {
                    mTvTimeLeft.setTextColor(Color.RED);
                }
                mTvTimeLeft.setText(FunctionModel.convertTimeToString(millisUntilFinished));

            }

            public void onFinish() {
                mTvTimeLeft.setText("00:00");
                mProgressBarTimer.setVisibility(View.INVISIBLE);
                finshTest();
            }
        }.start();
    }

    /**
     * set text
     *
     * @param
     */
    public void setTextViewLeftQuestion(int number) {
        mTvLeftQuestion.setText(number + "");
    }

    @Getter
    private int mLeftRight = 0;

    @Setter
    @Getter
    private boolean mIsCheckDisable;

    @Getter
    private long mRemainingTime;

    @Getter
    private int mFragmentCurrentDisplay = 0;

    @Getter
    private ArrayList<String> mListSelections;

    @Getter
    private ArrayList<Question> mQuestionDatas;

    @ViewById(R.id.viewpager)
    ViewPager mViewPager;

    @ViewById(R.id.tvTimeLeft)
    TextView mTvTimeLeft;

    @ViewById(R.id.tvLeftQuestion)
    TextView mTvLeftQuestion;

    @ViewById(R.id.imgPrevious)
    ImageView mImgPrevious;

    @ViewById(R.id.imgNext)
    ImageView mImgNext;

    @ViewById(R.id.tvNumberOfQuestion)
    TextView mNumberOfQuestion;

    @ViewById(R.id.tvTotalQuestion)
    TextView mTvTotalQuestion;

    @ViewById(R.id.btnSubmit)
    Button mBtnSubmit;

    @ViewById(R.id.frViewpager)
    FrameLayout mFrViewpager;

    @ViewById(R.id.rlButtonBottom)
    RelativeLayout mRlButtonBottom;

    @ViewById(R.id.progressBarTimer)
    ProgressBar mProgressBarTimer;

    @ViewById(R.id.tvTimeLeftCopy)
    TextView mTvTimeLeftCopy;

    @ViewById(R.id.tvLeftQuestionCopy)
    TextView mTvLeftQuestionCopy;
}