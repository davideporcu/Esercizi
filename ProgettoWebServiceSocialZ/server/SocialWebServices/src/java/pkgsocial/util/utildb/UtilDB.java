/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgsocial.util.utildb;

import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkgsocial.entita.Account;
import pkgsocial.entita.Hobby;
import pkgsocial.entita.PermessiVisibilita;
import pkgsocial.entita.Persona;
import pkgsocial.mobilecontainer.BasicUserInfo;
import pkgsocial.mobilecontainer.InfoUtente;
import pkgsocial.mobilecontainer.StructuredHobby;
import pkgsocial.mobilecontainer.StructuredSearchedGroup;
import pkgsocial.mobilecontainer.StructuredSearchedUser;
import static pkgsocial.util.util.Util.getParsedToBase64;
import static pkgsocial.util.util.Util.getPercorsoImgGruppo;
import static pkgsocial.util.util.Util.getPercorsoImgProfiloUtente;
import static pkgsocial.util.util.Util.parseDateToString;
import static pkgsocial.util.util.Util.parseStringToDate;

/**
 *
 * @author Davide
 */
public class UtilDB {

    final static String NOME_DB = "SocialDB.db";
    final static String DRIVE_PATH = "/home/davide/Scrivania/ProgettoSocial/Social/";
    public static Gson gsonizer = new Gson();

    static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:" + DRIVE_PATH + NOME_DB);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        try {
            conn.close();
            conn = null;
        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Account getAccountByUsername(String username) {
        Account account = new Account();
        String sql = "SELECT * "
                + "FROM tblAccount "
                + "WHERE username LIKE ?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idAccount = rs.getInt("idAccount");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String telefono = rs.getString("telefono");
                boolean isAdminSocial = isAccountAdminSocial(idAccount);
                Persona datiPersona = new Persona();//la mette il controller usando il DaoPersona
                account = new Account(idAccount, username, password, email, telefono, datiPersona, isAdminSocial);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return account;
    }

    public static boolean isAccountAdminSocial(int idAccount) {
        boolean isAdmin = false;
        String sql = "SELECT * "
                + "FROM tblAdminSocial "
                + "WHERE idAccount=?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAccount);
            ResultSet rs = ps.executeQuery();
            isAdmin = rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAdmin;
    }

    public static ArrayList<String> getListaEmailPraticantiDiHobby(int idHobby) {
        ArrayList<String> listaEmail = new ArrayList<>();

        String sql = "SELECT tblAccount.email "
                + "FROM tbrHobbyAccount INNER JOIN tblAccount ON tblAccount.idAccount=tbrHobbyAccount.idAccount "
                + "WHERE tbrHobbyAccount.idHobby=? ";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idHobby);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                listaEmail.add(email);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEmail;
    }

    public static ArrayList<BasicUserInfo> getPraticantiDiHobby(int idHobby) {
        ArrayList<BasicUserInfo> listaEmail = new ArrayList<>();

        String sql = "SELECT tblAccount.idAccount, tblAccount.username, tblAccount.email, tblPersona.nome, tblPersona.cognome, tblPersona.imgProfilo "
                + "FROM (tbrHobbyAccount INNER JOIN tblAccount ON tblAccount.idAccount=tbrHobbyAccount.idAccount) INNER JOIN tblPersona ON tblAccount.idAccount=tblPersona.idAccount "
                + "WHERE tbrHobbyAccount.idHobby=? ";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idHobby);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idAccount = rs.getInt("idAccount");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String imgProfilo = rs.getString("imgProfilo");
                String imgProfiloBase64 = getParsedToBase64(getPercorsoImgProfiloUtente(imgProfilo));
                BasicUserInfo bui = new BasicUserInfo(idAccount, nome, cognome, email, username, imgProfiloBase64);
                listaEmail.add(bui);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEmail;
    }

    public static ArrayList<Hobby> getListaHobby() {
        ArrayList<Hobby> listaHobby = new ArrayList<>();
        String sql = "SELECT * "
                + "FROM tblHobby "
                + "ORDER BY nomeHobby ASC";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idHobby = rs.getInt("idHobby");
                String nomeHobby = rs.getString("nomeHobby");
                Hobby hobby = new Hobby(idHobby, nomeHobby);
                listaHobby.add(hobby);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaHobby;
    }

    public static ArrayList<StructuredSearchedUser> getListOfStructuredSearchedUser(String valoreRicerca) {
        String[] splitted = valoreRicerca.split("[ ]+");
        String preparedString = "";
        for (String s : splitted) {
            preparedString += s + "%";
        }

        System.out.println("preparedString-> " + preparedString);

        String sql = "SELECT tblAccount.username,tblPersona.nome, tblPersona.cognome, tblPersona.imgProfilo "
                + "FROM tblAccount INNER JOIN tblPersona ON tblAccount.idAccount = tblPersona.idAccount "
                + "WHERE tblAccount.username LIKE ? OR (tblPersona.nome||' '||tblPersona.cognome) LIKE ?";

        ArrayList<StructuredSearchedUser> lista = new ArrayList<>();

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, (valoreRicerca + "%"));
            ps.setString(2, preparedString);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String nomeCognome = rs.getString("nome") + " " + rs.getString("cognome");
                String imgProfilo = rs.getString("imgProfilo");

                String imgProfiloBase64 = getParsedToBase64(getPercorsoImgProfiloUtente(imgProfilo));

                StructuredSearchedUser ssu = new StructuredSearchedUser(nomeCognome, username, imgProfiloBase64);
                lista.add(ssu);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static ArrayList<StructuredSearchedGroup> getListOfStructuredSearchedGroup(String valoreRicerca) {
        String[] splitted = valoreRicerca.split("[ ]+");
        String preparedString = "";
        for (String s : splitted) {
            preparedString += s + "%";
        }
        String sql = "SELECT tblGruppo.idGruppo, tblGruppo.nome, tblGruppo.imgGruppo "
                + "FROM tblGruppo "
                + "WHERE tblGruppo.nome LIKE ?";

        ArrayList<StructuredSearchedGroup> lista = new ArrayList<>();

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, preparedString);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idGruppo = rs.getInt("idGruppo");
                String nomeGruppo = rs.getString("nome");
                String imgGruppo = rs.getString("imgGruppo");

                String imgProfiloBase64 = getParsedToBase64(getPercorsoImgGruppo(imgGruppo));

                StructuredSearchedGroup ssg = new StructuredSearchedGroup(idGruppo, nomeGruppo, imgProfiloBase64);
                lista.add(ssg);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static PermessiVisibilita getPermessiVisibilitaDatiDiAccount(int idAccount) {
        PermessiVisibilita permessi = new PermessiVisibilita();
        String sql = "SELECT * "
                + "FROM tblPermessiVisibilita "
                + "WHERE idAccount=?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAccount);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                permessi.setIdPermesso(rs.getInt("idPermesso"));
                permessi.setIsVisibileDataDiNascita(rs.getBoolean("dataDiNascita"));
                permessi.setIsVisibileEmail(rs.getBoolean("email"));
                permessi.setIsVisibileIndirizzo(rs.getBoolean("indirizzo"));
                permessi.setIsVisibileListaHobby(rs.getBoolean("listaHobby"));
                permessi.setIsVisibileTelefono(rs.getBoolean("telefono"));
                permessi.setIsVisibileSesso(rs.getBoolean("sesso"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return permessi;
    }

    public static Persona getDatiPersonaliByIdAccount(int idAccount) {
        Persona datiPersonali = new Persona();
        String sql = "SELECT * "
                + "FROM tblPersona "
                + "WHERE idAccount=?";
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAccount);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idPersona = rs.getInt("idPersona");
                String nome = rs.getString("nome");
                String cognome = rs.getString("cognome");
                String dataDiNascita = rs.getString("dataDiNascita");
                char sesso = rs.getString("sesso").charAt(0);
                String imgProfilo = rs.getString("imgProfilo");
                String indirizzo = rs.getString("indirizzo");
                datiPersonali = new Persona(idPersona, nome, cognome, indirizzo, parseStringToDate(dataDiNascita), sesso, imgProfilo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datiPersonali;
    }

    public static InfoUtente getInfoUtente(String username) {
        InfoUtente info = new InfoUtente();
        Account account = getAccountByUsername(username);
        PermessiVisibilita pv = getPermessiVisibilitaDatiDiAccount(account.getIdAccount());
        //username è sempre visibile
        Persona persona = UtilDB.getDatiPersonaliByIdAccount(account.getIdAccount());
        if (pv.getIsVisibileSesso()) {
            info.setSesso(persona.getSesso() + "");
        }
        if (pv.getIsVisibileDataDiNascita()) {
            info.setDataDiNascita(parseDateToString(persona.getDataDiNascita()));
        }
        if (pv.getIsVisibileTelefono()) {
            info.setTelefono(account.getTelefono());
        }
        if (pv.getIsVisibileIndirizzo()) {
            info.setIndirizzo(persona.getIndirizzo());
        }
        if (pv.getIsVisibileEmail()) {
            info.setEmail(account.getEmail());
        }
        return info;
    }

    public static ArrayList<StructuredHobby> getListaStructuredHobbyPraticatiDaAccount(int idAccount) {
        ArrayList<StructuredHobby> lista = new ArrayList<>();

        String sql = "SELECT tblHobby.idHobby, tblHobby.nomeHobby, tbrHobbyAccount.dataInizioHobby "
                + "FROM tblHobby INNER JOIN tbrHobbyAccount ON tblHobby.idHobby=tbrHobbyAccount.idHobby "
                + "WHERE tbrHobbyAccount.idAccount=? "
                + "ORDER BY tbrHobbyAccount.dataInizioHobby DESC";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAccount);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idHobby = rs.getInt("idHobby");
                String nomeHobby = rs.getString("nomeHobby");
                String dataInizioHobby = rs.getString("dataInizioHobby");
                StructuredHobby sh = new StructuredHobby(idHobby, nomeHobby, dataInizioHobby);
                lista.add(sh);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public static String getImgProfiloUtente(String username) {
        String img = "";

        String sql = "SELECT tblPersona.imgProfilo  "
                + "FROM tblAccount INNER JOIN tblPersona ON tblAccount.idAccount=tblPersona.idAccount "
                + "WHERE tblAccount.username LIKE ? ";

        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String imgProfilo = rs.getString("imgProfilo");
                img = getParsedToBase64(getPercorsoImgProfiloUtente(imgProfilo));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }

}
