package pkgsocial.util.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Davide
 */
public class Util {

    //percorso per le immagini -> recuperate dal ImageServlet 
    public final static String PATH_IMG_PROGETTO = "/home/davide/Scrivania/ProgettoSocial/imgSocial/";

    public final static String PATH_IMG = "img/";
    public final static String PATH_IMG_PROFILI_UTENTE = "userProfileImg/";
    public final static String PATH_NO_IMG_DEFAULT = "default/";
    public final static String NOME_IMG_PROFILO_MANCANTE = "noimage.jpg";

    public static final String NOME_IMG_GRUPPO_MANCANTE = "noimagegroup.jpg";
    public static final String PATH_IMG_GRUPPO = "groupImg/";
    public static final String PATH_NO_IMG_GRUPPO_DEFAULT = "default/";

    public final static String MSG_DATO_PERSONALE_NON_VISIBILE = "Dato personale non visibile";

    final static String SEPARATORE_DATA = "/";
    final static String SEPARATORE_TEMPO = ":";
    final static String PATTERN_DATA = "yyyy" + SEPARATORE_DATA + "MM" + SEPARATORE_DATA + "dd";
    final static String PATTERN_DATAORA = PATTERN_DATA + " HH" + SEPARATORE_TEMPO + "mm" + SEPARATORE_TEMPO + "ss";
    final static DateFormat dfData = new SimpleDateFormat(PATTERN_DATA);
    final static DateFormat dfDataOra = new SimpleDateFormat(PATTERN_DATAORA);

    final static Encoder base64Encoder = Base64.getEncoder();

    public static String parseDateTimeToString(Date date) {
        return dfDataOra.format(date);
    }

    public static String parseDateToString(Date date) {
        return dfData.format(date);
    }

    public static Date parseStringToDate(String stringDate) {
        Date parsedDate;
        //dfData.setLenient(false);
        try {
            parsedDate = dfData.parse(stringDate);
        } catch (ParseException ex) {
            parsedDate = new Date();
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedDate;
    }

    public static Date parseStringToDateTime(String stringDate) {
        Date parsedDate;
        //dfDataOra.setLenient(false);
        try {
            parsedDate = dfDataOra.parse(stringDate);
        } catch (ParseException ex) {
            parsedDate = new Date();
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parsedDate;
    }

    public static Date parseStringToDate(String anno, String mese, String giorno) {
        String aaaa = anno;
        String mm = mese;
        String gg = giorno;
        if (aaaa.length() < 4) {//così aggiungo i possibili zeri mancanti
            aaaa = "0001".substring(4 - aaaa.length()) + aaaa;
        }
        if (mm.length() < 2) {
            mm = "01".substring(0, 2 - mm.length()) + mm;
        }
        if (gg.length() < 2) {
            gg = "01".substring(0, 2 - gg.length()) + gg;
        }

        String data = aaaa + SEPARATORE_DATA + mm + SEPARATORE_DATA + gg;
        System.out.println("data sistemata-> " + data);
        return parseStringToDate(data);
    }

    public static String getPercorsoImgProfiloUtente(String imgProfiloUtente) {
        String percorsoImg = PATH_IMG;
        if (imgProfiloUtente.equals(NOME_IMG_PROFILO_MANCANTE)) {
            percorsoImg += PATH_NO_IMG_DEFAULT;
        } else {
            percorsoImg += PATH_IMG_PROFILI_UTENTE;
        }
        percorsoImg += imgProfiloUtente;
        return percorsoImg;
    }

    public static String getPercorsoImgGruppo(String imgGruppo) {
        String percorsoImg = PATH_IMG;
        if (imgGruppo.equals(NOME_IMG_GRUPPO_MANCANTE)) {
            percorsoImg += PATH_NO_IMG_GRUPPO_DEFAULT;
        } else {
            percorsoImg += PATH_IMG_GRUPPO;
        }
        percorsoImg += imgGruppo;
        return percorsoImg;
    }

    public static String getParsedToBase64(String img) {
        String base64File = "";
        try {
            File imgFile = new File(PATH_IMG_PROGETTO + img);
            byte[] bytesArray = new byte[(int) imgFile.length()];
            FileInputStream fis = new FileInputStream(imgFile);
            fis.read(bytesArray); //read file into bytes[]
            fis.close();
            String estensione = img.substring(img.lastIndexOf(".") + 1);
            String header = "data:image/" + estensione + ";base64,";
            base64File = header + base64Encoder.encodeToString(bytesArray);
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return base64File;
    }

}
