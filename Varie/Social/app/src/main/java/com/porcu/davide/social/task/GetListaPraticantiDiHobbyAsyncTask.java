package com.porcu.davide.social.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.porcu.davide.social.R;
import com.porcu.davide.social.adapter.HobbyAdapter;
import com.porcu.davide.social.adapter.UserAdapter;
import com.porcu.davide.social.mobilecontainer.BasicUserInfo;
import com.porcu.davide.social.model.Hobby;
import com.porcu.davide.social.model.User;
import com.porcu.davide.social.util.Util;

import java.util.ArrayList;

/**
 * Created by davide on 27/04/18.
 */

public class GetListaPraticantiDiHobbyAsyncTask extends AsyncTask {

    private Activity activityContext;
    private int idHobby;

    public GetListaPraticantiDiHobbyAsyncTask(Activity context, int idHobby) {
        activityContext = context;
        this.idHobby = idHobby;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<User> listaPraticanti = Util.getListaUtentiPraticantiDiHobby(idHobby);
        UserAdapter userAdapter = new UserAdapter(listaPraticanti, activityContext);

        Log.d("LISTA UTENTI-> ", listaPraticanti.toString());

        return userAdapter;
    }

    @Override
    protected void onPostExecute(Object userAdapterObj) {
        super.onPostExecute(userAdapterObj);

        if (userAdapterObj != null) {
            activityContext.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    RecyclerView rv = activityContext.findViewById(R.id.rvPraticanti);
                    rv.setLayoutManager(new LinearLayoutManager(activityContext));
                    rv.setAdapter((UserAdapter) userAdapterObj);
                }
            });
        }
    }


}
