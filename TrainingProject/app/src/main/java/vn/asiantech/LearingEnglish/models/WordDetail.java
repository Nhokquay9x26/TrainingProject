package vn.asiantech.LearingEnglish.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WordDetail implements Serializable {
    private int image;
    private String word;
    private String mean;
    private String example;
}
