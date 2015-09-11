package vn.asiantech.LearingEnglish.network;

import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import retrofit.http.Query;
import vn.asiantech.LearingEnglish.models.BaseModel;
import vn.asiantech.LearingEnglish.models.Login;
import vn.asiantech.LearingEnglish.network.core.Callback;

public interface Api {

    @FormUrlEncoded
    @POST("/v1/device")
    void postToken(@Field(Parameter.EMAIL) String deviceToken,
                   Callback<BaseModel> callback);

    @DELETE("/v1/device")
    void deleteToken(@Query(Parameter.EMAIL) String deviceToken,
                     Callback<BaseModel> callback);


    /* ------------------------------------
     * login
     * -----------------------------------*/

    @FormUrlEncoded
    @POST("/login")
    void login(@Field("email") String email,
               @Field("password") String password,
               Callback<Login> callback);
}
