package pkgsocial.entita;

import java.io.Serializable;

/**
 * @author Davide
 */
public class Account implements Serializable {

    private int idAccount;
    private String username;
    private String password;
    private String email;
    private String telefono;
    private Persona persona;
    private boolean isAdminSocial;
    private PermessiVisibilita permessiVisibilita;

    public Account() {
        this.idAccount = -1;
        this.username = "";
        this.password = "";
        this.email = "";
        this.telefono = "";
        this.persona = new Persona();
        this.isAdminSocial = false;
        this.permessiVisibilita = new PermessiVisibilita();
    }

    public Account(String username, String password, String email, String telefono) {
        this.idAccount = -1;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.persona = new Persona();
        this.isAdminSocial = false;
        this.permessiVisibilita = new PermessiVisibilita();
    }

    public Account(String username, String password, String email, Persona persona) {
        this.idAccount = -1;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = "";
        this.persona = persona;
        this.isAdminSocial = false;
        this.permessiVisibilita = new PermessiVisibilita();
    }

    public Account(int idAccount, String username, String password, String email, String telefono, Persona persona) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.persona = persona;
        this.isAdminSocial = false;
        this.permessiVisibilita = new PermessiVisibilita();
    }

    public Account(int idAccount, String username, String password, String email, String telefono, Persona persona, boolean isAdminSocial) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.persona = persona;
        this.isAdminSocial = isAdminSocial;
        this.permessiVisibilita = new PermessiVisibilita();
    }

    public Account(int idAccount, String username, String password, String email, String telefono, Persona persona, boolean isAdminSocial, PermessiVisibilita permessiVisibilita) {
        this.idAccount = idAccount;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.persona = persona;
        this.isAdminSocial = isAdminSocial;
        this.permessiVisibilita = permessiVisibilita;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Persona getPersona() {
        return persona;
    }

    public boolean getIsAdminSocial() {
        return isAdminSocial;
    }

    public PermessiVisibilita getPermessiVisibilita() {
        return permessiVisibilita;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setIsAdminSocial(boolean isAdminSocial) {
        this.isAdminSocial = isAdminSocial;
    }

    public void setPermessiVisibilita(PermessiVisibilita permessiVisibilita) {
        this.permessiVisibilita = permessiVisibilita;
    }

    @Override
    public String toString() {
        return "Account{" + "idAccount=" + idAccount + ", username=" + username + ", password=" + password + ", email=" + email + ", telefono=" + telefono + ", persona=" + persona + ", isAdminSocial=" + isAdminSocial + ", permessiVisibilita=" + permessiVisibilita + '}';
    }

}
