package com.porcu.davide.social.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.porcu.davide.social.R;

import java.util.ArrayList;

/**
 * Created by davide on 22/04/18.
 */

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.ViewHolder> {

    private static ArrayList<String> emails;
    private static Context context;

    public EmailAdapter(ArrayList<String> emails, Context context) {
        this.emails = emails;
        this.context = context;
    }


    @Override
    public EmailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_email, parent, false);
        // Return a new holder instance
        EmailAdapter.ViewHolder viewHolder = new EmailAdapter.ViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EmailAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        String strEmail = emails.get(position);
        // Set item views based on the data model

        TextView txtEmail = holder.txtEmail;

        txtEmail.setText(strEmail);

    }

    @Override
    public int getItemCount() {
        return emails.size();
    }


    // view holder class
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtEmail;


        public ViewHolder(View itemView) {
            super(itemView);
            txtEmail = itemView.findViewById(R.id.txtEmail);
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