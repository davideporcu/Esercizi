package com.porcu.davide.socialapp.model;

import android.graphics.Bitmap;

import com.porcu.davide.socialapp.util.Util;

public class BasicUserInfo {

    private int idAccount;
    private String nome;
    private String cognome;
    private String username;
    private String base64Img;//usato solo per GSON
    private Bitmap btmpImgProfilo;

    public BasicUserInfo() {
        this.idAccount = -1;
        this.nome = "";
        this.cognome = "";
        this.username = "";
        this.btmpImgProfilo = null;
        this.base64Img="data:image/png;base64, ";
    }

    public BasicUserInfo(int idAccount, String nome, String cognome, String username, String base64Img) {
        this.idAccount = idAccount;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.base64Img="data:image/png;base64, ";
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

    public String getUsername() {
        return username;
    }

    public String getImgProfilo(){
        return base64Img;
    }

    public Bitmap getBtmpImgProfilo() {
        if(btmpImgProfilo==null){
            btmpImgProfilo=Util.getBitmapFromBase64(base64Img);
            base64Img="data:image/png;base64, ";//non si sa mai, magari qualcosa va storto
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBtmpImgProfilo(Bitmap btmpImgProfilo) {
        this.btmpImgProfilo = btmpImgProfilo;
    }

    public void setBtmpImgProfilo(String base64Img) {
        this.btmpImgProfilo = Util.getBitmapFromBase64(base64Img);
    }

    @Override
    public String toString() {
        return "BasicUserInfo{" +
                "idAccount=" + idAccount +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", username='" + username + '\'' +
                ", base64Img='" + base64Img + '\'' +
                ", btmpImgProfilo=" + btmpImgProfilo +
                '}';
    }
}
