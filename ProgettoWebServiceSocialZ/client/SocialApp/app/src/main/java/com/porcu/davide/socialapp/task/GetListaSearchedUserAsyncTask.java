package com.porcu.davide.socialapp.task;

import android.app.Activity;
import android.database.AbstractCursor;
import android.os.AsyncTask;
import android.util.Log;

import com.porcu.davide.socialapp.fragment.BackgroundTaskState;
import com.porcu.davide.socialapp.model.BasicUserInfo;
import com.porcu.davide.socialapp.model.Hobby;
import com.porcu.davide.socialapp.model.StructuredSearchedUser;
import com.porcu.davide.socialapp.util.Util;

import java.util.ArrayList;


/**
 * Created by davide on 27/04/18.
 */

public class GetListaSearchedUserAsyncTask extends AsyncTask {


    private BackgroundTaskState caller;
    private String query;

    public GetListaSearchedUserAsyncTask(BackgroundTaskState caller, String query) {
        this.caller = caller;
        this.query = query;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<StructuredSearchedUser> listaStructuredSearchedUser = Util.getListaStructuredSearchedUser(query);

        if (listaStructuredSearchedUser == null) {
            listaStructuredSearchedUser=new ArrayList<>();
        }

        return listaStructuredSearchedUser;
    }

    @Override
    protected void onPostExecute(final Object listaPraticanti) {
        super.onPostExecute(listaPraticanti);
        caller.onBackgroundTaskCompleted(listaPraticanti);
    }



}
