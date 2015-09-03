package vn.asiantech.LearingEnglish.models;

/**
 * Created by thehoa on 9/1/15.
 */
public class Top {
    private int mImgCategory;
    private String mCategory;

    public Top(int mImgCategory, String mCategory) {
        this.mImgCategory = mImgCategory;
        this.mCategory = mCategory;
    }

    public int getImgCategory() {
        return mImgCategory;
    }

    public void setImgCategory(int mImgCategory) {
        this.mImgCategory = mImgCategory;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }
}
