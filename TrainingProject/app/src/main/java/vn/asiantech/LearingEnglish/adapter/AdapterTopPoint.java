package vn.asiantech.LearingEnglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.TopPoint;

/**
 *  Created by sunday on 02/09/2015.
 */
public class AdapterTopPoint extends RecyclerView.Adapter<AdapterTopPoint.TopPointViewHolder>{
            private List<TopPoint> mTopPoints;

        public static class TopPointViewHolder extends RecyclerView.ViewHolder {
            private TextView mTvId;
            private TextView mTvName;
            private TextView mTvTopPoint;
            private ImageView mImgAvatar;


        public TopPointViewHolder(View itemView) {
            super(itemView);
            this.mTvId = (TextView) itemView.findViewById(R.id.tvId);
            this.mTvName = (TextView) itemView.findViewById(R.id.tvNameTopPoint);
            this.mTvTopPoint = (TextView) itemView.findViewById(R.id.tvTopPoint);
            this.mImgAvatar = (ImageView) itemView.findViewById(R.id.imgAvatarTopPoint);
        }
    }

    public AdapterTopPoint(List<TopPoint> mTopPoints) {
        this.mTopPoints = mTopPoints;
    }

    @Override
    public TopPointViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_top_point,viewGroup,false);
        return new TopPointViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopPointViewHolder holder, int position) {
        holder.mTvId.setText(mTopPoints.get(position).getMId());
        holder.mImgAvatar.setImageResource(mTopPoints.get(position).getMPhotoId());
        holder.mTvName.setText(mTopPoints.get(position).getMName());
        holder.mTvTopPoint.setText((mTopPoints.get(position).getMPoint())+"");
    }

    @Override
    public int getItemCount() {
        return mTopPoints.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}