package com.porcu.davide.campionato.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.porcu.davide.campionato.R;
import com.porcu.davide.campionato.adapter.MatchesAdapter;
import com.porcu.davide.campionato.adapter.RoundsAdapter;
import com.porcu.davide.campionato.pojo.Match;
import com.porcu.davide.campionato.pojo.Round;

import java.util.ArrayList;
import java.util.List;

public class DettaglioRoundActivity extends AppCompatActivity {

    private RecyclerView rv;
    private MatchesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dettaglio_round);

        Gson gson = new Gson();
        String jsonRound = getIntent().getStringExtra("round");
        Round round = gson.fromJson(jsonRound, Round.class);

        System.out.println(round.getMatches());

        ArrayList<Match> matches = (ArrayList<Match>) round.getMatches();//down cast pericoloso
        rv = findViewById(R.id.rvMatches);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MatchesAdapter(matches, this);
        // Attach the adapter to the recyclerview to populate items
        rv.setAdapter(adapter);
    }


}
