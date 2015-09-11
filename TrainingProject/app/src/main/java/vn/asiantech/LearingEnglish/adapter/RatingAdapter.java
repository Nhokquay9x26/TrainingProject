package vn.asiantech.LearingEnglish.adapter;


import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.RatingUser;

/**
 * @author mrson
 *         Created by mrson on 31/08/2015.
 */
@SuppressWarnings("deprecation")
public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {
    Context mcontext;
    public List<RatingUser> mListRating = new ArrayList<>();

    public RatingAdapter(FragmentActivity mcontext, List<RatingUser> mlist) {
        this.mcontext = mcontext;
        this.mListRating = mlist;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycleview_rating, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RatingUser ratingUser = mListRating.get(position);
        holder.tvRank.setText(ratingUser.getRank());
        holder.tvUsername.setText(ratingUser.getUserName());
        holder.tvPoint.setText(ratingUser.getPoint());
        holder.imgProfile.setImageResource(ratingUser.getProfileRating());
    }

    @Override
    public int getItemCount() {
        return mListRating.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tvRank;
        public TextView tvUsername;
        public ImageView imgProfile;
        public TextView tvPoint;

        public ViewHolder(View itemView) {
            super(itemView);
            tvRank = (TextView) itemView.findViewById(R.id.tvRankId);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvPoint = (TextView) itemView.findViewById(R.id.tvPointRating);
            imgProfile = (ImageView) itemView.findViewById(R.id.imgUserRank);

        }
    }

}
