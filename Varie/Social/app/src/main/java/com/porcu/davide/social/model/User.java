package com.porcu.davide.social.model;

import android.graphics.Bitmap;

import com.porcu.davide.social.util.Util;

/**
 * Created by davide on 22/04/18.
 */

public class User {

    private int idAccount;
    private String nome;
    private String cognome;
    private String email;
    private String username;
    private String imgProfilo;//usato solo per GSON
    private Bitmap btmpImgProfilo;

    public User(int idAccount, String nome, String cognome, String email, String username, String base64Img) {
        this.idAccount = idAccount;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.username = username;
        this.btmpImgProfilo = Util.getBitmapFromBase64(base64Img);
    }

    public int getIdAccount() {
        return idAccount;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public Bitmap getBtmpImgProfilo() {
        if(btmpImgProfilo==null){
            btmpImgProfilo=Util.getBitmapFromBase64(imgProfilo);
            imgProfilo="data:image/png;base64,";//non si sa mai, magari qualcosa va storto
        }
        return btmpImgProfilo;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBtmpImgProfilo(Bitmap btmpImgProfilo) {
        this.btmpImgProfilo = btmpImgProfilo;
    }

    public void setBtmpImgProfilo(String imgProfilo) {
        this.btmpImgProfilo = Util.getBitmapFromBase64(imgProfilo);
    }


    @Override
    public String toString() {
        return "BasicUserInfo{" + "idAccount=" + idAccount + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", username=" + username + ", imgProfilo=" + imgProfilo + '}';
    }

}
