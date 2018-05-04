package com.porcu.davide.social.task;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Spinner;

import com.porcu.davide.social.R;
import com.porcu.davide.social.adapter.HobbyAdapter;
import com.porcu.davide.social.model.Hobby;
import com.porcu.davide.social.util.Util;

import java.util.ArrayList;

/**
 * Created by davide on 27/04/18.
 */

public class GetListaHobbyAsyncTask extends AsyncTask {


    private Activity activityContext;

    public GetListaHobbyAsyncTask(Activity context) {
        activityContext = context;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<Hobby> listaHobby = Util.getListaHobby();

        Log.d("§#@ DEBUG ", listaHobby.get(0).toString());

        HobbyAdapter hobbyAdapter = new HobbyAdapter(listaHobby, activityContext);

        Log.d("§#@ DEBUG ", listaHobby.toString());

        return hobbyAdapter;
    }

    @Override
    protected void onPostExecute(Object hobbyAdapterObj) {
        super.onPostExecute(hobbyAdapterObj);

        if (hobbyAdapterObj != null) {
            activityContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Spinner spinner = activityContext.findViewById(R.id.spinnerHobby);
                    spinner.setAdapter((HobbyAdapter) hobbyAdapterObj);
                }
            });
        }
    }


}
