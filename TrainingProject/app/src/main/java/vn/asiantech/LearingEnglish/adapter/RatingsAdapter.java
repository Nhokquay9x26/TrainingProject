package vn.asiantech.LearingEnglish.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.Ranking;

/**
 * Created by tantv on 04/09/2015.
 */

public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.ViewHolder> {
    private ArrayList<Ranking> mRanking;

    public RatingsAdapter(ArrayList<Ranking> mRanking) {
        this.mRanking = mRanking;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_ratings, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (position){
            case 0:{
                holder.mTvIdRank.setText((position+1)+"st");
                holder.mTvUserName.setTextColor(Color.RED);
            }
            case 1:{
                holder.mTvIdRank.setText((position+1)+"nd");
                holder.mTvUserName.setTextColor(Color.BLUE);
            }
            case 2:{
                holder.mTvIdRank.setText((position+1)+"rd");
                holder.mTvUserName.setTextColor(Color.BLUE);
            }
            default:{
                holder.mTvIdRank.setText((position+1)+"th");
            }
        }
        holder.mTvUserName.setText(mRanking.get(position).getMUserName());
        holder.mTvPoint.setText(mRanking.get(position).getMPoint());

    }

    @Override
    public int getItemCount() {
        return mRanking.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTvIdRank;
        private ImageView mImgAvatar;
        private TextView mTvUserName;
        private TextView mTvPoint;
        public ViewHolder(View v) {
            super(v);
            mTvIdRank = (TextView)v.findViewById(R.id.tvRank);
            mImgAvatar = (ImageView)v.findViewById(R.id.imgAvatar);
            mTvUserName = (TextView)v.findViewById(R.id.tvNameUser);
            mTvPoint = (TextView)v.findViewById(R.id.tvPoint);

        }
    }
}
