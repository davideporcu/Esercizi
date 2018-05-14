package com.porcu.davide.socialapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.porcu.davide.socialapp.R;
import com.porcu.davide.socialapp.model.StructuredHobby;

import java.util.ArrayList;

public class StructuredHobbyAdapter extends RecyclerView.Adapter<StructuredHobbyAdapter.ViewHolder> {

    private static ArrayList<StructuredHobby> structuredHobbyList;
    private static Context context;

    public StructuredHobbyAdapter(ArrayList<StructuredHobby> structuredHobbyList, Context context) {
        this.structuredHobbyList = structuredHobbyList;
        this.context = context;
    }


    @Override
    public StructuredHobbyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_structured_hobby, parent, false);
        // Return a new holder instance
        StructuredHobbyAdapter.ViewHolder viewHolder = new StructuredHobbyAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StructuredHobbyAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        StructuredHobby sh = structuredHobbyList.get(position);
        // Set item views based on the data model

        TextView txtNome = holder.txtNome;
        TextView txtCognome = holder.txtDataInizio;

        txtNome.setText(sh.getNome());
        txtCognome.setText(sh.getDataInizio());

    }

    @Override
    public int getItemCount() {
        return structuredHobbyList.size();
    }

    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNome;
        public TextView txtDataInizio;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtDataInizio = itemView.findViewById(R.id.txtDataInizio);

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
