package vn.asiantech.LearingEnglish.network;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.models.Model_Test;
import vn.asiantech.LearingEnglish.network.core.ApiClient;
import vn.asiantech.LearingEnglish.network.core.Callback;

/**
 * Created by xuanphu on 10/09/2015.
 */
public class AuthApi {

    public static void getUserInfo(final Callback<Model_Test> callback){

        ApiClient.call().getInfo(new Callback<Model_Test>() {
            @Override
            public void success(Model_Test model_test) {
                callback.success(model_test);
            }

            @Override
            public void failure(RetrofitError error, Error myError) {
                callback.failure(error, myError);
            }
        });
    }
}
