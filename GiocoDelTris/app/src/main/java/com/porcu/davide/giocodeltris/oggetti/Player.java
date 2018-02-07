package com.porcu.davide.giocodeltris.oggetti;

import java.io.Serializable;

/**
 * Created by Davide on 06/02/2018.
 */

public class Player implements Serializable {

    private String nome;
    private Tris.StatoCasellaTris simbolo;
    private int puntiVittorie;
    private boolean isMacchina;

    public Player() {
        nome = "";
        simbolo = Tris.StatoCasellaTris.VUOTO;
        puntiVittorie = 0;
        isMacchina=false;
    }

    public Player(String nome) {
        this.nome = nome;
        this.simbolo = Tris.StatoCasellaTris.VUOTO;
        this.puntiVittorie = 0;
        this.isMacchina=false;
    }

    public Player(String nome,boolean isMacchina) {
        this.nome = nome;
        this.simbolo = Tris.StatoCasellaTris.VUOTO;
        this.puntiVittorie = 0;
        this.isMacchina=isMacchina;
    }

    public Player(String nome, Tris.StatoCasellaTris simbolo) {
        this.nome = nome;
        this.simbolo = simbolo;
        this.puntiVittorie = 0;
        this.isMacchina=false;
    }


    public String getNome() {
        return nome;
    }

    public Tris.StatoCasellaTris getSimbolo() {
        return simbolo;
    }

    public int getPuntiVittorie() {
        return puntiVittorie;
    }

    public boolean getIsMacchina() {
        return isMacchina;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSimbolo(Tris.StatoCasellaTris simbolo) {
        this.simbolo = simbolo;
    }

    public void setPuntiVittorie(int puntiVittorie) {
        this.puntiVittorie = puntiVittorie;
    }

    public void addPuntoVittoria() {
        this.puntiVittorie++;
    }

    public void setIsMacchina(boolean isMacchina) {
        this.isMacchina = isMacchina;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nome='" + nome + '\'' +
                ", simbolo=" + simbolo +
                ", puntiVittorie=" + puntiVittorie +
                '}';
    }
}
