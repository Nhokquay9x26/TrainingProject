package vn.asiantech.LearingEnglish.fragments;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.FavoriteAdapter;
import vn.asiantech.LearingEnglish.models.Favorite;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
@EFragment(R.layout.fragment_note)
public class NoteFragment extends BaseFragment {
    @ViewById(R.id.recycleViewFavorite)
    RecyclerView mRecycleFavorite;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Favorite> mArrFavorite = new ArrayList<Favorite>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @AfterViews
    void afterviews(){
        creatData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        FavoriteAdapter mAdapter = new FavoriteAdapter(mArrFavorite);
        mRecycleFavorite.setHasFixedSize(true);
        mRecycleFavorite.setLayoutManager(linearLayoutManager);
        mRecycleFavorite.setAdapter(mAdapter);

    }

    public void creatData(){
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Hello", "hello", R.drawable.ic_sound));
    }

}

