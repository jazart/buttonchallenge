package com.jazart.buttonchallenge;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by jazart on 3/20/2018.
 */

public interface FakeButtonService {

    @GET("user")
    Call<List<User>> listUsers(@Query("candidate") String candidate);

    @POST("user")
    Call<User> insertUser(@Body User user);

    @POST("transfer")
    Call<Transfer> newTransfer(@Body Transfer transfer);

    @GET("transfer")
    Call<Transfer> getTransfer (
            @Query("candidate") String candidate,
            @Query("id") int id);

}
