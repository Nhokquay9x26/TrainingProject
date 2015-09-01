package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.WordFavorite;


@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends BaseFragment {
    @ViewById(R.id.recyclerViewFavorite)
    RecyclerView mRecyclerViewFavorite;
    private WordFavorite mWordFavoriteAdaper;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<vn.asiantech.LearingEnglish.models.WordFavorite> mDatas = new ArrayList<>();
    String mFavorites[] = {"Home", "Welcome", "Tutorial", "Android", "Speed", "School", "Favorite"};
    @AfterViews
    void afterViews(){
        mRecyclerViewFavorite.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        mRecyclerViewFavorite.setLayoutManager(mLayoutManager);
        addData();
        mWordFavoriteAdaper = new WordFavorite(mDatas,getActivity());
        mRecyclerViewFavorite.setAdapter(mWordFavoriteAdaper);
        Log.e("aaaaaaaaaaa", ""+ mDatas.size());

    }
    private void addData(){
        for (int i = 0 ; i < mFavorites.length; i++){
            vn.asiantech.LearingEnglish.models.WordFavorite model = new vn.asiantech.LearingEnglish.models.WordFavorite();
            model.setWord(mFavorites[i]);
            mDatas.add(model);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}