package com.porcu.davide.socialapp.task;

import android.os.AsyncTask;

import com.porcu.davide.socialapp.fragment.BackgroundTaskState;
import com.porcu.davide.socialapp.fragment.UserPageFragment;
import com.porcu.davide.socialapp.model.Hobby;
import com.porcu.davide.socialapp.model.InfoUtente;
import com.porcu.davide.socialapp.model.StructuredHobby;
import com.porcu.davide.socialapp.util.Util;

import java.util.ArrayList;

public class GetListaHobbyPraticatiDaUtente extends AsyncTask {

    private BackgroundTaskState caller;
    private String username;

    public GetListaHobbyPraticatiDaUtente(BackgroundTaskState caller, String username) {
        this.caller = caller;
        this.username = username;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<StructuredHobby> lista = Util.getListaHobbyPraticatiDaUtente(username);

        if (lista == null) {
            lista = new ArrayList<>();
        }

        return lista;
    }

    @Override
    protected void onPostExecute(final Object info) {
        super.onPostExecute(info);
        caller.onBackgroundTaskCompleted(info);
    }
}
