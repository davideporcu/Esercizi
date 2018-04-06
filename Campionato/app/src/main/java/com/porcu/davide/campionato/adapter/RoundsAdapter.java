package com.porcu.davide.campionato.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.porcu.davide.campionato.R;
import com.porcu.davide.campionato.activity.DettaglioRoundActivity;
import com.porcu.davide.campionato.pojo.Round;

import java.util.ArrayList;

import static com.porcu.davide.campionato.activity.MainActivity.gson;

/**
 * Created by studente on 24.03.18.
 */

public class RoundsAdapter extends RecyclerView.Adapter<RoundsAdapter.ViewHolder> {

    private static ArrayList<Round> rounds;
    private static Context context;

    public RoundsAdapter(ArrayList<Round> rounds, Context context) {
        this.rounds = rounds;
        this.context = context;
    }


    @Override
    public RoundsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_round, parent, false);
        // Return a new holder instance
        RoundsAdapter.ViewHolder viewHolder = new RoundsAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RoundsAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Round round = rounds.get(position);

        // Set item views based on the data model
        TextView textView = holder.txtDataRound;
        textView.setText(round.getName());

        Button button = holder.btnDettaglio;
        button.setText(R.string.btnVisualizza);
        button.setOnClickListener((View view) -> {
                    mostraDettagliGiornata(round);
                }
        );
    }

    @Override
    public int getItemCount() {
        return rounds.size();
    }


    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtDataRound;
        public Button btnDettaglio;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDataRound = itemView.findViewById(R.id.txtDataRound);
            btnDettaglio = itemView.findViewById(R.id.btnDettaglio);
            btnDettaglio.setOnClickListener((View v) -> {
                int position = getLayoutPosition();
                Round round = rounds.get(position);
                //Toast.makeText(context, contact.getEmail(), Toast.LENGTH_SHORT).show();

            });
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position
            Round round = rounds.get(position);

            //apri giornata con lista partite

            Toast.makeText(context, round.getName() + ": " + position, Toast.LENGTH_SHORT).show();
        }

    }


    private void mostraDettagliGiornata(Round round) {
        Intent i = new Intent(context, DettaglioRoundActivity.class);
        String jsonRound = gson.toJson(round);
        i.putExtra("round", jsonRound);
        context.startActivity(i);
    }

}
