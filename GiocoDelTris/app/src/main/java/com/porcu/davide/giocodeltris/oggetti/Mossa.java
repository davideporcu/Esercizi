package com.porcu.davide.giocodeltris.oggetti;

/**
 * Created by Davide on 06/02/2018.
 */

public class Mossa {

    private int riga;
    private int colonna;

    public Mossa(int riga, int colonna) {
        this.riga = riga;
        this.colonna = colonna;
    }

    public int getRiga() {
        return riga;
    }

    public int getColonna() {
        return colonna;
    }

    public void setRiga(int riga) {
        this.riga = riga;
    }

    public void setColonna(int colonna) {
        this.colonna = colonna;
    }

    @Override
    public String toString() {
        return "Mossa{" +
                "riga=" + riga +
                ", colonna=" + colonna +
                '}';
    }
}
