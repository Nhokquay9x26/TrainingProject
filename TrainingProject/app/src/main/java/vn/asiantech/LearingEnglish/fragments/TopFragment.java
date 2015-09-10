package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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
public class TopFragment extends BaseFragment {
    @ViewById(R.id.recycleViewTop)
    RecyclerView mRecycleTop;

    @FragmentByTag("LoginFragment")
    protected DetailFragment mDetailFragment;

    private ArrayList<Top> mArrTop = new ArrayList<Top>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @AfterViews
    void afterviews() {
        creatData2();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        TopAdapter mAdapter = new TopAdapter(mArrTop);
        mRecycleTop.setHasFixedSize(true);
        mRecycleTop.setLayoutManager(linearLayoutManager);
        mRecycleTop.setAdapter(mAdapter);

    }

    public void creatData2() {
        mArrTop.add(new Top(R.drawable.ic_avatar, "hello", R.drawable.ic_next_top));
        mArrTop.add(new Top(R.drawable.ic_avatar, "hello", R.drawable.ic_next_top));
        mArrTop.add(new Top(R.drawable.ic_avatar, "hello", R.drawable.ic_next_top));
        mArrTop.add(new Top(R.drawable.ic_avatar, "hello", R.drawable.ic_next_top));
        mArrTop.add(new Top(R.drawable.ic_avatar, "hello", R.drawable.ic_next_top));
    }
}
