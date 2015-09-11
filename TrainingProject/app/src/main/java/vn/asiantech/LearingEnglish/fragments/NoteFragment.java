package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentByTag;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.MainActivity_;
import vn.asiantech.LearingEnglish.adapter.FavoriteAdapter;
import vn.asiantech.LearingEnglish.adapter.TopAdapter;
import vn.asiantech.LearingEnglish.models.Favorite;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
@EFragment(R.layout.fragment_note)
public class NoteFragment extends BaseFragment implements FavoriteAdapter.onClickItemInterface{
    @ViewById(R.id.recycleViewFavorite)
    RecyclerView mRecycleFavorite;

    @FragmentByTag("DetailFragment")
    protected DetailFragment mDetailFragment;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Favorite> mArrFavorite = new ArrayList<Favorite>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @AfterViews
    void afterviews(){
        createData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        FavoriteAdapter mAdapter = new FavoriteAdapter(mArrFavorite, this);
        mRecycleFavorite.setHasFixedSize(true);
        mRecycleFavorite.setLayoutManager(linearLayoutManager);
        mRecycleFavorite.setAdapter(mAdapter);

    }

    public void createData(){
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
    }

    @Override
    public void clickTopNext() {
        if(mDetailFragment == null){
            mDetailFragment = DetailFragment_.builder().build();
        }
        ((MainActivity_) getActivity()).addFragmentMain(mDetailFragment);
    }
}

