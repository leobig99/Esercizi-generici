package com.example.andrea.mongo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by andrea on 24/04/2018.
 */

public interface RetrofitService {

    @GET("zoccarato/prova")
    Call<Embedded> getVideoGames();

    @GET("zoccarato/prova")
    Call<Embedded> searchVideoGames();

    @GET("zoccarato/prova")
    Call<Embedded> searchVideoGamesByName(@Query(value = "filter", encoded = true) String query);

    @GET("zoccarato/prova")
    Call<Embedded> searchVideoGamesByDevelopers(@Query(value = "filter", encoded = true) String query);

    @POST("zoccarato/prova")
    Call<VideoGames> addVideoGames(@Body VideoGames videoGames);

    @DELETE("zoccarato/prova/{id}")
    Call<VideoGames> deleteVideoGames(@Path("id") String id);

    @PUT("zoccarato/prova/{id}")
    Call<VideoGames> updateVideoGames(@Path("id") String id, @Body VideoGames videoGames);

}
