package vn.asiantech.LearingEnglish.models;

import lombok.Data;

/**
 * Created by tantv on 04/09/2015.
 */

@Data
public class Ranking {
    private int mIdRank;
    private String mAvatar;
    private String mUserName;
    private int mPoint;
    public Ranking(int mIdRank,String mAvatar,String mUserName,int mPoint){
        this.mIdRank = mIdRank;
        this.mAvatar = mAvatar;
        this.mUserName = mUserName;
        this.mPoint = mPoint;
    }

}
