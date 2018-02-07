package com.porcu.davide.giocodeltris.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.porcu.davide.giocodeltris.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGiocoControUomo = findViewById(R.id.btnGiocoControUomo);
        Button btnGiocoControMacchina = findViewById(R.id.btnGiocoControMacchina);

        btnGiocoControUomo.setOnClickListener((view) -> {
            Intent i = new Intent(this, NomiGiocatoriActivity.class);
            i.putExtra("isGiocoConMacchina", false);
            startActivity(i);
        });

        btnGiocoControMacchina.setOnClickListener((view) -> {
            Intent i = new Intent(this, NomiGiocatoriActivity.class);
            i.putExtra("isGiocoConMacchina", true);
            startActivity(i);
        });


    }
}
