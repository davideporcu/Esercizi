package pkgsocial.entita;

/**
 * @author Davide
 */
public class Hobby {

    private int idHobby;
    private String nomeHobby;

    public Hobby() {
    }

    public Hobby(int idHobby, String nomeHobby) {
        this.idHobby = idHobby;
        this.nomeHobby = nomeHobby;
    }

    public Hobby(String nomeHobby) {
        this.idHobby = -1;
        this.nomeHobby = nomeHobby;
    }

    public int getIdHobby() {
        return idHobby;
    }

    public String getNomeHobby() {
        return nomeHobby;
    }

    public void setIdHobby(int idHobby) {
        this.idHobby = idHobby;
    }

    public void setNomeHobby(String nomeHobby) {
        this.nomeHobby = nomeHobby;
    }

    @Override
    public String toString() {
        return "Hobby{" + "idHobby=" + idHobby + ", nomeHobby=" + nomeHobby + '}';
    }

}
