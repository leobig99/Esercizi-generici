package com.example.andrea.mongo;

/**
 * Created by andrea on 24/04/2018.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class VideoGamesAdapter extends RecyclerView.Adapter<VideoGamesAdapter.ViewHolder> {

    private static ArrayList<VideoGames> videoGames;
    private Context context;

    public VideoGamesAdapter(Context context, ArrayList<VideoGames> videoGames) {
        this.context = context;
        this.videoGames = videoGames;
    }

    @Override
    public VideoGamesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_videogames, parent, false);
        return new VideoGamesAdapter.ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(VideoGamesAdapter.ViewHolder holder, int position) {
        VideoGames v = videoGames.get(position);
        holder.setItem(v);
    }

    @Override
    public int getItemCount() {
        return videoGames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Button modifica;
        private Button cancella;
        private TextView nome;
        private TextView creatori;
        private TextView descrizione;

        private String nominativo;
        private VideoGames videoGames;

        public ViewHolder(View itemView) {
            super(itemView);
            modifica = itemView.findViewById(R.id.button);
            cancella = itemView.findViewById(R.id.button2);
            nome = itemView.findViewById(R.id.nome);
            creatori = itemView.findViewById(R.id.creatori);
            descrizione = itemView.findViewById(R.id.descrizione);

            itemView.setOnClickListener(this);

            modifica.setOnClickListener(view -> {
                FragmentManager manager = ((MainActivity) context).getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString("nome", nominativo);
                bundle.putString("id", "" + videoGames.getId().get$oid());
                bundle.putString("descrizione", videoGames.getDescrizione());
                bundle.putString("creatori", videoGames.getCreatori());
                ModificaDialogFragment dialogFragment = new ModificaDialogFragment();
                dialogFragment.setArguments(bundle);
                dialogFragment.show(manager, "Modifica VideoGmaes");
            });

            cancella.setOnClickListener(view -> {

                String titleText = "Cancella";
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.colorAccent));
                SpannableStringBuilder ssBuilder = new SpannableStringBuilder(titleText);
                ssBuilder.setSpan(
                        foregroundColorSpan,
                        0,
                        titleText.length(),
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                );


                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext(), R.style.MyCustomTheme);
                alert.setTitle(ssBuilder);
                alert.setMessage("Sei sicuro di voler rimuovere " + nominativo + "?");
                alert.setPositiveButton("OK", (dialogInterface, i) -> {
                    InterazioneServer interazioneServer = new InterazioneServer();
                    interazioneServer.deleteVideoGames(videoGames.getId().get$oid());
                });
                alert.setNegativeButton("Cancel", (dialogInterface, i) -> {
                });
                Dialog dialog = alert.create();
                dialog.show();
            });
        }

        public void setItem(VideoGames videoGames) {
            this.nome.setText(videoGames.getNome());
            this.creatori.setText(videoGames.getCreatori());
            nominativo = videoGames.getNome();
            this.videoGames = videoGames;
        }

        @Override
        public void onClick(View view) {
            String text = descrizione.getText().toString();
            if (text.equals("Click to see description")) {
                String app = videoGames.getDescrizione();
                if (app.equals("")) {
                    descrizione.setText("Nessuna descrizione");
                } else {
                    descrizione.setText(app);
                }
            } else {
                descrizione.setText("Click to see description");
            }
        }
    }
}

