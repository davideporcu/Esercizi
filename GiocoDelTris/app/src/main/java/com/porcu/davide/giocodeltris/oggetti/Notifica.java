package com.porcu.davide.giocodeltris.oggetti;

import java.util.Arrays;
import java.util.List;

/**
 * Created by davide on 07/02/18.
 */

public class Notifica {

    public static final int DEBUGMESSAGE=-2,IGNOREME=-1,UPDATESTART=0,UPDATEMOSSA=1,UPDATEFORWINNER=2,PAREGGIO=3;

    private int tipoNotifica;
    private Object[] contenuto;

    public Notifica(){
        tipoNotifica=IGNOREME;
        contenuto=null;
    }

    public Notifica(int tipoNotifica, Object[]contenuto){
        this.tipoNotifica=tipoNotifica;
        this.contenuto=contenuto;
    }

    public int getTipoNotifica(){
        return tipoNotifica;
    }

    public Object[] getContenuto(){
        return contenuto;
    }

    public void setTipoNotifica(int tipoNotifica) {
        this.tipoNotifica = tipoNotifica;
    }

    public void setContenuto(Object[] contenuto) {
        this.contenuto = contenuto;
    }

    @Override
    public String toString() {
        return "Notifica{" +
                "tipoNotifica=" + tipoNotifica +
                ", contenuto=" + Arrays.toString(contenuto) +
                '}';
    }
}
