package vn.asiantech.LearingEnglish.models;

import com.orm.SugarRecord;

import lombok.Data;

/**
 *
 * Created by mrson on 01/09/2015.
 */
public class WordsEnglish extends SugarRecord {
    private String mNewWord;
    private String mSpellingWord;
    private String mMeans;
    private String mListenWord;

    public WordsEnglish(String mNewWord, String mSpellingWord, String mMeans) {
        this.mNewWord = mNewWord;
        this.mSpellingWord = mSpellingWord;
        this.mMeans = mMeans;
    }

    public WordsEnglish() {
    }

    public String getmNewWord() {
        return mNewWord;
    }

    public void setmNewWord(String mNewWord) {
        this.mNewWord = mNewWord;
    }

    public String getmSpellingWord() {
        return mSpellingWord;
    }

    public void setmSpellingWord(String mSpellingWord) {
        this.mSpellingWord = mSpellingWord;
    }

    public String getmMeans() {
        return mMeans;
    }

    public void setmMeans(String mMeans) {
        this.mMeans = mMeans;
    }

    public String getmListenWord() {
        return mListenWord;
    }

    public void setmListenWord(String mListenWord) {
        this.mListenWord = mListenWord;
    }
}
