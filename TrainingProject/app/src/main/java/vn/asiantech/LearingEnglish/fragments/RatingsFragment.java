package vn.asiantech.LearingEnglish.fragments;



import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.RatingsAdapter;
import vn.asiantech.LearingEnglish.models.Ranking;

/**
 * Created by tantv on 03/09/2015.
 */

@EFragment(R.layout.fragment_ratings)
public class RatingsFragment extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Ranking> mRanking;
    private RecyclerView.LayoutManager mLayoutManager;

    @ViewById(R.id.recycleRatings)
    RecyclerView mRecylerViewRatings;

    @AfterViews
    void afterView() {
        configRecycleView();
        mRanking = new ArrayList<Ranking>();
        mRecylerViewRatings.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecylerViewRatings.setLayoutManager(mLayoutManager);
        mAdapter = new RatingsAdapter(mRanking);
        mRecylerViewRatings.setAdapter(mAdapter);
    }

    /**
     * Config recyclerView
     */
    private void configRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecylerViewRatings.setLayoutManager(linearLayoutManager);
    }

}