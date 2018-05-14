package com.porcu.davide.socialapp.model;

import android.graphics.Bitmap;

import com.porcu.davide.socialapp.util.Util;

/**
 * @author Davide
 */
public class StructuredSearchedUser {

    private String nomeCognome;
    private String username;
    private String base64Img;//usato solo per GSON
    private Bitmap btmpImgProfilo;

    public StructuredSearchedUser(String nomeCognome, String username, String base64Img) {
        this.nomeCognome = nomeCognome;
        this.username = username;
        this.base64Img = base64Img;
        this.btmpImgProfilo = Util.getBitmapFromBase64(base64Img);
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public String getUsername() {
        return username;
    }

    public Bitmap getBtmpImgProfilo() {
        if (btmpImgProfilo == null) {
            btmpImgProfilo = Util.getBitmapFromBase64(base64Img);
            base64Img = "data:image/png;base64,";//non si sa mai, magari qualcosa va storto
        }
        return btmpImgProfilo;
    }


    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
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
        return "StructuredSearchedUser{" +
                "nomeCognome='" + nomeCognome + '\'' +
                ", username='" + username + '\'' +
                ", base64Img='" + base64Img + '\'' +
                ", btmpImgProfilo=" + btmpImgProfilo +
                '}';
    }
}
