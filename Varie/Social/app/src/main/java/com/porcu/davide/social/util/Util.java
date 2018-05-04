package com.porcu.davide.social.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.porcu.davide.social.mobilecontainer.BasicUserInfo;
import com.porcu.davide.social.model.Hobby;
import com.porcu.davide.social.model.User;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by davide on 22/04/18.
 */

public class Util {

    public static Bitmap getBitmapFromBase64(String base64) {

        Log.d("da convertire -> ",base64);
        String readyToBeParsed = base64.substring(base64.indexOf(",") + 1);

        byte[] decodedString = Base64.decode(readyToBeParsed, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        image.setImageBitmap(decodedByte);
        return decodedByte;
    }

    //indirizzo ip e porta
    public static String indirizzoIPServer = "";
    public static String portaServer = "";

    private static final String NAMESPACE = "http://web.pkgsocial/";
    private static final String URL_HEAD = "http://";//192.168.4.86:8080";
    private static final String URL_TAIL = "/SocialWebServices/Social?wsdl";

    //private static final String METHOD_NAME = "getListaHobby";
    private static final String SOAP_ACTION = "";


    public static ArrayList<Hobby> getListaHobby() {
        ArrayList<Hobby> listaHobby = new ArrayList<>();
        String url = URL_HEAD + indirizzoIPServer+":"+portaServer + URL_TAIL;
        Log.d("DEBUG ",url);
        try {
            SoapObject request =new SoapObject(NAMESPACE, "getListaHobby");
            //request.addProperty("nome webparam","valore webparam");
            SoapSerializationEnvelope envelope =new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport =new HttpTransportSE(url);
            try{
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                Log.d("RICEVUTO DA HTTP -> ", response.getProperty("return").toString() );
                Gson gsonizer=new Gson();
                listaHobby=gsonizer.fromJson(response.getProperty("return").toString(),new TypeToken<ArrayList<Hobby>>(){}.getType());


            }catch(Exception ex){
                Log.d("RICEVUTO DA HTTP -> ","ERRORE "+ex.toString());
            }
        } catch (Exception ex) {
            Log.d("RICEVUTO DA HTTP -> ","ERRORE "+ex.toString());
        }
        return listaHobby;
    }


    public static ArrayList<User> getListaUtentiPraticantiDiHobby(int idHobby){
        ArrayList<User> listaUser = new ArrayList<>();
        String url = URL_HEAD + indirizzoIPServer+":"+portaServer + URL_TAIL;
        try {
            SoapObject request =new SoapObject(NAMESPACE, "getListaPraticantiDiHobby");
            request.addProperty("idHobby",idHobby);
            SoapSerializationEnvelope envelope =new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport =new HttpTransportSE(url);
            try{
                androidHttpTransport.call(SOAP_ACTION, envelope);
                SoapObject response = (SoapObject) envelope.bodyIn;
                Log.d("RICEVUTO DA HTTP -> ", response.getProperty("return").toString() );
                Gson gsonizer=new Gson();
                listaUser=gsonizer.fromJson(response.getProperty("return").toString(),new TypeToken<ArrayList<User>>(){}.getType());
            }catch(Exception ex){
                Log.d("RICEVUTO DA HTTP -> ","ERRORE "+ex.toString());
            }
        } catch (Exception ex) {
            Log.d("RICEVUTO DA HTTP -> ","ERRORE "+ex.toString());
        }
        return listaUser;
    }




}
