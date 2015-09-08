package vn.asiantech.LearingEnglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.core.fragments.Fragment;
import vn.asiantech.LearingEnglish.models.Top;
import vn.asiantech.LearingEnglish.views.CircleImageView;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {

    private Fragment mFragment;
    private ArrayList<Top> mListTop;
    private OnTopListenner mOnTopListenner;

    public TopAdapter(Fragment mFragment, ArrayList<Top> mListTop, OnTopListenner listener) {
        this.mFragment = mFragment;
        this.mListTop = mListTop;
        mOnTopListenner = listener;
    }

    @Override
    public TopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mFragment.getActivity()).inflate(R.layout.item_list_top, parent, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopViewHolder holder, final int position) {
        holder.mImgCategory.setImageResource(mListTop.get(position).getImgCategory());
        holder.mTxtCategory.setText(mListTop.get(position).getCategory());
        holder.mImgDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnTopListenner != null){
                    mOnTopListenner.OnClickItem(position);
                }
            }
        });
    }

    @Override

    public int getItemCount() {
        return mListTop.size();
    }

    public class TopViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView mImgCategory;
        private TextView mTxtCategory;
        private ImageView mImgDetail;

        public TopViewHolder(View itemView) {
            super(itemView);
            mImgCategory = (CircleImageView) itemView.findViewById(R.id.imgCategory);
            mTxtCategory = (TextView) itemView.findViewById(R.id.txtCategory);
            mImgDetail = (ImageView) itemView.findViewById(R.id.imgDetail);

        }
    }
    public interface OnTopListenner{
        void OnClickItem(int position);
    }
}
