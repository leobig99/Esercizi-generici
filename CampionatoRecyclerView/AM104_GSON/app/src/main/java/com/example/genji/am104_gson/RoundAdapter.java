package com.example.genji.am104_gson;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrea on 26/03/2018.
 */

public class RoundAdapter extends RecyclerView.Adapter<RoundAdapter.ViewHolder> {

    private static List<Round> rounds;


    public RoundAdapter(MainActivity activity, List<Round> rounds) {
        this.rounds = rounds;
    }

    @Override
    public RoundAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_round, parent, false);
        return new ViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(RoundAdapter.ViewHolder holder, int position) {
        Round round = rounds.get(position);
        holder.setItem(round);
    }

    @Override
    public int getItemCount() {
        return rounds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView giornata;
        private List<Match> matches = new ArrayList<>();
        private RecyclerView recyclerView;
        private Round round;


        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_view_matches);
            giornata = itemView.findViewById(R.id.date);
            itemView.setOnClickListener(this);
        }

        void setItem(Round round) {
            this.round = round;
            giornata.setText(round.getName());
        }

        @Override
        public void onClick(View view) {
            MatchesAdapter adapter = new MatchesAdapter(matches);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(itemView.getContext());


            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setAdapter(adapter);
            if (matches.size() == 0) {
                matches.addAll(round.getMatches());
                adapter.notifyDataSetChanged();
            } else {
                matches.clear();
            }
        }
    }
}
