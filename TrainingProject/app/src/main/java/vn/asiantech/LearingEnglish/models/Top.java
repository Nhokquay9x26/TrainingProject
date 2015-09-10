package vn.asiantech.LearingEnglish.models;

import com.orm.SugarRecord;

import lombok.Data;

/**
 * @author DaoQuocViet
 *         Created by nhokquay9x26 on 10/09/2015.
 */
@Data
public class Top extends SugarRecord{
    int image;
    String category;
    int next;

    public Top(int image, String catogery, int next) {
        this.image = image;
        this.category = catogery;
        this.next = next;
    }
}
