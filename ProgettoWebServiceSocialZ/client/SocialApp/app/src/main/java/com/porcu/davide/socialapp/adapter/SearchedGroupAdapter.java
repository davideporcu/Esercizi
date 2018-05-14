package com.porcu.davide.socialapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.porcu.davide.socialapp.R;
import com.porcu.davide.socialapp.model.StructuredSearchedGroup;
import com.porcu.davide.socialapp.model.StructuredSearchedUser;

import java.util.ArrayList;

public class SearchedGroupAdapter extends RecyclerView.Adapter<SearchedGroupAdapter.ViewHolder> {

    private static ArrayList<StructuredSearchedGroup> structuredSearchedGroupsList;
    private static Context context;

    public SearchedGroupAdapter(ArrayList<StructuredSearchedGroup> structuredSearchedGroupsList, Context context) {
        this.structuredSearchedGroupsList = structuredSearchedGroupsList;
        this.context = context;
    }


    @Override
    public SearchedGroupAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_searched_group, parent, false);
        // Return a new holder instance
        SearchedGroupAdapter.ViewHolder viewHolder = new SearchedGroupAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchedGroupAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        StructuredSearchedGroup structuredSearchedGroup = structuredSearchedGroupsList.get(position);
        // Set item views based on the data model

        TextView txtNomeGruppo = holder.txtNomeGruppo;
        ImageView imgGruppo = holder.imgGruppo;

        txtNomeGruppo.setText(structuredSearchedGroup.getNomeGruppo());
        imgGruppo.setImageBitmap(structuredSearchedGroup.getBtmpImgGruppo());

    }

    @Override
    public int getItemCount() {
        return structuredSearchedGroupsList.size();
    }

    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNomeGruppo;
        public ImageView imgGruppo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNomeGruppo = itemView.findViewById(R.id.txtNomeGruppo);
            imgGruppo = itemView.findViewById(R.id.imgGruppo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position
            //Match match = matches.get(position);
            //Toast.makeText(context, match.getName() + ": " + position, Toast.LENGTH_SHORT).show();
            Toast.makeText(context, "Click su gruppo!", Toast.LENGTH_SHORT).show();
        }

    }
}