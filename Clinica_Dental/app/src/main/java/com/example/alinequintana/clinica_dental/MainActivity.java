package com.example.alinequintana.clinica_dental;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Principal;

import OpenHelper.SQLite_OpenHelper;

public class MainActivity extends AppCompatActivity {
    TextView tv_registrarse;
    Button btningresar;

    String info_preferencias;
    SharedPreferences preferencias;
    EditText et_usuario, et_pass;
    CheckBox recordar;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "BDClinica", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        et_usuario=(EditText)findViewById(R.id.TXTusuario);


        preferencias = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        et_usuario.setText(preferencias.getString("usuario",""));
        et_usuario.setText(preferencias.getString("usuario",""));



        tv_registrarse = findViewById(R.id.LABELregistrarse);
        tv_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentReg = new Intent(MainActivity.this, Registro.class);
                MainActivity.this.startActivity(intentReg);
            }
        });

        btningresar = (Button) findViewById(R.id.BTNingresar);
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencias=getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("usuario", et_usuario.getText().toString());
                editor.commit();


                EditText txtusu = (EditText) findViewById(R.id.TXTusuario);
                EditText txtpass = (EditText) findViewById(R.id.TXTpassword);
                try {
                    Cursor cursor = helper.Validarusupas(txtusu.getText().toString(), txtpass.getText().toString());
                    if (cursor.getCount() > 0) {
                        Intent i = new Intent(getApplicationContext(), Pantalla_principal.class);
                        MainActivity.this.startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "USUARIO Y/O PASSWORD INCORRECTOS", Toast.LENGTH_LONG).show();
                    }
                    txtusu.setText("");
                    txtpass.setText("");
                    txtusu.findFocus();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        });

    }
    public void onResume(){
        super.onResume();
        preferencias = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        info_preferencias = preferencias.getString("info_actividad","");

    }



}


