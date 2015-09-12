package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.QuestionAdapter;
import vn.asiantech.LearingEnglish.models.ApplicationData;
import vn.asiantech.LearingEnglish.models.NumberTesting;

/**
 * Created by tantv on 31/08/2015.
 */

@EFragment(R.layout.fragment_question)
public class QuestionFragment extends Fragment implements View.OnClickListener {
    @ViewById(R.id.recycleQuestion)
    RecyclerView mRecycleQuestion;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<NumberTesting> mNumberTestings;

    @AfterViews
    void afterView() {
        configRecycleView();
        mNumberTestings = new ArrayList<>();
        mNumberTestings = ApplicationData.getNumberTestings();
        mRecycleQuestion.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleQuestion.setLayoutManager(mLayoutManager);
        mAdapter = new QuestionAdapter(mNumberTestings, getActivity().getApplicationContext(), getActivity());
        mRecycleQuestion.setAdapter(mAdapter);

    }

    /**
     * Config recyclerView
     */
    private void configRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleQuestion.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View v) {

    }
}