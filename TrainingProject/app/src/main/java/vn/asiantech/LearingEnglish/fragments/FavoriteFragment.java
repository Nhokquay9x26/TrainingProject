package vn.asiantech.LearingEnglish.fragments;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.Select;
import com.orm.query.Condition;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.adapter.FavoriteAdapter;
import vn.asiantech.LearingEnglish.models.WordsEnglish;
import vn.asiantech.LearingEnglish.utils.NotifyDataSetChanged;
import vn.asiantech.LearingEnglish.utils.TabBar;

/**
 * @author mrs
 *         Created by mrson on 31/08/2015.
 */
@EFragment(R.layout.fragment_favorite)
public class FavoriteFragment extends BaseFragment implements NotifyDataSetChanged {
    @ViewById(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<WordsEnglish> wordEnglishs;
    FavoriteAdapter mAdapter;
    BroadcastReceiver updateReceiver;
    private int mPosition;
    public static final String ACTION = "add";

    @Override
    public void onResume() {
        super.onResume();
        updateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                afterView();
            }
        };
        getActivity().registerReceiver(updateReceiver, new IntentFilter(ACTION));
    }

    @AfterViews
    void afterView() {
//        wordEnglishs = WordsEnglish.listAll(WordsEnglish.class);
//        mAdapter = new FavoriteAdapter(getActivity(), wordEnglishs, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
//        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void NotifyDataSetChanged(int position) {
        mPosition = mPosition;
        new AlertDialog.Builder(getActivity())
                .setTitle(" ")
                .setMessage("Are you Remove Favorite?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                WordsEnglish.deleteAll(WordsEnglish.class);
                                wordEnglishs.remove(mPosition);
                                Toast.makeText(getActivity(), "Remove: succsess", Toast.LENGTH_SHORT).show();
                                for (int i = 0; i < wordEnglishs.size(); i++) {
                                    WordsEnglish.save(wordEnglishs.get(i));
                                }
                                afterView();
                            }
                        }
                )
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                )
                .setIcon(android.R.drawable.ic_delete)
                .show();
    }

}