package com.porcu.davide.socialapp.task;

import android.os.AsyncTask;
import android.widget.Toast;

import com.porcu.davide.socialapp.fragment.BackgroundTaskState;
import com.porcu.davide.socialapp.model.StructuredSearchedGroup;
import com.porcu.davide.socialapp.model.StructuredSearchedUser;
import com.porcu.davide.socialapp.util.Util;

import java.util.ArrayList;


/**
 * Created by davide on 27/04/18.
 */

public class GetListaSearchedGroupAsyncTask extends AsyncTask {


    private BackgroundTaskState caller;
    private String query;

    public GetListaSearchedGroupAsyncTask(BackgroundTaskState caller, String query) {
        this.caller = caller;
        this.query = query;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<StructuredSearchedGroup> listaStructuredSearchedGroup = Util.getListaStructuredSearchedGroup(query);

        if (listaStructuredSearchedGroup == null) {
            listaStructuredSearchedGroup=new ArrayList<>();
        }

        return listaStructuredSearchedGroup;
    }

    @Override
    protected void onPostExecute(final Object lista) {
        super.onPostExecute(lista);
        caller.onBackgroundTaskCompleted(lista);
    }


}
