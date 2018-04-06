package com.porcu.davide.campionato.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.porcu.davide.campionato.R;
import com.porcu.davide.campionato.pojo.Match;
import com.porcu.davide.campionato.pojo.Round;

import java.util.ArrayList;

/**
 * Created by davide on 24/03/18.
 */

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {

    private static ArrayList<Match> matches;
    private static Context context;

    public MatchesAdapter(ArrayList<Match> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }


    @Override
    public MatchesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_match, parent, false);
        // Return a new holder instance
        MatchesAdapter.ViewHolder viewHolder = new MatchesAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MatchesAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Match match = matches.get(position);

        // Set item views based on the data model
        TextView txtNomeTeam1 = holder.txtNomeTeam1;
        TextView txtNomeTeam2 = holder.txtNomeTeam2;
        TextView txtPuntiTeam1 = holder.txtPuntiTeam1;
        TextView txtPuntiTeam2 = holder.txtPuntiTeam2;

        txtNomeTeam1.setText(match.getTeam1().getName());
        txtNomeTeam2.setText(match.getTeam2().getName());
        txtPuntiTeam1.setText(match.getScore1().toString());
        txtPuntiTeam2.setText(match.getScore2().toString());

    }

    @Override
    public int getItemCount() {
        return matches.size();
    }


    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNomeTeam1;
        public TextView txtNomeTeam2;
        public TextView txtPuntiTeam1;
        public TextView txtPuntiTeam2;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNomeTeam1 = itemView.findViewById(R.id.txtNomeTeam1);
            txtNomeTeam2 = itemView.findViewById(R.id.txtNomeTeam2);
            txtPuntiTeam1 = itemView.findViewById(R.id.txtPuntiTeam1);
            txtPuntiTeam2 = itemView.findViewById(R.id.txtPuntiTeam2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //int position = getLayoutPosition(); // gets item position
            //Match match = matches.get(position);
            //Toast.makeText(context, match.getName() + ": " + position, Toast.LENGTH_SHORT).show();
        }

    }

}
