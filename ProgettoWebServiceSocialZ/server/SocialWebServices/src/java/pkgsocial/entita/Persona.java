/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsocial.entita;

import java.io.Serializable;
import java.util.Date;
import static pkgsocial.util.util.Util.NOME_IMG_PROFILO_MANCANTE;

/**
 *
 * @author Davide
 */
public class Persona implements Serializable {

    /**
     * @author Davide
     */
    private int idPersona;
    private String nome;
    private String cognome;
    private String indirizzo;
    private Date dataDiNascita; //nel DB è una stringa 
    private char sesso;//M, F , quello che è

    private String imgProfilo;

    public Persona() {
        this.idPersona = -1;
        this.nome = "";
        this.cognome = "";
        this.indirizzo = "";
        this.dataDiNascita = new Date();
        this.sesso = 'M';
        this.imgProfilo = NOME_IMG_PROFILO_MANCANTE;
    }

    public Persona(int idPersona, String nome, String cognome, String imgProfilo) {//info di base -> visibili a tutti
        this.idPersona = idPersona;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = "";
        this.dataDiNascita = new Date();
        this.sesso = ' ';
        this.imgProfilo = imgProfilo;
    }

    public Persona(String nome, String cognome, String indirizzo, Date dataDiNascita, char sesso) {
        this.idPersona = -1;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.sesso = sesso;
        this.imgProfilo = NOME_IMG_PROFILO_MANCANTE;
    }

    public Persona(String nome, String cognome, String indirizzo, Date dataDiNascita, char sesso, String imgProfilo) {
        this.idPersona = -1;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.sesso = sesso;
        this.imgProfilo = imgProfilo;
    }

    public Persona(int idPersona, String nome, String cognome, String indirizzo, Date dataDiNascita, char sesso, String imgProfilo) {
        this.idPersona = idPersona;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.sesso = sesso;
        this.imgProfilo = imgProfilo;
    }

    public Persona(int idPersona, String nome, String cognome, String indirizzo, Date dataDiNascita, char sesso) {
        this.idPersona = idPersona;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.dataDiNascita = dataDiNascita;
        this.sesso = sesso;
        this.imgProfilo = NOME_IMG_PROFILO_MANCANTE;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    public char getSesso() {
        return sesso;
    }

    public String getImgProfilo() {
        return imgProfilo;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public void setImgProfilo(String imgProfilo) {
        this.imgProfilo = imgProfilo;
    }

    @Override
    public String toString() {
        return "Persona{" + "idPersona=" + idPersona + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo + ", dataDiNascita=" + dataDiNascita + ", sesso=" + sesso + ", imgProfilo=" + imgProfilo + '}';
    }

}
