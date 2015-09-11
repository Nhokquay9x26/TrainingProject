package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity;
import vn.asiantech.LearingEnglish.adapter.TopAdapter;
import vn.asiantech.LearingEnglish.models.Top;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
@EFragment(R.layout.fragment_top)
public class TopFragment extends BaseFragment implements TopAdapter.onClickItemInterface{
    @ViewById(R.id.recycleViewTop)
    RecyclerView mRecycleTop;

    @FragmentByTag("mDetailFragment")
    protected DetailFragment mDetailFragment;

    private ArrayList<Top> mArrTop = new ArrayList<Top>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @AfterViews
    void afterviews() {
        creatData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        TopAdapter mAdapter = new TopAdapter(mArrTop, this);
        mRecycleTop.setHasFixedSize(true);
        mRecycleTop.setLayoutManager(linearLayoutManager);
        mRecycleTop.setAdapter(mAdapter);

    }

    public void creatData() {
        mArrTop.add(new Top(R.drawable.ic_avatar, "About", R.drawable.ic_next_top));
        mArrTop.add(new Top(R.drawable.ic_avatar, "Active", R.drawable.ic_next_top));
        mArrTop.add(new Top(R.drawable.ic_avatar, "Only", R.drawable.ic_next_top));
        mArrTop.add(new Top(R.drawable.ic_avatar, "Father", R.drawable.ic_next_top));
        mArrTop.add(new Top(R.drawable.ic_avatar, "Party", R.drawable.ic_next_top));
    }

    @Override
    public void clickTopNext() {
        if(mDetailFragment == null){
            mDetailFragment = DetailFragment_.builder().build();
        }
        addChildFragment(mDetailFragment);
    }
}
