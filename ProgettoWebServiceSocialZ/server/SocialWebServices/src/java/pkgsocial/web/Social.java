package pkgsocial.web;

import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import pkgsocial.entita.Account;
import pkgsocial.entita.Hobby;
import pkgsocial.entita.PermessiVisibilita;
import pkgsocial.entita.Persona;
import pkgsocial.mobilecontainer.BasicUserInfo;
import pkgsocial.mobilecontainer.InfoUtente;
import pkgsocial.mobilecontainer.StructuredHobby;
import pkgsocial.mobilecontainer.StructuredSearchedGroup;
import pkgsocial.mobilecontainer.StructuredSearchedUser;
import static pkgsocial.util.util.Util.MSG_DATO_PERSONALE_NON_VISIBILE;
import static pkgsocial.util.util.Util.parseDateToString;
import pkgsocial.util.utildb.UtilDB;
import static pkgsocial.util.utildb.UtilDB.gsonizer;

/**
 * @author Davide
 */
@WebService(serviceName = "Social")
public class Social {

    @WebMethod(operationName = "getListaHobby")
    public String getListaHobby() {
        ArrayList<Hobby> lista = UtilDB.getListaHobby();
        System.out.println("SERVICE -> getListaHobby");
        return gsonizer.toJson(lista);
    }

    @WebMethod(operationName = "getListaPraticantiDiHobby")
    public String getListaPraticantiDiHobby(@WebParam(name = "idHobby") int idHobby) {
        ArrayList<BasicUserInfo> lista = UtilDB.getPraticantiDiHobby(idHobby);
        System.out.println("SERVICE -> getListaPraticantiDiHobby");
        return gsonizer.toJson(lista);
    }

    @WebMethod(operationName = "getListaEmailPraticantiDiHobby")
    public String getListaEmailPraticantiDiHobby(@WebParam(name = "idHobby") int idHobby) {
        ArrayList<String> listaEmail = UtilDB.getListaEmailPraticantiDiHobby(idHobby);
        System.out.println("SERVICE -> getListaEmailPraticantiDiHobby");
        return gsonizer.toJson(listaEmail);
    }

    @WebMethod(operationName = "getListaOfStructuredSearchedUser")
    public String getListaOfStructuredSearchedUser(@WebParam(name = "query") String query) {
        ArrayList<StructuredSearchedUser> listaCercati = UtilDB.getListOfStructuredSearchedUser(query);
        System.out.println("SERVICE -> getListaOfStructuredSearchedUser");
        return gsonizer.toJson(listaCercati);
    }

    @WebMethod(operationName = "getListaOfStructuredSearchedGroup")
    public String getListaOfStructuredSearchedGroup(@WebParam(name = "query") String query) {
        ArrayList<StructuredSearchedGroup> listaCercati = UtilDB.getListOfStructuredSearchedGroup(query);
        System.out.println("SERVICE -> getListaOfStructuredSearchedGroup");
        return gsonizer.toJson(listaCercati);
    }

    @WebMethod(operationName = "getInfoDiUtente")
    public String getInfoDiUtente(@WebParam(name = "username") String username) {
        InfoUtente info = UtilDB.getInfoUtente(username);
        System.out.println("SERVICE -> getInfoDiUtente");
        return gsonizer.toJson(info);
    }

    @WebMethod(operationName = "getListaStructuredHobbyPraticatiDaUtente")
    public String getListaStructuredHobbyPraticatiDaUtente(@WebParam(name = "username") String username) {
        Account account = UtilDB.getAccountByUsername(username);
        ArrayList<StructuredHobby> lista = UtilDB.getListaStructuredHobbyPraticatiDaAccount(account.getIdAccount());
        System.out.println("SERVICE -> getListaStructuredHobbyPraticatiDaUtente");
        return gsonizer.toJson(lista);
    }
    
    @WebMethod(operationName = "getImgProfiloUtente")
    public String getImgProfiloUtente(@WebParam(name = "username") String username) {
        String imgBase64=UtilDB.getImgProfiloUtente(username);
        System.out.println("SERVICE -> getImgProfiloUtente");
        return imgBase64;
    }

}
