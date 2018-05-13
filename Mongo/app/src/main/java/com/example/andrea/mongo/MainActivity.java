package com.example.andrea.mongo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.andrea.mongo.InserisciDialogFragment.OnItemInserted;
import com.example.andrea.mongo.SearchDialogFragment.SearchDialogListener;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.andrea.mongo.ModificaDialogFragment.*;

public class MainActivity extends AppCompatActivity implements OnItemInserted, OnItemModified, SearchDialogListener {

    private ArrayList<VideoGames> videoGames = new ArrayList<>();
    private VideoGamesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new VideoGamesAdapter(this, videoGames);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        getVideoGames();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            FragmentManager manager = getSupportFragmentManager();
            InserisciDialogFragment dialogFragment = new InserisciDialogFragment();
            dialogFragment.show(manager, "Inserisci VideoGames");
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickAll(View view) {
        getVideoGames();
    }

    public void onClickSearch(View view) {
        SearchDialogFragment dialogFragment = new SearchDialogFragment();
        dialogFragment.show(getFragmentManager(), "Modifica VideoGmaes");
    }

    public void getVideoGames() {
        RetrofitService mService = RetrofitClient.getClient().create(RetrofitService.class);
        mService.getVideoGames().enqueue(new Callback<Embedded>() {
            private ArrayList<VideoGames> arrayList = new ArrayList<>();

            @Override
            public void onResponse(Call<Embedded> call, Response<Embedded> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                    Embedded embedded = response.body();
                    arrayList = embedded.getEmbedded();
                    showVideoGames(arrayList);
                } else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "result code is: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Embedded> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    public void searchVideoGamesByName(String nome) {
        RetrofitService mService = RetrofitClient.getClient().create(RetrofitService.class);
        String query = "{'nome':{'$regex':'(?i)" + nome + "'}}";
        mService.searchVideoGamesByName(query).enqueue(new Callback<Embedded>() {
            private ArrayList<VideoGames> arrayList = new ArrayList<>();

            @Override
            public void onResponse(Call<Embedded> call, Response<Embedded> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                    Embedded embedded = response.body();
                    arrayList = embedded.getEmbedded();
                    if (arrayList.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Nessun elemento trovato", Toast.LENGTH_SHORT).show();
                    } else {
                        showVideoGames(arrayList);
                    }
                } else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "result code is: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Embedded> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    public void searchVideoGamesByDevelopers(String creatori) {
        RetrofitService mService = RetrofitClient.getClient().create(RetrofitService.class);
        String query = "{'creatori':{'$regex':'(?i)" + creatori + "'}}";
        mService.searchVideoGamesByDevelopers(query).enqueue(new Callback<Embedded>() {
            private ArrayList<VideoGames> arrayList = new ArrayList<>();

            @Override
            public void onResponse(Call<Embedded> call, Response<Embedded> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                    Embedded embedded = response.body();
                    arrayList = embedded.getEmbedded();
                    if (arrayList.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Nessun elemento trovato", Toast.LENGTH_SHORT).show();
                    } else {
                        showVideoGames(arrayList);
                    }
                } else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "result code is: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Embedded> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    public void searchVideoGames(String nome, String creatori) {
        RetrofitService mService = RetrofitClient.getClient().create(RetrofitService.class);
        String query = "{'creatori':{'$regex':'(?i)" + creatori + "'}},{'nome':{'$regex':'(?i)" + nome + "'}}";
        mService.searchVideoGamesByDevelopers(query).enqueue(new Callback<Embedded>() {
            private ArrayList<VideoGames> arrayList = new ArrayList<>();

            @Override
            public void onResponse(Call<Embedded> call, Response<Embedded> response) {
                if (response.isSuccessful()) {
                    Log.d("MainActivity", "posts loaded from API");
                    Embedded embedded = response.body();
                    arrayList = embedded.getEmbedded();
                    if (arrayList.isEmpty()) {
                        Toast.makeText(getBaseContext(), "Nessun elemento trovato", Toast.LENGTH_SHORT).show();
                    } else {
                        showVideoGames(arrayList);
                    }
                } else {
                    int statusCode = response.code();
                    Log.d("MainActivity", "result code is: " + statusCode);
                }
            }

            @Override
            public void onFailure(Call<Embedded> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });
    }

    public void showVideoGames(ArrayList<VideoGames> arrayList) {
        this.videoGames.clear();
        this.videoGames.addAll(arrayList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemInserted(VideoGames videoGames) {
        InterazioneServer interazioneServer = new InterazioneServer();
        interazioneServer.addVideoGames(videoGames);
        getVideoGames();
    }

    @Override
    public void onItemModified(VideoGames videoGames, String id) {
        InterazioneServer interazioneServer = new InterazioneServer();
        interazioneServer.modificaVideoGames(id, videoGames);
        getVideoGames();
    }

    @Override
    public void onItemSearched(String tipologiaRicerca, String nome, String creatore) {
        switch (tipologiaRicerca) {
            case "vuoto":
                System.out.println("_____________________________________");
                System.out.println("Vuoto");
                searchVideoGames("", "");
                break;
            case "nome":
                System.out.println("_____________________________________");
                System.out.println("Nome");
                searchVideoGamesByName(nome);
                break;
            case "creatori":
                System.out.println("_____________________________________");
                System.out.println("Creat");
                searchVideoGamesByDevelopers(creatore);
                break;
            case "nome&&creatori":
                System.out.println("_____________________________________");
                System.out.println("Norm");
                searchVideoGames(nome, creatore);
                break;
        }
    }
}
