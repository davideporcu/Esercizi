package com.porcu.davide.socialapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.porcu.davide.socialapp.R;
import com.porcu.davide.socialapp.activity.FragmentEventListener;
import com.porcu.davide.socialapp.model.StructuredSearchedUser;

import java.util.ArrayList;

public class SearchedUserAdapter extends RecyclerView.Adapter<SearchedUserAdapter.ViewHolder> {

    private static ArrayList<StructuredSearchedUser> structuredSearchedUsersList;
    private static Activity context;

    public SearchedUserAdapter(ArrayList<StructuredSearchedUser> structuredSearchedUsersList, Activity context) {
        this.structuredSearchedUsersList = structuredSearchedUsersList;
        this.context = context;
    }


    @Override
    public SearchedUserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_searched_user, parent, false);
        // Return a new holder instance
        SearchedUserAdapter.ViewHolder viewHolder = new SearchedUserAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SearchedUserAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        StructuredSearchedUser structuredSearchedUser = structuredSearchedUsersList.get(position);
        // Set item views based on the data model

        TextView txtNomeCognome = holder.txtNomeCognome;
        TextView txtUsername = holder.txtUsername;
        ImageView imgProfilo = holder.imgProfilo;

        txtNomeCognome.setText(structuredSearchedUser.getNomeCognome());
        txtUsername.setText(structuredSearchedUser.getUsername());
        imgProfilo.setImageBitmap(structuredSearchedUser.getBtmpImgProfilo());

    }

    @Override
    public int getItemCount() {
        return structuredSearchedUsersList.size();
    }

    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNomeCognome;
        public TextView txtUsername;
        public ImageView imgProfilo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNomeCognome = itemView.findViewById(R.id.txtNomeCognome);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            imgProfilo = itemView.findViewById(R.id.imgProfilo);
            ImageButton imgBtn = itemView.findViewById(R.id.imgViewInfo);
            imgBtn.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int position = getLayoutPosition(); // gets item position
            StructuredSearchedUser ssu = structuredSearchedUsersList.get(position);
            //Toast.makeText(context, "Click su utente!", Toast.LENGTH_SHORT).show();
            FragmentEventListener activityListener = (FragmentEventListener) context;
            activityListener.fragmentEventHadler(ssu.getNomeCognome(), ssu.getUsername());

        }

    }
}