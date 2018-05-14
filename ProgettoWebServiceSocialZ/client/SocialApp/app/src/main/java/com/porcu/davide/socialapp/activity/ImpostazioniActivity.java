package com.porcu.davide.socialapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.porcu.davide.socialapp.R;
import com.porcu.davide.socialapp.util.Util;

public class ImpostazioniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_impostazioni);
        final EditText txtIndirizzo = findViewById(R.id.txtIndirizzo);
        final EditText txtPorta = findViewById(R.id.txtPorta);

        final SharedPreferences sharedPref = getSharedPreferences("mieImpostazioni", Context.MODE_PRIVATE);
        String indirizzo = sharedPref.getString("indirizzo", "");
        String porta = sharedPref.getString("porta", "");

        txtIndirizzo.setText(indirizzo);
        txtPorta.setText(porta);


        Button btn = findViewById(R.id.btnModificaImpostazioni);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String indirizzo = txtIndirizzo.getText().toString();
                String porta = txtPorta.getText().toString();

                if (!indirizzo.isEmpty() && !porta.isEmpty()) {

                    SharedPreferences.Editor editor = sharedPref.edit();
                    // Save your string in SharedPref
                    editor.putString("indirizzo", indirizzo);
                    editor.putString("porta", porta);
                    editor.commit();
                    Util.setIndirizzoPortaServer(indirizzo,porta);
                }


            }
        });


    }
}
