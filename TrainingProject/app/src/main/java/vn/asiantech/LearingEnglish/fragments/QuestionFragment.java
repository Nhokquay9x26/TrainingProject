package vn.asiantech.LearingEnglish.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by xuanphu on 31/08/2015.
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
/*
        final Intent mIntent = new Intent(getActivity(), TestingActivity_.class);
        new AlertDialog.Builder(getActivity())
                .setTitle("NOTIFICATION")
                .setMessage("Are you sure you want to continue test?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(mIntent);

                            }
                        }
                )
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        }

                )
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();*/
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
