package com.example.genji.am104_gson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by andrea on 26/03/2018.
 */

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private static List<Match> matches;

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @Override
    public MatchesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_match, parent, false);
        return new MatchesAdapter.ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(MatchesAdapter.ViewHolder holder, int position) {
        Match m = matches.get(position);
        holder.setGiorno(m.getDate());
        holder.setSquadre(m.getTeam1().getName() + " - " + m.getTeam2().getName());
        holder.setRisultato(m.getScore1() + " - " + m.getScore2());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView giorno;
        private TextView squadre;
        private TextView risultato;

        public ViewHolder(View itemView) {
            super(itemView);
            giorno = itemView.findViewById(R.id.date);
            squadre = itemView.findViewById(R.id.team);
            risultato = itemView.findViewById(R.id.score);
        }

        public void setGiorno(String giorno) {
            this.giorno.setText(giorno);
        }

        public void setSquadre(String squadre) {
            this.squadre.setText(squadre);
        }

        public void setRisultato(String risultato) {
            this.risultato.setText(risultato);
        }

    }
}
