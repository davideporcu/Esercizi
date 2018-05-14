package com.porcu.davide.socialapp.task;

import android.os.AsyncTask;

import com.porcu.davide.socialapp.fragment.BackgroundTaskState;
import com.porcu.davide.socialapp.fragment.UserPageFragment;
import com.porcu.davide.socialapp.model.InfoUtente;
import com.porcu.davide.socialapp.model.StructuredSearchedGroup;
import com.porcu.davide.socialapp.util.Util;

import java.util.ArrayList;

public class GetInfoUtenteAsyncTask extends AsyncTask{

    private BackgroundTaskState caller;
    private String username;

    public GetInfoUtenteAsyncTask(BackgroundTaskState caller, String username) {
        this.caller = caller;
        this.username = username;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        InfoUtente infoUtente = Util.getInfoDiUtente(username);

        if (infoUtente == null) {
            infoUtente=new InfoUtente();
        }

        return infoUtente;
    }

    @Override
    protected void onPostExecute(final Object info) {
        super.onPostExecute(info);
        caller.onBackgroundTaskCompleted(info);
    }
}
