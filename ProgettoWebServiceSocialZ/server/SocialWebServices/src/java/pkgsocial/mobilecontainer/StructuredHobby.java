/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsocial.mobilecontainer;

/**
 *
 * @author davide
 */
public class StructuredHobby {
    private int idHobby;
    private String nome;
    private String dataInizio;

    public StructuredHobby(int idHobby, String nome, String dataInizio) {
        this.idHobby = idHobby;
        this.nome = nome;
        this.dataInizio = dataInizio;
    }

    public int getIdHobby() {
        return idHobby;
    }

    public String getNome() {
        return nome;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setIdHobby(int idHobby) {
        this.idHobby = idHobby;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    @Override
    public String toString() {
        return "StructuredHobby{" + "idHobby=" + idHobby + ", nome=" + nome + ", dataInizio=" + dataInizio + '}';
    }
    
}
