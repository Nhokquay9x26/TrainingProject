package vn.asiantech.LearingEnglish.adapter;

import android.content.Context;
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
 * Created by sunday on 02/09/2015.
 */
public class AdapterTopPoint extends RecyclerView.Adapter<AdapterTopPoint.TopPointViewHolder>{
            private List<TopPoint> mTopPoints;
            private Context mContext;

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

    public AdapterTopPoint(List<TopPoint> mTopPoints, Context mContext) {
        this.mTopPoints = mTopPoints;
        this.mContext = mContext;
    }

    @Override
    public TopPointViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_top_point,viewGroup,false);
        TopPointViewHolder  tPv = new TopPointViewHolder(view);
        return tPv;
    }

    @Override
    public void onBindViewHolder(TopPointViewHolder holder, int position) {
        holder.mTvId.setText(mTopPoints.get(position).getmId());
        holder.mImgAvatar.setImageResource(mTopPoints.get(position).getmPhotoId());
        holder.mTvName.setText(mTopPoints.get(position).getmName());
        holder.mTvTopPoint.setText((mTopPoints.get(position).getmPoint())+"");
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