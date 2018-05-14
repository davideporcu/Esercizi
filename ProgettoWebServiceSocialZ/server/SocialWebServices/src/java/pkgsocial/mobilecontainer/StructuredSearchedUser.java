package pkgsocial.mobilecontainer;

import static pkgsocial.util.util.Util.NOME_IMG_PROFILO_MANCANTE;
import static pkgsocial.util.util.Util.PATH_IMG;
import static pkgsocial.util.util.Util.PATH_NO_IMG_DEFAULT;

/**
 * @author Davide
 */
public class StructuredSearchedUser {

    private String nomeCognome;
    private String username;
    private String base64Img;

    public StructuredSearchedUser() {
        this.nomeCognome = "";
        this.username = "";
        this.base64Img = PATH_IMG + PATH_NO_IMG_DEFAULT + NOME_IMG_PROFILO_MANCANTE;
    }

    public StructuredSearchedUser(String nomeCognome, String username, String imgProfilo) {
        this.nomeCognome = nomeCognome;
        this.username = username;
        this.base64Img = imgProfilo;
    }

    public String getNomeCognome() {
        return nomeCognome;
    }

    public String getUsername() {
        return username;
    }

    public String getBase64Img() {
        return base64Img;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBase64Img(String imgProfilo) {
        this.base64Img = imgProfilo;
    }

    @Override
    public String toString() {
        return "StructuredSearchedUser{" + "nomeCognome=" + nomeCognome + ", username=" + username + ", base64Img=" + base64Img + '}';
    }

}
