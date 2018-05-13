package com.example.andrea.mongo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andrea on 24/04/2018.
 */

public class RetrofitClient {

    private static String BASE_URL = "http://casa.tramontini.it:99/";
    private static String AUTH = "Basic YTph";
    private static Retrofit retrofit;
    private static OkHttpClient client;

    static {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request request = chain.request().newBuilder().addHeader("Authorization", AUTH).build();
            return chain.proceed(request);
        });

        client = httpClient.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public static Retrofit getClient() {
        return retrofit;
    }
}
