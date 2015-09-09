package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.WordFavoriteAdapter;
import vn.asiantech.LearingEnglish.widget.DividerItemDecoration;

@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends BaseFragment {
    @ViewById(R.id.recyclerViewFavorite)
    RecyclerView mRecyclerViewFavorite;
    private List<vn.asiantech.LearingEnglish.models.WordFavorite> mDatas = new ArrayList<>();
    String mFavorites[] = {"Home", "Welcome", "Tutorial", "Android", "Speed", "School", "Favorite", "Class", "Program", "Test"};
    String mSpells[] = {"/hoʊm/", "/'welk m/", "/tju´tɔ:riəl/", "/´ændrɔid/", "/spi:d/", "/sku:l/", "/ˈfeɪvərɪt , ˈfeɪvrɪt/",
            "/klɑ:s/", "/´prougræm/", "/test/"};

    @AfterViews
    void afterViews() {
        mRecyclerViewFavorite.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewFavorite.setLayoutManager(layoutManager);
        mRecyclerViewFavorite.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        addData();
        WordFavoriteAdapter wordFavoriteAdaper = new WordFavoriteAdapter(mDatas, getActivity());
        mRecyclerViewFavorite.setAdapter(wordFavoriteAdaper);

    }

    private void addData() {
        for (int i = 0; i < mFavorites.length; i++) {
            vn.asiantech.LearingEnglish.models.WordFavorite model = new vn.asiantech.LearingEnglish.models.WordFavorite();
            model.setMWord(mFavorites[i]);
            model.setMPronunciation(mSpells[i]);
            mDatas.add(model);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}