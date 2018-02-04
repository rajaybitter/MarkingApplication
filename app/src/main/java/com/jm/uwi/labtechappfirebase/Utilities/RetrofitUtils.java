package com.jm.uwi.labtechappfirebase.Utilities;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by rajay on 2/8/17.
 */

public interface RetrofitUtils {

    @FormUrlEncoded
    @POST("/register")
    Call<ResponseBody> sendConfirmation(@Field("firstName") String fname, @Field("lastName") String lname);
}

