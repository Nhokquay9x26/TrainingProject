package vn.asiantech.LearingEnglish.network;

import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import vn.asiantech.LearingEnglish.models.BaseModel;
import vn.asiantech.LearingEnglish.models.Login;
import vn.asiantech.LearingEnglish.models.Model_Test;
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
    @POST("/v1/login")
    void login(@Field(Parameter.EMAIL) String studentId,
               @Field(Parameter.PASSWORD) String password,
               Callback<Login> callback);

    @GET("/victorydst3")
    void getInfo(Callback<Model_Test> callback);
}
