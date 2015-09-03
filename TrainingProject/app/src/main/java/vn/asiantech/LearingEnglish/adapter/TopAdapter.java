package vn.asiantech.LearingEnglish.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.Top;
import vn.asiantech.LearingEnglish.views.CircleImageView;

/**
 * Created by thehoa on 9/1/15.
 */
public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {
    private Activity mActivity;
    private ArrayList<Top> mListTop;

    public TopAdapter(Activity mActivity, ArrayList<Top> mListTop) {
        this.mActivity = mActivity;
        this.mListTop = mListTop;
    }

    @Override
    public TopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.item_list_top, parent, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopViewHolder holder, int position) {
        holder.mImgCategory.setImageResource(mListTop.get(position).getImgCategory());
        holder.mTxtCategory.setText(mListTop.get(position).getCategory());
    }

    @Override

    public int getItemCount() {
        return mListTop.size();
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImgCategory;
        private TextView mTxtCategory;

        public TopViewHolder(View itemView) {
            super(itemView);
            mImgCategory = (CircleImageView) itemView.findViewById(R.id.imgCategory);
            mTxtCategory = (TextView) itemView.findViewById(R.id.txtCategory);
        }
    }
}
