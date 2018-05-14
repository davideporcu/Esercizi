package pkgsocial.entita;

/**
 * @author Davide
 */
public class PermessiVisibilita {

    private int idPermesso;
    //private HashMap<String, Boolean> permessi;

    private boolean isVisibileTelefono;
    private boolean isVisibileEmail;
    private boolean isVisibileSesso;
    private boolean isVisibileDataDiNascita;
    private boolean isVisibileListaHobby;
    private boolean isVisibileIndirizzo;

    public PermessiVisibilita() {
        this.idPermesso = -1;
        this.isVisibileTelefono = false;
        this.isVisibileEmail = false;
        this.isVisibileSesso = false;
        this.isVisibileDataDiNascita = false;
        this.isVisibileListaHobby = false;
        this.isVisibileIndirizzo = false;
    }

    public PermessiVisibilita(int idPermesso, boolean isVisibileTelefono, boolean isVisibileEmail, boolean isVisibileSesso, boolean isVisibileDataDiNascita, boolean isVisibileListaHobby, boolean isVisibileIndirizzo) {
        this.idPermesso = idPermesso;
        this.isVisibileTelefono = isVisibileTelefono;
        this.isVisibileEmail = isVisibileEmail;
        this.isVisibileSesso = isVisibileSesso;
        this.isVisibileDataDiNascita = isVisibileDataDiNascita;
        this.isVisibileListaHobby = isVisibileListaHobby;
        this.isVisibileIndirizzo = isVisibileIndirizzo;
    }

    public int getIdPermesso() {
        return idPermesso;
    }

    public boolean getIsVisibileTelefono() {
        return isVisibileTelefono;
    }

    public boolean getIsVisibileEmail() {
        return isVisibileEmail;
    }

    public boolean getIsVisibileSesso() {
        return isVisibileSesso;
    }

    public boolean getIsVisibileDataDiNascita() {
        return isVisibileDataDiNascita;
    }

    public boolean getIsVisibileListaHobby() {
        return isVisibileListaHobby;
    }

    public boolean getIsVisibileIndirizzo() {
        return isVisibileIndirizzo;
    }

    public void setIdPermesso(int idPermesso) {
        this.idPermesso = idPermesso;
    }

    public void setIsVisibileTelefono(boolean isVisibileTelefono) {
        this.isVisibileTelefono = isVisibileTelefono;
    }

    public void setIsVisibileEmail(boolean isVisibileEmail) {
        this.isVisibileEmail = isVisibileEmail;
    }

    public void setIsVisibileSesso(boolean isVisibileSesso) {
        this.isVisibileSesso = isVisibileSesso;
    }

    public void setIsVisibileDataDiNascita(boolean isVisibileDataDiNascita) {
        this.isVisibileDataDiNascita = isVisibileDataDiNascita;
    }

    public void setIsVisibileListaHobby(boolean isVisibileListaHobby) {
        this.isVisibileListaHobby = isVisibileListaHobby;
    }

    public void setIsVisibileIndirizzo(boolean isVisibileIndirizzo) {
        this.isVisibileIndirizzo = isVisibileIndirizzo;
    }

    @Override
    public String toString() {
        return "PermessiVisibilita{" + "idPermesso=" + idPermesso + ", isVisibileTelefono=" + isVisibileTelefono + ", isVisibileEmail=" + isVisibileEmail + ", isVisibileSesso=" + isVisibileSesso + ", isVisibileDataDiNascita=" + isVisibileDataDiNascita + ", isVisibileListaHobby=" + isVisibileListaHobby + ", isVisibileIndirizzo=" + isVisibileIndirizzo + '}';
    }

}
