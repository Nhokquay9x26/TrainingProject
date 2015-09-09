package vn.asiantech.LearingEnglish.fragments;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import java.util.ArrayList;
import java.util.List;
import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.FavoriteAdapter;
import vn.asiantech.LearingEnglish.models.WordsEnglish;
import vn.asiantech.LearingEnglish.utils.TabBar;

/**
 * @author mrs
 * Created by mrson on 31/08/2015.
 */
@EFragment (R.layout.fragment_favorite)
public class FavoriteFragment extends BaseFragment {
    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    List<WordsEnglish> wordsEnglishs = new ArrayList<WordsEnglish>();

    @AfterViews
    void afterView(){
        fakeData();
        favoriteAdapter = new FavoriteAdapter(getActivity(),wordsEnglishs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setAdapter(favoriteAdapter);}

    public void fakeData(){
        WordsEnglish wordsEnglish = new WordsEnglish();
        wordsEnglish.setNewWord("Rice");
        wordsEnglish.setSpellingWord("/Rai/");
        wordsEnglishs.add(wordsEnglish);
    }
}