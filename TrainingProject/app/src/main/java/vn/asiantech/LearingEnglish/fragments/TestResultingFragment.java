package vn.asiantech.LearingEnglish.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.TestingActivity_;
import vn.asiantech.LearingEnglish.adapter.TestResultingAdapter;
import vn.asiantech.LearingEnglish.models.ApplicationData;
import vn.asiantech.LearingEnglish.models.QuestionData;
import vn.asiantech.LearingEnglish.models.SomeOtherFunction;

/**
 * Created by tantv on 01/09/2015.
 */

@SuppressWarnings("ALL")
@EFragment(R.layout.fragment_test_resulting)
public class TestResultingFragment extends Fragment {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<QuestionData> mQuestionDataDatas;
    private ArrayList<String> mListSelections;
    private ArrayList<Boolean> mIsResultUser;

    @ViewById(R.id.recycleViewResult)
    RecyclerView mRecycleViewResult;

    @ViewById(R.id.tvTotalResult)
    TextView mTvTotalResult;

    @ViewById(R.id.imgShare)
    ImageView mImgShare;

    @AfterViews
    void afterView() {
        configRecycleView();
        mQuestionDataDatas = new ArrayList<QuestionData>();
        mListSelections = new ArrayList<>();
        ApplicationData.getSelectionTrue(mListSelections);
        if (getActivity() instanceof TestingActivity_) {
            mQuestionDataDatas = ((TestingActivity_) getActivity()).getMQuestionDataDatas();
        }
        mIsResultUser = SomeOtherFunction.resultUser(mQuestionDataDatas,mListSelections);
        SomeOtherFunction.changeTextViewToTwoColor(mTvTotalResult,"Total: ",totalSelectionTrueUser() + "/" + mIsResultUser.size(), Color.RED,Color.BLACK);
        mRecycleViewResult.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleViewResult.setLayoutManager(mLayoutManager);
        // specify an adapter (see also next example)
        mAdapter = new TestResultingAdapter(mQuestionDataDatas, mListSelections, mIsResultUser);
        mRecycleViewResult.setAdapter(mAdapter);
    }

    @Click(R.id.imgShare)
    void imgShareClicked() {

        if (getActivity() instanceof TestingActivity_) {
            ((TestingActivity_) getActivity()).shareFaceBook();
        }
    }

    /**
     * Config recyclerView
     */
    private void configRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleViewResult.setLayoutManager(linearLayoutManager);
    }

    /**
     * Count Number Selection True User
     *
     * @return
     */
    private int totalSelectionTrueUser() {
        int count = 0;
        for (int i = 0; i < mIsResultUser.size(); i++) {
            if (mIsResultUser.get(i)) {
                count++;
            }
        }
        return count;
    }
}

