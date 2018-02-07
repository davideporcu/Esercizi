package com.porcu.davide.giocodeltris.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.porcu.davide.giocodeltris.R;
import com.porcu.davide.giocodeltris.oggetti.Player;

public class EsitoPartitaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esito_partita);

        TextView txtVincitore = findViewById(R.id.txtNomeVincitore);
        TextView txtEtcNomeVincitore = findViewById(R.id.txtEtcNomeVincitore);
        Object vincitoreObj = getIntent().getSerializableExtra("winnerPlayer");
        boolean isControMacchina = getIntent().getBooleanExtra("isGiocoConMacchina", false);
        if (vincitoreObj != null) {
            Player vincitore = (Player) vincitoreObj;

            if (!isControMacchina) {
                txtVincitore.setText(vincitore.getNome());
                txtEtcNomeVincitore.setText(getResources().getString(R.string.txtEtcVincitore));
            } else {
                txtEtcNomeVincitore.setText("");
                if (vincitore.getIsMacchina()) {
                    txtVincitore.setText(getResources().getString(R.string.strHaiPerso));
                } else {
                    txtVincitore.setText(getResources().getString(R.string.strHaiVinto));
                }
            }
        } else {
            //messaggio pareggio
            txtVincitore.setText(getResources().getString(R.string.strPareggio));
            txtEtcNomeVincitore.setText("");
        }

        Button btnGiocaAncora = findViewById(R.id.btnGiocaAncora);
        btnGiocaAncora.setOnClickListener((view) -> {
            Intent i = new Intent();
            setResult(RESULT_OK, i);
            finish();
        });


    }

}