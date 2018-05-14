package pkgsocial.mobilecontainer;

import static pkgsocial.util.util.Util.NOME_IMG_GRUPPO_MANCANTE;
import static pkgsocial.util.util.Util.PATH_IMG;
import static pkgsocial.util.util.Util.PATH_NO_IMG_GRUPPO_DEFAULT;

/**
 * @author Davide
 */
public class StructuredSearchedGroup {

    private int idGruppo;
    private String nomeGruppo;
    private String base64Img;

    public StructuredSearchedGroup(int idGruppo, String nomeGruppo, String imgGruppo) {
        this.idGruppo = idGruppo;
        this.nomeGruppo = nomeGruppo;
        this.base64Img = imgGruppo;
    }

    public StructuredSearchedGroup() {
        this.idGruppo = -1;
        this.nomeGruppo = "";
        this.base64Img = PATH_IMG + PATH_NO_IMG_GRUPPO_DEFAULT + NOME_IMG_GRUPPO_MANCANTE;
    }

    public int getIdGruppo() {
        return idGruppo;
    }

    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public String getBase64Img() {
        return base64Img;
    }

    public void setIdGruppo(int idGruppo) {
        this.idGruppo = idGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public void setBase64Img(String imgGruppo) {
        this.base64Img = imgGruppo;
    }

    @Override
    public String toString() {
        return "StructuredSearchedGroup{" + "idGruppo=" + idGruppo + ", nomeGruppo=" + nomeGruppo + ", base64Img=" + base64Img + '}';
    }

}
