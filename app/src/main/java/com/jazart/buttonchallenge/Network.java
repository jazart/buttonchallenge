package com.jazart.buttonchallenge;

import android.provider.ContactsContract;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jazart on 3/22/2018.
 */

public class Network {
    private Retrofit mRetrofit;
    private Cache mCache;
    private OkHttpClient mClient;
    private int size;
    final String BASE_URL = "http://fake-button.herokuapp.com/";


    public FakeButtonService getButtonService() {
        return mButtonService;
    }

    private FakeButtonService mButtonService;

    public Network(File dir) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(dir , cacheSize);

        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mButtonService = retrofit.create(FakeButtonService.class);
    }
}
