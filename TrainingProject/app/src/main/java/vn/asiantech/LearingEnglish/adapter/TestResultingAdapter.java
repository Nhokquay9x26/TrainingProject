package vn.asiantech.LearingEnglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.Question;

/**
 * Created by tantv on 02/09/2015.
 */
@SuppressWarnings({"ALL", "DefaultFileTemplate"})
public class TestResultingAdapter extends RecyclerView.Adapter<TestResultingAdapter.ViewHolder> {

    private ArrayList<Question> mQuestions;
    private ArrayList<String> mListSelections;
    private ArrayList<Boolean> mIsResultUser;

    public TestResultingAdapter(ArrayList<Question> questions,ArrayList<String> listSelections,ArrayList<Boolean> isResultUser){
        this.mQuestions = questions;
        this.mListSelections = listSelections;
        this.mIsResultUser = isResultUser;
    }

    @Override
    public TestResultingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_results, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(TestResultingAdapter.ViewHolder holder, int position) {
        holder.tvQuestion.setText("Question " + (position + 1) + ": ");
        holder.tvSelection.setText(mListSelections.get(position));
        if (mIsResultUser.get(position)){
            holder.imgCheckFalse.setVisibility(View.INVISIBLE);
            holder.imgCheckTrue.setVisibility(View.VISIBLE);
        } else {
            holder.imgCheckFalse.setVisibility(View.VISIBLE);
            holder.imgCheckTrue.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mQuestions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvQuestion;
        public TextView tvSelection;
        public ImageView imgCheckTrue;
        public ImageView imgCheckFalse;
        public ViewHolder(View v) {
            super(v);
            tvQuestion = (TextView) v.findViewById(R.id.tvListQuestion);
            tvSelection = (TextView) v.findViewById(R.id.tvSelection);
            imgCheckTrue = (ImageView)v.findViewById(R.id.imgCheckTrue);
            imgCheckFalse = (ImageView)v.findViewById(R.id.imgCheckFalse);

        }

    }

}

