package com.porcu.davide.campionato.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.porcu.davide.campionato.R;
import com.porcu.davide.campionato.RFService;
import com.porcu.davide.campionato.adapter.RoundsAdapter;
import com.porcu.davide.campionato.pojo.Pojo;
import com.porcu.davide.campionato.pojo.Round;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static Gson gson = new Gson();

    private RFService mService;
    private RecyclerView rv;
    private RoundsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Lookup the recyclerview in activity layout
        rv = findViewById(R.id.rvRounds);
        rv.setLayoutManager(new LinearLayoutManager(this));


        mService = RFService.retrofit.create(RFService.class);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener((View view) -> {
            Log.d("MainActivity", "getRFResponse()");
            getRFResponse();
        });

    }

    public void getRFResponse() {
        Activity main = this;//brutto
        mService.getPojo().enqueue(new Callback<Pojo>() {

            private ArrayList<Round> rounds = new ArrayList<>();

            @Override
            public void onResponse(Call<Pojo> call, Response<Pojo> response) {

                Log.d("MainActivity", "onresponse()");

                if (response.isSuccessful()) {
                    Log.d("MainActivity", "onresponse.isSuccessful()");
                    Toast.makeText(MainActivity.this, "get response", Toast.LENGTH_SHORT);
                    rounds = (ArrayList<Round>) response.body().getRounds();
                    // MainActivity.this.showRounds(rounds);

                    // Create adapter passing in the sample user data
                    adapter = new RoundsAdapter(rounds, main);

                    // Attach the adapter to the recyclerview to populate items
                    rv.setAdapter(adapter);

                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                    Log.d("MainActivity", "handle request errors");
                }
            }

            @Override
            public void onFailure(Call<Pojo> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    /*void showRounds(ArrayList<Round> rounds){

        scrollView.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        params.setMargins(40, 10, 10, 10);
        for(final Round round : rounds){
            TextView tv = new TextView(this);
            tv.setLayoutParams(params);
            tv.setText(round.getName());
            tv.setOnClickListener((View view) ->
                    Toast.makeText(MainActivity.this, round.getName(), Toast.LENGTH_SHORT).show()
            );
            linearLayout.addView(tv);
        }
        scrollView.addView(linearLayout);
    }*/

    void showErrorMessage() {
        Log.d("MainActivity", "Error");
    }


    private void mostraDettagliGiornata(Round round) {
        Intent i = new Intent(this, DettaglioRoundActivity.class);
        i.putExtra("isGiocoConMacchina", true);
        startActivity(i);

    }

}
