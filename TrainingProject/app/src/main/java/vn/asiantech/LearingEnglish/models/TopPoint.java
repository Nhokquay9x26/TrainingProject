package vn.asiantech.LearingEnglish.models;

/**
 * Created by sunday on 02/09/2015.
 */
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

    public String getmId() {
        return mId;
    }

    public String getmName() {
        return mName;
    }

    public int getmPoint() {
        return mPoint;
    }

    public int getmPhotoId() {
        return mPhotoId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmPhotoId(int mPhotoId) {
        this.mPhotoId = mPhotoId;
    }

    public void setmPoint(int mPoint) {
        this.mPoint = mPoint;
    }





}
