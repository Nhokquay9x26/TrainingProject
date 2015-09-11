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

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.TestingAdapter;
import vn.asiantech.LearingEnglish.fragments.TestResultingFragment_;
import vn.asiantech.LearingEnglish.fragments.TestingFragment_;
import vn.asiantech.LearingEnglish.models.ApplicationData;
import vn.asiantech.LearingEnglish.models.ListQuestion;
import vn.asiantech.LearingEnglish.models.QuestionData;
import vn.asiantech.LearingEnglish.models.SomeOtherFunction;
import vn.asiantech.LearingEnglish.network.Api;
import vn.asiantech.LearingEnglish.network.core.Callback;

/**
 * Created by tantv on 28/08/2015.
 */

@SuppressWarnings("ALL")
@EActivity(R.layout.activity_testing)
public class TestingActivity extends FragmentActivity {
    private static final String PERMISSION = "publish_actions";
    private CallbackManager mCallbackManager;
    private ShareDialog mShareDialog;
    private ProfileTracker mProfileTracker;
    private AccessTokenTracker mAccessTokenTracker;
    public static AccessToken mAccessToken;
    private int mPositionCurrent;
    private ArrayList<Fragment> mFragmentTestings;
    private boolean mIsCheckTimerStop = true;
    private long mSaveRemainingTime;
    private TestingAdapter mAdapterTesting;

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
        createCountDown();
        setOnClickViewPager();
        mNumberOfQuestion.setText("1 - " + mQuestionDataDatas.size());
        mTvTotalQuestion.setText("1/" + mQuestionDataDatas.size());
        mTvLeftQuestion.setText(mQuestionDataDatas.size()+"");
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    //login
    private void login() {
        List<String> permissionNeeds = Arrays.asList("publish_actions");
        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithPublishPermissions(this, permissionNeeds);
        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("user_friends", "user_photos", "read_custom_friendlists"));
        LoginManager.getInstance().registerCallback(mCallbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        sharePhotoToFacebook();
                        AccessToken accessToken = loginResult.getAccessToken();
                        Profile profile = Profile.getCurrentProfile();
                        if (profile != null) {
                            mAccessToken = accessToken;
                        } else {
                            Log.d("vinhhlb", "profile null");
                        }
                    }

                    @Override
                    public void onCancel() {
                        Log.d("vinhhlb", "onCancel ");
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("vinhhlb", "onError " + exception.getMessage());
                    }
                });
    }

    public void shareFaceBook() {
        if (isNetworkAvailable()) {
            if (hasPublishPermission()) {
                login();
            } else {
                // We need to get new permissions, then complete the action when we get called back.
                LoginManager.getInstance().logInWithPublishPermissions(
                        this,
                        Arrays.asList(PERMISSION));
            }

        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Warning !")
                    .setMessage("No conecting network .\nPlease ! Turn on mobile data or wifi .")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    public void toastMakeText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private void sharePhotoToFacebook() {
        try {
            Bitmap image = screenShot(mFrViewpager);
            ArrayList<Boolean> resultUser = SomeOtherFunction.resultUser(mQuestionDataDatas, mListSelection);
            String share = "Your result: "+SomeOtherFunction.countResultsTrue(resultUser)+"/"+ resultUser.size();
            SharePhoto photo = new SharePhoto.Builder().setBitmap(image).setCaption(share).build();
            SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
            ShareApi.share(content, null);
            toastMakeText("Congratulation ! You just share your results on Timeline !");
        } catch (Exception e) {
            toastMakeText("Fail");
        }
    }

    public Bitmap screenShot(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
                view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }

    @Click(R.id.btnSubmit)
    void mBtnSubmitClicked() {
        if (mIsCheckTimerStop) {
            mSaveRemainingTime = mRemainingTime;
        }
        mIsCheckTimerStop = false;
        //INVISIBLE CONTROLL
        mRlButtonBottom.setVisibility(View.INVISIBLE);
        mTvTimeLeft.setVisibility(View.INVISIBLE);
        mTvTimeLeftCopy.setVisibility(View.INVISIBLE);
        mTvTotalQuestion.setVisibility(View.INVISIBLE);
        mTvLeftQuestion.setVisibility(View.INVISIBLE);
        mTvLeftQuestionCopy.setVisibility(View.VISIBLE);
        mTvLeftQuestionCopy.setText("Total Time: " + convertTimeToString(300000 - mSaveRemainingTime));
        mProgressBarTimer.setVisibility(View.INVISIBLE);

        //NEW FRAGMENT
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
        if (mFragmentCurrentDisplay != mQuestionDataDatas.size() - 1) {
            mViewPager.setCurrentItem(mFragmentCurrentDisplay + 1);
        }
    }

    //check has public Permission
    private boolean hasPublishPermission() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && accessToken.getPermissions().contains("publish_actions");
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
                    mNumberOfQuestion.setText((mFragmentCurrentDisplay + 1) + " - " + mQuestionDataDatas.size());
                    mTvTotalQuestion.setText((mFragmentCurrentDisplay + 1) + "/" + mQuestionDataDatas.size());
                    if (mFragmentCurrentDisplay > mPositionCurrent) {
                        // Move Right
                        mLeftRight = 1;
                        mPositionCurrent = mFragmentCurrentDisplay;
                        Log.d("mPositionCurrent R: ", mPositionCurrent + "");
                    } else if (mFragmentCurrentDisplay < mPositionCurrent) {
                        // Move Left
                        mLeftRight = -1;
                        mPositionCurrent = mFragmentCurrentDisplay;
                        Log.d("mPositionCurrent L: ", mPositionCurrent + "");
                    }
                }
            }
        });
    }



    /**
     * New Fragment from Number of QuestionData
     */
    private void newFragmentQuestion() {
        TestingFragment_ objectTestingFragment_ = new TestingFragment_();
        for (int i = 0; i < mQuestionDataDatas.size(); i++) {
            int numberQuestion = i + 1;
            String question = mQuestionDataDatas.get(i).getQuestion();
            String selectionA = mQuestionDataDatas.get(i).getSelectionA();
            String selectionB = mQuestionDataDatas.get(i).getSelectionB();
            String selectionC = mQuestionDataDatas.get(i).getSelectionC();
            String selectionD = mQuestionDataDatas.get(i).getSelectionD();
            mFragmentTestings.add(objectTestingFragment_.newInstance(numberQuestion, question, selectionA, selectionB, selectionC, selectionD));
        }
    }

    /**
     * get QuestionData From Data
     */
    private void getQuestion() {
        mQuestionDataDatas = new ArrayList<QuestionData>();
        String api = "http://172.16.100.115:8080/internship/api/index.php/";
        RestAdapter adapter = new RestAdapter.Builder().setEndpoint(api).build();
        Api testRetrofit = adapter.create(Api.class);
        testRetrofit.getQuestion(new Callback<ListQuestion>() {
            @Override
            public void success(ListQuestion listQuestion) {
                QuestionData question;
                for (int i = 0; i < listQuestion.getQuestion().size(); i++) {
//                    String selectionA = listQuestion.getQuestion().get(i).getAnswerA()
                            ;
//                    question = new QuestionData);
//                    mQuestionDataDatas.add(question);
                }
            }

            @Override
            public void failure(RetrofitError error, vn.asiantech.LearingEnglish.network.Error myError) {

            }
        });


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
    private void createCountDown() {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    /**
     * set text
     *
     * @param
     */
    public void setTextViewLeftQuestion(int number) {
        mTvLeftQuestion.setText(number + "");
    }

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

    @Getter
    private ArrayList<QuestionData> mQuestionDataDatas;
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
    private ArrayList<String> mListSelection;
}