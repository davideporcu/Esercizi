package com.porcu.davide.giocodeltris.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.porcu.davide.giocodeltris.R;
import com.porcu.davide.giocodeltris.oggetti.Mossa;
import com.porcu.davide.giocodeltris.oggetti.Notifica;
import com.porcu.davide.giocodeltris.oggetti.Player;
import com.porcu.davide.giocodeltris.oggetti.Tris;

import java.util.Observable;
import java.util.Observer;

public class TrisActivity extends AppCompatActivity implements Observer {

    static final int ESITO_PARTITA_ACTIVITY = 3;

    private Tris giocoTris;
    private Button[][] scacchiera;

    private TextView txtNomePlayer1;
    private TextView txtNomePlayer2;
    private TextView txtSimboloPlayer1;
    private TextView txtSimboloPlayer2;
    private TextView txtPuntiPlayer1;
    private TextView txtPuntiPlayer2;
    private TextView txtNomeGicocatoreDiTurno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tris);

        Player player1 = (Player) getIntent().getSerializableExtra("player1");
        Player player2 = (Player) getIntent().getSerializableExtra("player2");

        giocoTris = new Tris(player1, player2);
        giocoTris.addObserver(this);

        txtNomePlayer1 = findViewById(R.id.txtNomePlayer1);
        txtNomePlayer2 = findViewById(R.id.txtNomePlayer2);
        txtSimboloPlayer1 = findViewById(R.id.txtSimboloPlayer1);
        txtSimboloPlayer2 = findViewById(R.id.txtSimboloPlayer2);
        txtPuntiPlayer1 = findViewById(R.id.txtPuntiPlayer1);
        txtPuntiPlayer2 = findViewById(R.id.txtPuntiPlayer2);
        txtNomeGicocatoreDiTurno = findViewById(R.id.txtNomeGicocatoreDiTurno);

        txtNomePlayer1.setText(player1.getNome());
        txtNomePlayer2.setText(player2.getNome());

        scacchiera = new Button[3][3];

        Resources resources = getResources();
        String name = getPackageName();
        for (int riga = 0; riga < 3; riga++) {
            for (int colonna = 0; colonna < 3; colonna++) {
                scacchiera[riga][colonna] = findViewById(resources.getIdentifier("btn" + riga + "" + colonna, "id", name));
                scacchiera[riga][colonna].setText("");

                int finalColonna = colonna;//for lambda
                int finalRiga = riga;
                scacchiera[riga][colonna].setOnClickListener(view -> {
                    giocoTris.doMossa(new Mossa(finalRiga, finalColonna));
                });
            }
        }
        giocoTris.startGioco();
    }

    @Override
    public void update(Observable observable, Object notificaObj) {
        Notifica notifica = (Notifica) notificaObj;
        Object[] contenuto = notifica.getContenuto();
        switch (notifica.getTipoNotifica()) {
            case Notifica.DEBUGMESSAGE:
                Toast.makeText(this, contenuto[0].toString(), Toast.LENGTH_SHORT).show();
                break;
            case Notifica.UPDATESTART:
                Player player1 = (Player) contenuto[0];
                Player player2 = (Player) contenuto[1];
                Player playerDiTurno = (Player) contenuto[2];
                //nel caso di restart gioco
                for (Button[] rigaBtns : scacchiera) {
                    for (Button btn : rigaBtns) {
                        btn.setText("");
                    }
                }
                txtSimboloPlayer1.setText(player1.getSimbolo().toString());
                txtSimboloPlayer2.setText(player2.getSimbolo().toString());
                txtPuntiPlayer1.setText(player1.getPuntiVittorie() + "");
                txtPuntiPlayer2.setText(player2.getPuntiVittorie() + "");
                if (!player1.getIsMacchina() && !player2.getIsMacchina()) {//la macchina fa la mossa in automatico
                    txtNomeGicocatoreDiTurno.setText(getResources().getString(R.string.toastTurnoDi) + " " + playerDiTurno.getNome());
                }
                break;
            case Notifica.UPDATEMOSSA:
                //aggiorna e mostra la mossa
                Mossa mossaUpdate = (Mossa) contenuto[0];
                Player player = (Player) contenuto[1];
                Button btnMossa = scacchiera[mossaUpdate.getRiga()][mossaUpdate.getColonna()];
                btnMossa.setText(player.getSimbolo().toString());
                Player playerSuccessivo = (Player) contenuto[2];
                if (!player.getIsMacchina() && !playerSuccessivo.getIsMacchina()) {
                    txtNomeGicocatoreDiTurno.setText(getResources().getString(R.string.toastTurnoDi) + " " + playerSuccessivo.getNome());
                    Toast.makeText(this, getResources().getString(R.string.toastTurnoDi) + " " + playerSuccessivo.getNome(), Toast.LENGTH_SHORT).show();
                }
                break;
            case Notifica.UPDATEFORWINNER:
                //notifica chi è il vincitore e il nuovo punteggio...
                Mossa mossaWinner = (Mossa) contenuto[0];
                Player winner = (Player) contenuto[1];
                Button btnMossaWinner = scacchiera[mossaWinner.getRiga()][mossaWinner.getColonna()];
                btnMossaWinner.setText(winner.getSimbolo().toString());

                Intent i = new Intent(this, EsitoPartitaActivity.class);
                i.putExtra("winnerPlayer", winner);
                i.putExtra("isGiocoConMacchina", giocoTris.getIsControMacchina());

                startActivityForResult(i, ESITO_PARTITA_ACTIVITY);
                break;
            case Notifica.PAREGGIO:
                // nuovo intent con risposta per esito positivo a rivincita
                Intent pareggioIntent = new Intent(this, EsitoPartitaActivity.class);
                // start activity for result
                startActivityForResult(pareggioIntent, ESITO_PARTITA_ACTIVITY);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ESITO_PARTITA_ACTIVITY) {
            //---if the result is OK---
            if (resultCode == RESULT_OK) {
                //riavvia gioco tris
                giocoTris.startGioco();
            } else finish(); //se esito negativo chiudi
        }
    }

}
