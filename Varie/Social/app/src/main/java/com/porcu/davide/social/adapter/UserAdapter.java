package com.porcu.davide.social.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.porcu.davide.social.R;
import com.porcu.davide.social.model.User;

import java.util.ArrayList;

/**
 * Created by davide on 22/04/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private static ArrayList<User> users;
    private static Context context;

    public UserAdapter(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
    }


    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_user, parent, false);
        // Return a new holder instance
        UserAdapter.ViewHolder viewHolder = new UserAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        User user = users.get(position);
        // Set item views based on the data model

        TextView txtNome = holder.txtNome;
        TextView txtCognome = holder.txtCognome;
        TextView txtUsername = holder.txtUsername;
        TextView txtEmail = holder.txtEmail;
        ImageView imgProfilo = holder.imgProfilo;


        txtNome.setText(user.getNome());
        txtCognome.setText(user.getCognome());
        txtUsername.setText(user.getUsername());
        txtEmail.setText(user.getEmail());
        imgProfilo.setImageBitmap(user.getBtmpImgProfilo());

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtNome;
        public TextView txtCognome;
        public TextView txtUsername;
        public TextView txtEmail;
        public ImageView imgProfilo;

        public ViewHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtCognome = itemView.findViewById(R.id.txtCognome);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            imgProfilo = itemView.findViewById(R.id.imgProfilo);

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
