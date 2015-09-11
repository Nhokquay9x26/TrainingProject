package vn.asiantech.LearingEnglish.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.FragmentByTag;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.activities.InitializeActivity_;
import vn.asiantech.LearingEnglish.fragments.DetailFragment;
import vn.asiantech.LearingEnglish.fragments.DetailFragment_;
import vn.asiantech.LearingEnglish.fragments.SignUpFragment;
import vn.asiantech.LearingEnglish.fragments.SignUpFragment_;
import vn.asiantech.LearingEnglish.models.Top;

/**
 * @author DaoQuocViet
 *         Created by nhokquay9x26 on 10/09/2015.
 */
public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {
    private ArrayList<Top> mArrTops;
    private onClickItemInterface mListener;

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImgImage;
        private TextView mTvCategory;
        private ImageView mTopNext;

        public TopViewHolder(View itemView) {
            super(itemView);
            this.mImgImage = (ImageView) itemView.findViewById(R.id.imgImage);
            this.mTvCategory = (TextView) itemView.findViewById(R.id.tvCategory);
            this.mTopNext = (ImageView) itemView.findViewById(R.id.imgTopNext);
        }
    }

    public TopAdapter(ArrayList<Top> mArrTops, onClickItemInterface listener) {
        this.mArrTops = mArrTops;
        mListener = listener;
    }

    @Override
    public TopViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_top,
                viewGroup, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopViewHolder holder, int position) {
        holder.mImgImage.setImageResource(mArrTops.get(position).getImage());
        holder.mTvCategory.setText(mArrTops.get(position).getCategory());
        holder.mTopNext.setImageResource(mArrTops.get(position).getNext());
        holder.mTopNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.clickTopNext();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mArrTops.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface onClickItemInterface {
        void clickTopNext();
    }
}
