package vn.asiantech.LearingEnglish.activities;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
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

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;
import vn.asiantech.LearingEnglish.fragments.TestResultingFragment_;
import vn.asiantech.LearingEnglish.fragments.TestingFragment_;
import vn.asiantech.LearingEnglish.models.ApplicationData;
import vn.asiantech.LearingEnglish.models.Question;

/**
 * Created by tantv on 28/08/2015.
 */

@SuppressWarnings("ALL")
@EActivity(R.layout.activity_testing)
public class TestingActivity extends FragmentActivity {

    private int mPositionCurrent;
    @Getter
    private int mLeftRight = 0;
    private ArrayList<Fragment> mFragmentTestings;
    private static boolean isCheckTimerStop = true;
    private long mSaveRemainingTime;
    private TestingAdapter mAdapterTesting;
    @Setter @Getter
    private boolean isCheckDisable;

    @Getter
    private long mRemainingTime;

    @Getter
    private int mFragmentCurrentDisplay = 0;

    @Getter
    private ArrayList<String> mListSelection;

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

    }

    @Click(R.id.btnSubmit)
    void mBtnSubmitClicked() {

        if (isCheckTimerStop) {
            mSaveRemainingTime = mRemainingTime;
        }
        isCheckTimerStop = false;
        mRlButtonBottom.setVisibility(View.INVISIBLE);
        mTvTimeLeft.setVisibility(View.INVISIBLE);
        mTvTimeLeftCopy.setVisibility(View.INVISIBLE);
        mTvTotalQuestion.setVisibility(View.INVISIBLE);
        mTvLeftQuestion.setVisibility(View.INVISIBLE);
        mTvLeftQuestionCopy.setVisibility(View.VISIBLE);
        mTvLeftQuestionCopy.setText("Total Time: " + convertTimeToString(300000 - mSaveRemainingTime));
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
            f = mAdapterTesting.getItem(0);
            //set for fragment 0
            if (f instanceof TestingFragment_) {
                ((TestingFragment_) f).disableRadioButtonGroup();
                ((TestingFragment_) f).setDisableRadioButtonGroup();
                ((TestingFragment_) f).setTextColorFragment(0);
                Toast.makeText(this, "Clicked" + mFragmentTestings.size(), Toast.LENGTH_SHORT).show();
            }

            //set for fragment 1
            f = mAdapterTesting.getItem(1);
            if (f instanceof TestingFragment_) {
                ((TestingFragment_) f).disableRadioButtonGroup();
                ((TestingFragment_) f).setTextColorFragment(1);
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
                        // Move Right
                       // Log.d("tag change page: ", "right " + mFragmentCurrentDisplay + mPositionCurrent);

                        Log.d("======================","========================");
                        mLeftRight = 1;
                        mPositionCurrent = mFragmentCurrentDisplay;
                        Log.d("mPositionCurrent R: ", mPositionCurrent+"");
                    } else if (mFragmentCurrentDisplay < mPositionCurrent) {
                        // Move Left
                        Log.d("======================","========================");
                        //Log.d("tag change page: ", "left " + mFragmentCurrentDisplay + mPositionCurrent);
                        mLeftRight = -1;
                        mPositionCurrent = mFragmentCurrentDisplay;
                        Log.d("mPositionCurrent L: ", mPositionCurrent+"");
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
     * get Question From Data
     */
    private void getQuestion() {
        mQuestionDatas = new ArrayList<Question>();
        ApplicationData.getDataQuestion(mQuestionDatas);

    }

    private void getListSelection() {
        mListSelection = new ArrayList<String>();
        ApplicationData.getSelectionTrue(mListSelection);

    }


    private String convertTimeToString(long time) {
        long min = (time / 1000) / 60;
        long sec = (time / 1000) % 60;
        if (min < 10 && (sec < 10)) {
            return ("0" + min + ":0" + sec);
        } else if (min < 10 && (sec >= 10)) {
            return ("0" + min + ":" + sec);
        } else if (min >= 10 && (sec < 10)) {
            return (min + ":0" + sec);
        } else {
            return (min + ":" + sec);
        }
    }

    /**
     * CountDown Timer
     */
    private void creatCountDown() {
        //10min = 600 sec = 600000ms
        new CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 10000) {
                    mTvTimeLeft.setTextColor(Color.RED);
                }
                mTvTimeLeft.setText(convertTimeToString(millisUntilFinished));
                mRemainingTime = millisUntilFinished;
            }

            public void onFinish() {
                mTvTimeLeft.setText("00:00");
                mProgressBarTimer.setVisibility(View.INVISIBLE);
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

}