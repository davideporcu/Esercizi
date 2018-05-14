package com.porcu.davide.socialapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.porcu.davide.socialapp.R;

import java.util.ArrayList;

public class InfoUtenteAdapter extends RecyclerView.Adapter<InfoUtenteAdapter.ViewHolder> {

    private static ArrayList<Pair<String, String>> listaInfoUtente;
    private static Context context;

    public InfoUtenteAdapter(ArrayList<Pair<String, String>> listaInfoUtente, Context context) {
        this.listaInfoUtente = listaInfoUtente;
        this.context = context;
    }


    @Override
    public InfoUtenteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_info_utente, parent, false);
        // Return a new holder instance
        InfoUtenteAdapter.ViewHolder viewHolder = new InfoUtenteAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InfoUtenteAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Pair<String, String> infoUtente = listaInfoUtente.get(position);
        // Set item views based on the data model

        TextView txtEtichetta = holder.txtEtichetta;
        TextView txtInfo = holder.txtInfo;

        txtEtichetta.setText(infoUtente.first);
        txtInfo.setText(infoUtente.second);

    }

    @Override
    public int getItemCount() {
        return listaInfoUtente.size();
    }

    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtEtichetta;
        public TextView txtInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtEtichetta = itemView.findViewById(R.id.txtEtichetta);
            txtInfo = itemView.findViewById(R.id.txtInfo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position
        }

    }
}
