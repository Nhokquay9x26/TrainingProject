package vn.asiantech.LearingEnglish.models;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by chanh on 11/09/2015.
 */

@Data
@AllArgsConstructor(suppressConstructorProperties = true)
public class Question {

    private String question;
    private String selectionA;
    private String selectionB;
    private String selectionC;
    private String selectionD;
    private int selectionUser = 0;
}
