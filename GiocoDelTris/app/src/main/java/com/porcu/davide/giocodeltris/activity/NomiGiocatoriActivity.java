package com.porcu.davide.giocodeltris.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.porcu.davide.giocodeltris.R;
import com.porcu.davide.giocodeltris.oggetti.Player;

public class NomiGiocatoriActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nomi_giocatori);

        Button btnGioca = findViewById(R.id.btnGioca);
        EditText nomePlayer1 = findViewById(R.id.edtTxtPlayer1);
        EditText nomePlayer2 = findViewById(R.id.edtTxtPlayer2);

        boolean isGiocoConMacchina = getIntent().getBooleanExtra("isGiocoConMacchina", false);
        if (isGiocoConMacchina) {
            nomePlayer2.setVisibility(View.GONE);//rimuovo il bottone che tanto non mi serve

            btnGioca.setOnClickListener((view) -> {
                String nomePlayer1Str = nomePlayer1.getText().toString().trim();

                if (!nomePlayer1Str.equals("")) {
                    Intent i = new Intent(this, TrisActivity.class);
                    Player player1 = new Player(nomePlayer1Str);
                    Player player2 = new Player("Computer", true);
                    i.putExtra("player1", player1);
                    i.putExtra("player2", player2);

                    startActivity(i);
                } else {
                    Toast.makeText(this, getResources().getString(R.string.toastInserisciNome), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            btnGioca.setOnClickListener((view) -> {
                String nomePlayer1Str = nomePlayer1.getText().toString().trim();
                String nomePlayer2Str = nomePlayer2.getText().toString().trim();

                if (!nomePlayer1Str.equals("") && !nomePlayer2Str.equals("")) {
                    Intent i = new Intent(this, TrisActivity.class);
                    Player player1 = new Player(nomePlayer1Str);
                    Player player2 = new Player(nomePlayer2Str);
                    i.putExtra("player1", player1);
                    i.putExtra("player2", player2);

                    startActivity(i);
                } else {
                    Toast.makeText(this, getResources().getString(R.string.toastInserisciNomi), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
