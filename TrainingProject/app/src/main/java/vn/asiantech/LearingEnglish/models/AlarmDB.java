package vn.asiantech.LearingEnglish.models;

import com.orm.SugarRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by tantv on 13/09/2015.
 */
@Data
@AllArgsConstructor
public class AlarmDB extends SugarRecord {
    public AlarmDB() {
    }
    int hour;
    int minute;
    String message;
    int status;
    int pos;
}
