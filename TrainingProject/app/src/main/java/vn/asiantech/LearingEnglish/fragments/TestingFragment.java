package vn.asiantech.LearingEnglish.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.TestingActivity_;
import vn.asiantech.LearingEnglish.models.Question;

/**
 * Created by tantv on 28/08/2015.
 */

@EFragment(R.layout.fragment_testing)
public class TestingFragment extends Fragment {
    private ArrayList<Question> mArrQuestions;
    private int mFragmentCurrentDisplay = 0;

    public static final String EXTRA_MESSAGE_QUESTION = "EXTRA_MESSAGE_QUESTION";
    public static final String EXTRA_MESSAGE_NUMBER_QUESTION = "EXTRA_MESSAGE_NUMBER_QUESTION";
    public static final String EXTRA_MESSAGE_SELECTIONA = "EXTRA_MESSAGE_SELECTIONA";
    public static final String EXTRA_MESSAGE_SELECTIONB = "EXTRA_MESSAGE_SELECTIONB";
    public static final String EXTRA_MESSAGE_SELECTIONC = "EXTRA_MESSAGE_SELECTIONC";
    public static final String EXTRA_MESSAGE_SELECTIOND = "EXTRA_MESSAGE_SELECTIOND";

    @ViewById(R.id.tvQuestionTitle)
    TextView mTvQuestionTitle;

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
        String space = "                   ";
        int numberQuestion = getArguments().getInt(EXTRA_MESSAGE_NUMBER_QUESTION);
        String question = getArguments().getString(EXTRA_MESSAGE_QUESTION);
        String selectionA = getArguments().getString(EXTRA_MESSAGE_SELECTIONA);
        String selectionB = getArguments().getString(EXTRA_MESSAGE_SELECTIONB);
        String selectionC = getArguments().getString(EXTRA_MESSAGE_SELECTIONC);
        String selectionD = getArguments().getString(EXTRA_MESSAGE_SELECTIOND);
        mTvQuestionTitle.setText("Question " + numberQuestion + " : ");
        mTvDetailQuestion.setText(space + question);
        mRbA.setText(selectionA);
        mRbB.setText(selectionB);
        mRbC.setText(selectionC);
        mRbD.setText(selectionD);

        onCheckRadioGroup();

    }

    public void onCheckRadioGroup() {
        if (getActivity() instanceof TestingActivity_) {
           mFragmentCurrentDisplay =  ((TestingActivity_) getActivity()).getMFragmentCurrentDisplay();
            mArrQuestions = ((TestingActivity_) getActivity()).getMArrQuestionDatas();


        }

        mRbGroupSelection.setOnCheckedChangeListener
                (new RadioGroup.OnCheckedChangeListener() {
                     @Override
                     public void onCheckedChanged(RadioGroup group, int checkedId) {
                         //Toast.makeText(getActivity(),"group"+group+"|"+"checkid:"+checkedId,Toast.LENGTH_SHORT).show();
                         int selectionUser = 0;
                         if (checkedId == R.id.rbA){
                             selectionUser = 1;
                             //Toast.makeText(getActivity(),"A",Toast.LENGTH_SHORT).show();
                         }
                         else
                         if (checkedId == R.id.rbB){
                             selectionUser = 2;

                             //Toast.makeText(getActivity(),"B",Toast.LENGTH_SHORT).show();
                         }
                         if (checkedId == R.id.rbC){
                             selectionUser = 3;
                             //Toast.makeText(getActivity(),"C",Toast.LENGTH_SHORT).show();
                         }
                         if (checkedId == R.id.rbD){
                             selectionUser = 4;
                             //Toast.makeText(getActivity(),"D",Toast.LENGTH_SHORT).show();
                         }
                         mArrQuestions.get(mFragmentCurrentDisplay+1).setSelectionUser(selectionUser);
                     }
                 }
                );
    }
}
