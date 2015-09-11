package vn.asiantech.LearingEnglish.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.TestingActivity_;
import vn.asiantech.LearingEnglish.models.NumberTesting;
import vn.asiantech.LearingEnglish.models.SomeOtherFunction;

/**
 * Created by tantv on 10/09/2015.
 */
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private ArrayList<NumberTesting> mNumberTestings;
    private Context mContext;
    private Activity mActivity;

    public QuestionAdapter(ArrayList<NumberTesting> mNumberTestings, Context mContext, Activity mActivity) {
        this.mNumberTestings = mNumberTestings;
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_question, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, int position) {
        String pos = "";
        if (position < 9) {
            pos = (position + 1) + " ";
        } else {
            pos = (position + 1) + "";
        }
        SomeOtherFunction.changeTextViewToTwoColor(holder.tvQuestionNumber, "Test " + pos + ":  ", mNumberTestings.get(position).getLevel() + " - " + mNumberTestings.get(position).getTime(), Color.BLUE, Color.BLACK);
    }


    @Override
    public int getItemCount() {
        return mNumberTestings.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvQuestionNumber;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            tvQuestionNumber = (TextView) v.findViewById(R.id.tvQuestionNumber);
        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "onClick " + getPosition());
            final Intent mIntent = new Intent(mActivity, TestingActivity_.class);
            new AlertDialog.Builder(mActivity)
                    .setTitle(" ")
                    .setMessage("Are you ready?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    mActivity.startActivity(mIntent);
                                }
                            }
                    )
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // do nothing
                                }
                            }

                    )
                    .setIcon(R.drawable.ico_start)
                    .show();
        }
    }
}
