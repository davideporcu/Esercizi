package com.porcu.davide.giocodeltris.oggetti;

import java.util.Observable;
import java.util.Random;

/**
 * Created by Davide on 06/02/2018.
 */

public class Tris extends Observable {

    public enum StatoCasellaTris {X, O, VUOTO}

    private boolean isGiocoPartito;
    private boolean isTurnoPrimoGiocatore;
    private boolean isControMacchina;
    private int casellePiene;

    private Player player1;//true
    private Player player2;//false
    private StatoCasellaTris[][] scacchiera;
    private Random random;

    public Tris() {
        random = new Random();
        scacchiera = new StatoCasellaTris[3][3];
        isTurnoPrimoGiocatore = random.nextBoolean();
        //scelta simboli player da fare
        isGiocoPartito = false;
        isControMacchina = false;
    }

    public Tris(Player player1, Player player2) {
        random = new Random();
        scacchiera = new StatoCasellaTris[3][3];
        this.player1 = player1;
        this.player2 = player2;
        isTurnoPrimoGiocatore = random.nextBoolean();
        isGiocoPartito = false;
        isControMacchina = player2.getIsMacchina();

        //scelta simboli player da fare ---> magari in reset scacchiera
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getGiocatoreCorrente() {
        return isTurnoPrimoGiocatore ? player1 : player2;
    }

    public boolean isGiocoPartito() {
        return isGiocoPartito;
    }

    public boolean isTurnoPrimoGiocatore() {
        return isTurnoPrimoGiocatore;
    }

    public void setPlayer1(Player player) {
        this.player1 = player;
    }

    public void setPlayer2(Player player) {
        this.player2 = player;
    }

    public boolean getIsControMacchina() {
        return isControMacchina;
    }

    private void resetScacchiera() {
        isGiocoPartito = false;
        casellePiene = 0;
        boolean simbolo = random.nextBoolean();// meglio se si inverte dopo ogni partita se si ripete
        player1.setSimbolo(simbolo ? StatoCasellaTris.X : StatoCasellaTris.O);//generazione casuale
        player2.setSimbolo(!simbolo ? StatoCasellaTris.X : StatoCasellaTris.O);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                scacchiera[i][j] = StatoCasellaTris.VUOTO;
            }
        }
        Object[] contenuto = new Object[2];
        contenuto[0] = player1;
        contenuto[1] = player2;

        setChanged();
        notifyObservers(new Notifica(Notifica.UPDATESTART, contenuto));
    }

    public void startGioco() {
        //scegli a caso primo giocatore
        resetScacchiera();
        isTurnoPrimoGiocatore = !isTurnoPrimoGiocatore;
        //setChanged();
        //notifyObservers(new Notifica(Notifica.DEBUGMESSAGE, new Object[]{"Start Game"}));
        if (!isTurnoPrimoGiocatore && isControMacchina) {//se macchina inizia per prima!
            Mossa mossa = prossimaMossa(player2.getSimbolo());
            scacchiera[mossa.getRiga()][mossa.getColonna()] = player2.getSimbolo();
            Object[] contenuto = new Object[3];
            contenuto[0] = mossa;
            contenuto[1] = player2;//giocatore che ha fatto la mossa
            contenuto[2] = player1;//giocatore successivo
            Notifica notifica = new Notifica(Notifica.UPDATEMOSSA, contenuto);//notifico la vista
            setChanged();
            notifyObservers(notifica);
            isTurnoPrimoGiocatore = !isTurnoPrimoGiocatore;
        }
        isGiocoPartito = true;
    }

    public void doMossa(Mossa mossa) {
        int riga = mossa.getRiga();
        int colonna = mossa.getColonna();
        if (isGiocoPartito && scacchiera[riga][colonna] == StatoCasellaTris.VUOTO) {
            scacchiera[riga][colonna] = getGiocatoreCorrente().getSimbolo();
            casellePiene++;
            Notifica notifica;
            if (haVinto(getGiocatoreCorrente().getSimbolo())) {
                isGiocoPartito = false;//finito il gioco
                //getPlayer() -> vincitore
                Player vincitore = getGiocatoreCorrente();
                vincitore.addPuntoVittoria();
                Object[] contenuto = new Object[2];
                contenuto[0] = mossa;
                contenuto[1] = vincitore;//vincitore
                notifica = new Notifica(Notifica.UPDATEFORWINNER, contenuto);
                //notifyObservers(notifica);//notify mossa, vincitore e posizioni della vincita
            } else {
                Player giocatoreDellaMossa = getGiocatoreCorrente();
                isTurnoPrimoGiocatore = !isTurnoPrimoGiocatore;//tocca al altro player
                Object[] contenuto = new Object[3];
                contenuto[0] = mossa;
                contenuto[1] = giocatoreDellaMossa;//giocatore che ha fatto la mossa
                contenuto[2] = getGiocatoreCorrente();//giocatore successivo
                notifica = new Notifica(Notifica.UPDATEMOSSA, contenuto);//notifico la vista
                //notifyObservers(notifica);//notify mossa , giocatore successivo (solo stringa con nome e simbolo?)
            }
            setChanged();
            notifyObservers(notifica);
            controllaPareggio();

            if (isGiocoPartito && isControMacchina) {//turno della macchina dopo quello dell'umano
                Mossa mossaMacchina = prossimaMossa(player2.getSimbolo());
                System.out.println(mossaMacchina);
                scacchiera[mossaMacchina.getRiga()][mossaMacchina.getColonna()] = player2.getSimbolo();
                casellePiene++;
                Notifica notificaMacchina;
                if (haVinto(player2.getSimbolo())) {
                    isGiocoPartito = false;//finito il gioco
                    Player vincitore = player2;
                    vincitore.addPuntoVittoria();
                    Object[] contenutoVittoria = new Object[2];
                    contenutoVittoria[0] = mossaMacchina;
                    contenutoVittoria[1] = vincitore;//vincitore
                    setChanged();
                    notificaMacchina = new Notifica(Notifica.UPDATEFORWINNER, contenutoVittoria);
                } else {
                    Object[] contenuto = new Object[3];
                    contenuto[0] = mossaMacchina;
                    contenuto[1] = player2;//giocatore che ha fatto la mossa
                    contenuto[2] = player1;//giocatore successivo
                    notificaMacchina = new Notifica(Notifica.UPDATEMOSSA, contenuto);//notifico la vista
                    isTurnoPrimoGiocatore = !isTurnoPrimoGiocatore;
                }
                setChanged();
                notifyObservers(notificaMacchina);
                controllaPareggio();
            }

        }

    }

    private void controllaPareggio() {
        if (isGiocoPartito && casellePiene == 9) {
            isGiocoPartito = false;
            setChanged();
            notifyObservers(new Notifica(Notifica.PAREGGIO, new Object[0]));
        }
    }

    private boolean haVinto(StatoCasellaTris giocatore) { //verifica se giocatore indicato ha vinto
        boolean vinto = false;
        int conta, conta2 = 0;
        for (int i = 0; i < 3 && !vinto; i++) {
            int j = 0;
            while (j < 3 && scacchiera[i][j] == giocatore) {//controllo righe
                j++;
            }
            vinto = j == 3;
            j = 0;
            while (j < 3 && scacchiera[j][i] == giocatore && !vinto) {//controllo colonne
                j++;
            }
            vinto = j == 3 || vinto;
        }
        conta = 0;
        for (int i = 0; !vinto && i < 3; i++) {        //controllo diagonali
            if (scacchiera[i][i] == giocatore) {               //diagonale sx -> dx
                conta++;
            }
            if (scacchiera[i][3 - i - 1] == giocatore) {           //diagonale dx -> sx
                conta2++;
            }
        }
        return vinto || conta == 3 || conta2 == 3;
    }

    public Mossa prossimaMossaVincente(StatoCasellaTris giocatore) {
        Mossa mossa = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (scacchiera[i][j] == StatoCasellaTris.VUOTO) {
                    scacchiera[i][j] = giocatore;
                    boolean vinto = haVinto(giocatore);
                    scacchiera[i][j] = StatoCasellaTris.VUOTO;
                    if (vinto) {
                        mossa = new Mossa(i, j);
                    }
                }
            }
        }
        return mossa;
    }

    public Mossa prossimaMossa(StatoCasellaTris giocatore) {
        Mossa mossa;
        if (casellePiene == 0) {
            mossa = new Mossa(random.nextInt(3), random.nextInt(3));
        } else if (scacchiera[1][1] == StatoCasellaTris.VUOTO) {//fortuna nella cella centrale...
            mossa = new Mossa(1, 1);
        } else {
            mossa = prossimaMossaVincente(giocatore);//cerca la mossa vincente...
            if (mossa == null) {//se non c'è allora..
                for (int i = 0; i < 3 && mossa == null; i++) {//sceglie la mossa che previene la vittoria dell'avversario
                    for (int j = 0; j < 3 && mossa == null; j++) {
                        if (scacchiera[i][j] == StatoCasellaTris.VUOTO) //controlla se la cella è vuota
                        {
                            scacchiera[i][j] = giocatore;
                            Mossa mossaVincenteAvversario = prossimaMossaVincente(giocatore == StatoCasellaTris.X ? StatoCasellaTris.O : StatoCasellaTris.X);
                            if (mossa == null && mossaVincenteAvversario != null) {
                                mossa = mossaVincenteAvversario;
                            }
                            scacchiera[i][j] = StatoCasellaTris.VUOTO;
                        }
                    }
                }
                //magari qui a random-> così da variare e non far rientrare in schemi precisi e limitare i danni

                if (mossa == null) {                                    //se non è disponibile nessuna mossa di difesa
                    for (int i = 0; i < 3 && mossa == null; i++) {      // scegli la prima cella vuota disponibile
                        for (int j = 0; j < 3 && mossa == null; j++) {
                            if (scacchiera[i][j] == StatoCasellaTris.VUOTO) {
                                mossa = new Mossa(i, j);
                            }
                        }
                    }
                }
            }
        }
        return mossa;
    }

}
