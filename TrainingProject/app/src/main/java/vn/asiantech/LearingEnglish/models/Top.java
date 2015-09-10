package vn.asiantech.LearingEnglish.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by thehoa on 9/1/15.
 */
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Top {
    private int imgCategory;
    private String category;
}
