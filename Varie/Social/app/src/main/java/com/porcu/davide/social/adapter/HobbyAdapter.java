package com.porcu.davide.social.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;


import com.porcu.davide.social.R;
import com.porcu.davide.social.model.Hobby;

import java.util.ArrayList;

public class HobbyAdapter extends ArrayAdapter<Hobby> {

    private final Activity context;
    // the hobbies arrayList
    private ArrayList<Hobby> hobbies;

    public HobbyAdapter(ArrayList<Hobby> listaHobby, Activity context) {
        super(context, R.layout.item_user_spinner, listaHobby);
        this.context = context;
        this.hobbies = listaHobby;
    }

    public Activity getActivityContext(){
        return context;
    }

    private static final String TAG = "ViewHolder";

    // this object will be tag
    static class ViewHolder {
        public TextView txtNomeHobby;
    }


    @Override
    public Hobby getItem(int position) {
        return hobbies.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        ViewHolder viewHolder;


        Hobby h = hobbies.get(position);

        // reuse view: ViewHolder pattern
        if (itemView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            itemView = inflater.inflate(R.layout.item_user_spinner, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.txtNomeHobby = itemView.findViewById(R.id.txtNomeHobby);
            // take memory of the view
            itemView.setTag(viewHolder);
        } else {
            // reuse the object
            viewHolder = (ViewHolder) itemView.getTag();
            // Log steTag()
            Log.d(TAG, "getTag() for object in position: " + position);
        }
        viewHolder.txtNomeHobby.setText(h.getNomeHobby());
        return itemView;
    }

}
