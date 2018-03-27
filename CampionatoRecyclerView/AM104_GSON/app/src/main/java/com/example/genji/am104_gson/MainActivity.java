package com.example.genji.am104_gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class MainActivity extends AppCompatActivity {

    private RFService mService;
    private RecyclerView recyclerView;
    private RoundAdapter adapter;
    private List<Round> rounds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new RoundAdapter(this,rounds);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        Log.d("MainActivity", "call RFInterface.getEmbeddedService()");
        mService = RFService.retrofit.create(RFService.class);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener((View view) -> {
            Log.d("MainActivity", "getRFResponse()");
            getRFResponse();
        });
    }

    public void getRFResponse() {
        mService.getPojo().enqueue(new Callback<Pojo>() {

            private List<Round> rounds = new ArrayList<>();

            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {

                Log.d("MainActivity", "onresponse()");

                if (response.isSuccessful()) {
                    Log.d("MainActivity", "onresponse.isSuccessful()");
                    Toast.makeText(MainActivity.this, "get response", Toast.LENGTH_SHORT);
                    rounds = response.body().getRounds();
                    showRounds(rounds);

                } else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "handle request errors");
                }
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    void showRounds(List<Round> rounds) {
        this.rounds.clear();
        this.rounds.addAll(rounds);
        adapter.notifyDataSetChanged();
    }
}
