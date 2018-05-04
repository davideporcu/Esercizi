package com.porcu.davide.social.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.porcu.davide.social.R;
import com.porcu.davide.social.model.Hobby;
import com.porcu.davide.social.task.GetListaHobbyAsyncTask;
import com.porcu.davide.social.task.GetListaPraticantiDiHobbyAsyncTask;
import com.porcu.davide.social.util.Util;


public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rvPraticanti);
        rv.setLayoutManager(new LinearLayoutManager(this));

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener((View view) -> {
            Log.d("MainActivity", "CLICK!");
            EditText txtIP = findViewById(R.id.txtIP);
            EditText txtPorta = findViewById(R.id.txtPorta);

            Util.indirizzoIPServer = txtIP.getText().toString();
            Util.portaServer = txtPorta.getText().toString();

            new GetListaHobbyAsyncTask(this).execute();
        });

        Spinner spinnerHobby = (Spinner) findViewById(R.id.spinnerHobby);
        spinnerHobby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Hobby hobbyScelto=(Hobby)spinnerHobby.getAdapter().getItem(position);

                new GetListaPraticantiDiHobbyAsyncTask((Activity)view.getContext(),hobbyScelto.getIdHobby()).execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

    private void makeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
