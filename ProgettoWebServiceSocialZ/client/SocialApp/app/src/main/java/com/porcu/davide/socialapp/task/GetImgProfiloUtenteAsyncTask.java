package com.porcu.davide.socialapp.task;


import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.porcu.davide.socialapp.fragment.BackgroundTaskState;
import com.porcu.davide.socialapp.model.StructuredHobby;
import com.porcu.davide.socialapp.util.Util;

import java.util.ArrayList;

/**
 * Created by davide on 27/04/18.
 */

public class GetImgProfiloUtenteAsyncTask extends AsyncTask {

    private BackgroundTaskState caller;
    private String username;

    public GetImgProfiloUtenteAsyncTask(BackgroundTaskState caller, String username) {
        this.caller = caller;
        this.username = username;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        String base64Img = Util.getImgProfiloUtente(username);
        Bitmap imgProfilo = Util.getBitmapFromBase64(base64Img);
        return imgProfilo;
    }

    @Override
    protected void onPostExecute(final Object imgProfilo) {
        super.onPostExecute(imgProfilo);
        caller.onBackgroundTaskCompleted(imgProfilo);
    }
}