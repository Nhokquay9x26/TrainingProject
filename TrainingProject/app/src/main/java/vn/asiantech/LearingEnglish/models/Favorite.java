package vn.asiantech.LearingEnglish.models;

import com.orm.SugarRecord;

import lombok.Data;

/**
 * @author DaoQuocViet
 *         Created by nhokquay9x26 on 10/09/2015.
 */
@Data
public class Favorite extends SugarRecord {
    String english;
    String phonetic;
    int sound;

    public Favorite(String english, String phonetic, int sound) {
        this.english = english;
        this.phonetic = phonetic;
        this.sound = sound;
    }
}
