package vn.asiantech.LearingEnglish.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.TestingActivity_;
import vn.asiantech.LearingEnglish.models.QuestionData;
import vn.asiantech.LearingEnglish.models.SomeOtherFunction;

/**
 * Created by tantv on 28/08/2015.
 */

@SuppressWarnings("ALL")
@EFragment(R.layout.fragment_testing)
public class TestingFragment extends Fragment {
    private ArrayList<QuestionData> mArrQuestionDatas;

    private int mFragmentCurrentDisplay = 0;
    private int mLeftRight = 0;
    private boolean isCheckDisable;
    private ArrayList<String> mListSelection;
    public static final String EXTRA_MESSAGE_QUESTION = "EXTRA_MESSAGE_QUESTION";
    public static final String EXTRA_MESSAGE_NUMBER_QUESTION = "EXTRA_MESSAGE_NUMBER_QUESTION";
    public static final String EXTRA_MESSAGE_SELECTIONA = "EXTRA_MESSAGE_SELECTIONA";
    public static final String EXTRA_MESSAGE_SELECTIONB = "EXTRA_MESSAGE_SELECTIONB";
    public static final String EXTRA_MESSAGE_SELECTIONC = "EXTRA_MESSAGE_SELECTIONC";
    public static final String EXTRA_MESSAGE_SELECTIOND = "EXTRA_MESSAGE_SELECTIOND";

    @ViewById(R.id.tvDetailQuestion)
    TextView mTvDetailQuestion;

    @ViewById(R.id.rbA)
    RadioButton mRbA;

    @ViewById(R.id.rbB)
    RadioButton mRbB;

    @ViewById(R.id.rbC)
    RadioButton mRbC;

    @ViewById(R.id.rbD)
    RadioButton mRbD;

    @ViewById(R.id.rbGroupSelection)
    RadioGroup mRbGroupSelection;


    public TestingFragment_ newInstance(int numberQuestion, String question, String selectionA, String selectionB,
                                        String selectionC, String selectionD) {

        TestingFragment_ f = new TestingFragment_();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_MESSAGE_NUMBER_QUESTION, numberQuestion);
        bundle.putString(EXTRA_MESSAGE_QUESTION, question);
        bundle.putString(EXTRA_MESSAGE_SELECTIONA, selectionA);
        bundle.putString(EXTRA_MESSAGE_SELECTIONB, selectionB);
        bundle.putString(EXTRA_MESSAGE_SELECTIONC, selectionC);
        bundle.putString(EXTRA_MESSAGE_SELECTIOND, selectionD);
        f.setArguments(bundle);
        return f;
    }


    @AfterViews
    void afterView() {
        if (getActivity() instanceof TestingActivity_) {
            isCheckDisable = ((TestingActivity_) getActivity()).isMIsCheckDisable();
        }

        if (isCheckDisable) {
            disableRadioButtonGroup();
            if (getActivity() instanceof TestingActivity_) {
                mListSelection = ((TestingActivity_) getActivity()).getMListSelection();
                mFragmentCurrentDisplay = ((TestingActivity_) getActivity()).getMFragmentCurrentDisplay();
                mLeftRight = ((TestingActivity_) getActivity()).getMLeftRight();
                if (mLeftRight == -1) {
                    Log.d("Left", mFragmentCurrentDisplay + "");
                    if (mFragmentCurrentDisplay != 0) {
                        setTextColorFragment(mFragmentCurrentDisplay - 1);
                    }
                } else if (mLeftRight == 1) {
                    if (mFragmentCurrentDisplay != mListSelection.size() - 1) {
                        Log.d("Right", mFragmentCurrentDisplay + "");
                        setTextColorFragment(mFragmentCurrentDisplay + 1);
                    }
                }
            }
        }

        int numberQuestion = getArguments().getInt(EXTRA_MESSAGE_NUMBER_QUESTION);
        String question = getArguments().getString(EXTRA_MESSAGE_QUESTION);
        String selectionA = getArguments().getString(EXTRA_MESSAGE_SELECTIONA);
        String selectionB = getArguments().getString(EXTRA_MESSAGE_SELECTIONB);
        String selectionC = getArguments().getString(EXTRA_MESSAGE_SELECTIONC);
        String selectionD = getArguments().getString(EXTRA_MESSAGE_SELECTIOND);
        SomeOtherFunction.changeTextViewToTwoColor(mTvDetailQuestion, "QuestionData " + numberQuestion + ": ", question, Color.RED, Color.BLACK);
        mRbA.setText(selectionA);
        mRbB.setText(selectionB);
        mRbC.setText(selectionC);
        mRbD.setText(selectionD);
        onCheckRadioGroup();

    }

    /**
     * Set Color for fragment position i
     *
     * @param i
     */
    public void setTextColorFragment(int i) {
        if (getActivity() instanceof TestingActivity_) {
            mListSelection = ((TestingActivity_) getActivity()).getMListSelection();
            setTextColorRadioButtonTrue(mListSelection.get(i));
        }

    }

    /**
     * SetText Color for text on radio button
     *
     * @param textRb
     */
    private void setTextColorRadioButtonTrue(String textRb) {
        switch (textRb) {
            case "A": {
                mRbA.setTextColor(Color.RED);
                break;
            }
            case "B": {
                mRbB.setTextColor(Color.RED);
                break;
            }
            case "C": {
                mRbC.setTextColor(Color.RED);
                break;
            }
            case "D": {
                mRbD.setTextColor(Color.RED);
                break;
            }
            default: {
                mRbA.setTextColor(Color.RED);
                mRbB.setTextColor(Color.RED);
                mRbC.setTextColor(Color.RED);
                mRbD.setTextColor(Color.RED);
                break;
            }


        }
    }

    private int countSelectUser() {
        int count = 0;
        for (int i = 0; i < mArrQuestionDatas.size(); i++) {
            if (mArrQuestionDatas.get(i).getSelectionUser() == 0) {
                count++;
            }
        }
        return count;
    }

    public void setDisableRadioButtonGroup() {
        if (getActivity() instanceof TestingActivity_) {
            ((TestingActivity_) getActivity()).setMIsCheckDisable(true);

        }
    }

    /**
     * disable radiobutton group
     */
    public void disableRadioButtonGroup() {
        for (int i = 0; i < mRbGroupSelection.getChildCount(); i++) {
            mRbGroupSelection.getChildAt(i).setEnabled(false);
        }
    }


    /**
     * Event when click radio button
     */
    public void onCheckRadioGroup() {

        mRbGroupSelection.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                                         @Override
                                                         public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                             if (getActivity() instanceof TestingActivity_) {
                                                                 mFragmentCurrentDisplay = ((TestingActivity_) getActivity()).getMFragmentCurrentDisplay();
                                                                 mArrQuestionDatas = ((TestingActivity_) getActivity()).getMQuestionDataDatas();
                                                             }

                                                             int selectionUser = 0;
                                                             switch (checkedId) {

                                                                 case R.id.rbA: {
                                                                     selectionUser = 1;
                                                                     mArrQuestionDatas.get(mFragmentCurrentDisplay).setSelectionUser(selectionUser);
                                                                     if (getActivity() instanceof TestingActivity_) {
                                                                         ((TestingActivity_) getActivity()).setTextViewLeftQuestion(countSelectUser());
                                                                     }
                                                                     break;
                                                                 }
                                                                 case R.id.rbB: {
                                                                     selectionUser = 2;
                                                                     mArrQuestionDatas.get(mFragmentCurrentDisplay).setSelectionUser(selectionUser);
                                                                     if (getActivity() instanceof TestingActivity_) {
                                                                         ((TestingActivity_) getActivity()).setTextViewLeftQuestion(countSelectUser());
                                                                     }
                                                                     break;
                                                                 }
                                                                 case R.id.rbC: {
                                                                     selectionUser = 3;
                                                                     mArrQuestionDatas.get(mFragmentCurrentDisplay).setSelectionUser(selectionUser);
                                                                     if (getActivity() instanceof TestingActivity_) {
                                                                         ((TestingActivity_) getActivity()).setTextViewLeftQuestion(countSelectUser());
                                                                     }
                                                                     break;
                                                                 }
                                                                 case R.id.rbD: {
                                                                     selectionUser = 4;
                                                                     mArrQuestionDatas.get(mFragmentCurrentDisplay).setSelectionUser(selectionUser);
                                                                     if (getActivity() instanceof TestingActivity_) {
                                                                         ((TestingActivity_) getActivity()).setTextViewLeftQuestion(countSelectUser());
                                                                     }
                                                                     break;
                                                                 }
                                                                 default:
                                                             }
                                                         }
                                                     }
        );
    }
}
