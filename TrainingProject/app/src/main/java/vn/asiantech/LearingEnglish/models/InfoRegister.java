package vn.asiantech.LearingEnglish.models;

import com.google.gson.annotations.Expose;

import lombok.Data;

/**
 * Created by mrson on 10/09/2015.
 */
@Data
public class InfoRegister {
    @Expose
    private Boolean error;
    @Expose
    private String message;

}
