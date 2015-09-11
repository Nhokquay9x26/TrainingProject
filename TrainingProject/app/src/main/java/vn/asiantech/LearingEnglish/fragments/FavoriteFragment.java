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
import vn.asiantech.LearingEnglish.adapter.FavoriteAdapter;
import vn.asiantech.LearingEnglish.models.Favorite;

/**
 * Copyright © 2015 AsianTech inc.
 * Created by PhuQuy on 8/27/15.
 */
@EFragment(R.layout.fragment_note)
public class FavoriteFragment extends BaseFragment {
    @ViewById(R.id.recycleViewFavorite)
    RecyclerView mRecycleFavorite;

    @FragmentByTag("DetailFragment")
    protected DetailFragment mDetailFragment;

    private ArrayList<Favorite> mArrFavorite = new ArrayList<Favorite>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @AfterViews
    void afterviews() {
        creatData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        FavoriteAdapter mAdapter = new FavoriteAdapter(mArrFavorite, getActivity());
        mRecycleFavorite.setHasFixedSize(true);
        mRecycleFavorite.setLayoutManager(linearLayoutManager);
        mRecycleFavorite.setAdapter(mAdapter);

    }

    public void creatData() {
        mArrFavorite.add(new Favorite("About", "/əˈbaʊt/", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Across", "/əˈkrɒs/", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Active", "/ˈæk.tɪv/", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Bag", "/bæg/", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Father", "/ˈfɑː.ðər /", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Only", "/ˈəʊn.li/", R.drawable.ic_sound));
        mArrFavorite.add(new Favorite("Party", "/ˈpɑː.ti/", R.drawable.ic_sound));
    }
}

