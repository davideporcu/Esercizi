package com.porcu.davide.socialapp.model;

import android.graphics.Bitmap;

import com.porcu.davide.socialapp.util.Util;

/**
 * @author Davide
 */
public class StructuredSearchedGroup {

    private int idGruppo;
    private String nomeGruppo;
    private String base64Img;
    private Bitmap btmpImgGruppo;

    public StructuredSearchedGroup(int idGruppo, String nomeGruppo, String base64Img) {
        this.idGruppo = idGruppo;
        this.nomeGruppo = nomeGruppo;
        this.base64Img = base64Img;
        this.btmpImgGruppo = Util.getBitmapFromBase64(base64Img);
    }

    public int getIdGruppo() {
        return idGruppo;
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public Bitmap getBtmpImgGruppo() {
        if(btmpImgGruppo==null){
            btmpImgGruppo=Util.getBitmapFromBase64(base64Img);
            base64Img="data:image/png;base64, ";//non si sa mai, magari qualcosa va storto
        }
        return btmpImgGruppo;
    }


    public void setIdGruppo(int idGruppo) {
        this.idGruppo = idGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public void setBtmpImgGruppo(Bitmap btmpImgGruppo) {
        this.btmpImgGruppo = btmpImgGruppo;
    }

    public void setBtmpImgGruppo(String base64Img) {
        this.btmpImgGruppo = Util.getBitmapFromBase64(base64Img);
    }


    @Override
    public String toString() {
        return "StructuredSearchedGroup{" + "idGruppo=" + idGruppo + ", nomeGruppo=" + nomeGruppo + ", btmpImgGruppo=" + btmpImgGruppo + '}';
    }

}
