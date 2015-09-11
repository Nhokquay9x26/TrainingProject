package vn.asiantech.LearingEnglish.network;

import retrofit.RetrofitError;
import vn.asiantech.LearingEnglish.models.InfoRegister;
import vn.asiantech.LearingEnglish.network.core.ApiClient;
import vn.asiantech.LearingEnglish.network.core.Callback;

/**
 * Created by mrson on 10/09/2015.
 */
public class AuthorApi {
    public AuthorApi() {
    }

    public static void registerAccount(String email, String password, String name, final Callback<InfoRegister> callback) {
        ApiClient.call().register(email, password, name, new Callback<InfoRegister>() {
            @Override
            public void success(InfoRegister infoRegister) {
                callback.success(infoRegister);
            }

            @Override
            public void failure(RetrofitError error, Error myError) {
                callback.failure(error, myError);
            }
        });
    }
}