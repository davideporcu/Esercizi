package com.porcu.davide.socialapp.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.IDNA;
import android.provider.BaseColumns;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.porcu.davide.socialapp.activity.MainActivity;
import com.porcu.davide.socialapp.model.BasicUserInfo;
import com.porcu.davide.socialapp.model.Hobby;
import com.porcu.davide.socialapp.model.InfoUtente;
import com.porcu.davide.socialapp.model.StructuredHobby;
import com.porcu.davide.socialapp.model.StructuredSearchedGroup;
import com.porcu.davide.socialapp.model.StructuredSearchedUser;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

/**
 * Created by davide on 22/04/18.
 */

public class Util {

    public static Gson gsonizer = new Gson();

    private static final String NAMESPACE = "http://web.pkgsocial/";
    private static final String URL_HEAD = "http://";//192.168.4.86:8080";
    private static final String URL_TAIL = "/SocialWebServices/Social?wsdl";

    private static String complete_url = "";

    private static final String SOAP_ACTION = "";

    public static Bitmap getBitmapFromBase64(String base64) {
        //Log.d("da convertire -> ", base64);
        String readyToBeParsed = base64.substring(base64.indexOf(",") + 1);
        byte[] decodedString = Base64.decode(readyToBeParsed, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        image.setImageBitmap(decodedByte);
        return decodedByte;
    }


    public static void setIndirizzoPortaServer(String indirizzo, String porta) {
        complete_url = URL_HEAD + indirizzo + ":" + porta + URL_TAIL;
    }


    public static ArrayList<Hobby> getListaHobby(String username) {
        String method_name = "getListaHobby";
        String responseString = callWebService(method_name);
        ArrayList<Hobby> listaHobby = gsonizer.fromJson(responseString, new TypeToken<ArrayList<Hobby>>() {
        }.getType());
        return listaHobby;
    }


    public static ArrayList<BasicUserInfo> getListaUtentiPraticantiDiHobby(int idHobby) {
        String method_name = "getListaPraticantiDiHobby";
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("idHobby");//nome web param (nel web service)
        propertyInfo.setValue(idHobby);

        String responseString = callWebService(method_name, propertyInfo);
        ArrayList<BasicUserInfo> listaUser = gsonizer.fromJson(responseString, new TypeToken<ArrayList<BasicUserInfo>>() {
        }.getType());
        return listaUser;
    }


    public static ArrayList<StructuredHobby> getListaHobbyPraticatiDaUtente(String username) {
        String method_name = "getListaStructuredHobbyPraticatiDaUtente";
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("username");//nome web param (nel web service)
        propertyInfo.setValue(username);

        String responseString = callWebService(method_name, propertyInfo);
        ArrayList<StructuredHobby> listaUser = gsonizer.fromJson(responseString, new TypeToken<ArrayList<StructuredHobby>>() {
        }.getType());

        return listaUser;
    }

    public static ArrayList<StructuredSearchedUser> getListaStructuredSearchedUser(String query) {
        String method_name = "getListaOfStructuredSearchedUser";
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("query");//nome web param (nel web service)
        propertyInfo.setValue(query);

        String responseString = callWebService(method_name, propertyInfo);
        ArrayList<StructuredSearchedUser> listaUser = gsonizer.fromJson(responseString, new TypeToken<ArrayList<StructuredSearchedUser>>() {
        }.getType());

        return listaUser;
    }

    public static ArrayList<StructuredSearchedGroup> getListaStructuredSearchedGroup(String query) {
        String method_name = "getListaOfStructuredSearchedGroup";
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("query");//nome web param (nel web service)
        propertyInfo.setValue(query);

        String responseString = callWebService(method_name, propertyInfo);
        ArrayList<StructuredSearchedGroup> listaGroup = gsonizer.fromJson(responseString, new TypeToken<ArrayList<StructuredSearchedGroup>>() {
        }.getType());

        return listaGroup;
    }

    public static InfoUtente getInfoDiUtente(String username) {
        String method_name = "getInfoDiUtente";
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("username");//nome web param (nel web service)
        propertyInfo.setValue(username);

        String responseString = callWebService(method_name, propertyInfo);
        InfoUtente infoUtente = gsonizer.fromJson(responseString, InfoUtente.class);

        return infoUtente;
    }


    public static String getImgProfiloUtente(String username) {
        String method_name = "getImgProfiloUtente";
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName("username");//nome web param (nel web service)
        propertyInfo.setValue(username);

        String responseString = callWebService(method_name, propertyInfo);

        return responseString;
    }


    private static String callWebService(String method_name, PropertyInfo... propertyInfos) {
        String responseString = "";

        try {
            SoapObject request = new SoapObject(NAMESPACE, method_name);

            for (PropertyInfo propertyInfo : propertyInfos) {
                request.addProperty(propertyInfo);
            }

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(complete_url);
            try {
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;

                Log.d("RICEVUTO DA HTTP -> ", response.getProperty("return").toString());
                responseString = response.getProperty("return").toString();
            } catch (Exception ex) {
                Log.d("RICEVUTO DA HTTP -> ", "ERRORE " + ex.toString());

            }
        } catch (Exception ex) {
            Log.d("RICEVUTO DA HTTP -> ", "ERRORE " + ex.toString());
        }

        return responseString;
    }

}
