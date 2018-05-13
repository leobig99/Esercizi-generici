package com.example.andrea.mongo;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by andrea on 30/04/2018.
 */

public class InterazioneServer {

    private RetrofitService mService;

    public InterazioneServer() {
        Log.d("MainActivity", "create Retrofit Service");
        mService = RetrofitClient.getClient().create(RetrofitService.class);
    }

    public void addVideoGames(VideoGames videoGames) {
        mService.addVideoGames(videoGames).enqueue(new Callback<VideoGames>() {
            @Override
            public void onResponse(Call<VideoGames> call, Response<VideoGames> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "RESPONSE: " + response.body().toString());
                } else {
                    Log.i(TAG, "ERROR: " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<VideoGames> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.getMessage());
            }
        });
    }

    public void modificaVideoGames(String id, VideoGames videoGames) {
        mService.updateVideoGames(id, videoGames).enqueue(new Callback<VideoGames>() {
            @Override
            public void onResponse(Call<VideoGames> call, Response<VideoGames> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "RESPONSE: " + response.body().toString());
                } else {
                    Log.i(TAG, "ERROR: " + response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<VideoGames> call, Throwable t) {
                Log.e(TAG, "ERROR: " + t.getMessage());
            }
        });
    }

    public void deleteVideoGames(String id) {
        mService.deleteVideoGames(id).enqueue(new Callback<VideoGames>() {
            @Override
            public void onResponse(Call<VideoGames> call, Response<VideoGames> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "result code is: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<VideoGames> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

}
