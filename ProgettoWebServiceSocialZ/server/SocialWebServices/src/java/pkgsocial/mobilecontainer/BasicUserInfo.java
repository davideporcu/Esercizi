package pkgsocial.mobilecontainer;

/**
 * @author Davide
 */
public class BasicUserInfo {

    private int idAccount;
    private String nome;
    private String cognome;
    private String username;
    private String base64Img;

    public BasicUserInfo(int idAccount, String nome, String cognome, String email, String username, String base64Img) {
        this.idAccount = idAccount;
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.base64Img = base64Img;
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

    public String getBase64Img() {
        return base64Img;
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

    public void setBase64Img(String base64Img) {
        this.base64Img = base64Img;
    }

    @Override
    public String toString() {
        return "BasicUserInfo{" + "idAccount=" + idAccount + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username + ", base64Img=" + base64Img + '}';
    }

}
