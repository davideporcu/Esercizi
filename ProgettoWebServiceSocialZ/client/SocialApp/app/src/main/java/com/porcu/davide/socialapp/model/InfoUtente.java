/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.porcu.davide.socialapp.model;

/**
 *
 * @author davide
 */
public class InfoUtente {
    
    private String sesso;
    private String dataDiNascita;
    private String telefono;
    private String indirizzo;
    private String email;

    public InfoUtente() {
        this.sesso = "";
        this.dataDiNascita = "";
        this.telefono = "";
        this.indirizzo = "";
        this.email = "";
    }

    public InfoUtente(String sesso, String dataDiNascita, String telefono, String indirizzo, String email) {
        this.sesso = sesso;
        this.dataDiNascita = dataDiNascita;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.email = email;
    }

    public String getSesso() {
        return sesso;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "InfoUtente{" + "sesso=" + sesso + ", dataDiNascita=" + dataDiNascita + ", telefono=" + telefono + ", indirizzo=" + indirizzo + ", email=" + email + '}';
    }
}
