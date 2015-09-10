package vn.asiantech.LearingEnglish.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import vn.asiantech.LearingEnglish.R;
import vn.asiantech.LearingEnglish.models.Favorite;

/**
 * @author DaoQuocViet
 *         Created by nhokquay9x26 on 10/09/2015.
 */
public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private ArrayList<Favorite> mArrFavorites;

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvEnglish;
        private TextView mTvPhonetic;
        private ImageView mImgSound;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            this.mTvEnglish = (TextView) itemView.findViewById(R.id.tvEnglish);
            this.mTvPhonetic = (TextView) itemView.findViewById(R.id.tvPhonetic);
            this.mImgSound = (ImageView) itemView.findViewById(R.id.imgSound);
        }
    }

    public FavoriteAdapter(ArrayList<Favorite> mArrFavorites) {
        this.mArrFavorites = mArrFavorites;
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_favorite,
                viewGroup, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        holder.mTvEnglish.setText(mArrFavorites.get(position).getEnglish());
        holder.mTvPhonetic.setText(mArrFavorites.get(position).getPhonetic());
        holder.mImgSound.setImageResource(mArrFavorites.get(position).getSound());

    }

    @Override
    public int getItemCount() {
        return mArrFavorites.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
