package com.porcu.davide.socialapp.task;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.porcu.davide.socialapp.R;
import com.porcu.davide.socialapp.adapter.UserAdapter;
import com.porcu.davide.socialapp.fragment.BackgroundTaskState;
import com.porcu.davide.socialapp.fragment.UserPageFragment;
import com.porcu.davide.socialapp.model.BasicUserInfo;
import com.porcu.davide.socialapp.util.Util;

import java.util.ArrayList;


public class GetListaPraticantiDiHobbyAsyncTask extends AsyncTask {

    private BackgroundTaskState caller;
    private int idHobby;

    public GetListaPraticantiDiHobbyAsyncTask(BackgroundTaskState caller, int idHobby) {
        this.caller = caller;
        this.idHobby = idHobby;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ArrayList<BasicUserInfo> listaPraticanti = Util.getListaUtentiPraticantiDiHobby(idHobby);
        if(listaPraticanti==null){
            listaPraticanti=new ArrayList<>();
        }

        return listaPraticanti;
    }

    @Override
    protected void onPostExecute(final Object listaPraticanti) {
        super.onPostExecute(listaPraticanti);
        caller.onBackgroundTaskCompleted(listaPraticanti);
    }


}
