package vn.asiantech.LearingEnglish.models;
import com.google.gson.annotations.Expose;

import lombok.Data;

@Data
public class Login {

    @Expose
    private Boolean error;
    @Expose
    private String name;
    @Expose
    private String email;
    @Expose
    private String createdAt;

}