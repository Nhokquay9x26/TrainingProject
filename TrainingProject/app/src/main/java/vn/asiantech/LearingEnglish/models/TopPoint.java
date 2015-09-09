package vn.asiantech.LearingEnglish.models;

import lombok.Data;

/**
 * Created by sunday on 02/09/2015.
 */
@Data
public class TopPoint {
    private String mId;
    private String mName;
    private int mPoint;
    private int mPhotoId;

    public TopPoint(String mId, int mPhotoId, String mName, int mPoint) {
        this.mId = mId;
        this.mName = mName;
        this.mPoint = mPoint;
        this.mPhotoId = mPhotoId;
    }


}
