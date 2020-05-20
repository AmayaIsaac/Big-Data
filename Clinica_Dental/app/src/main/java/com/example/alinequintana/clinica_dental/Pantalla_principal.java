package com.example.alinequintana.clinica_dental;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class Pantalla_principal extends AppCompatActivity {
    EditText txtnom, txtusu, txtpass, tedad, txtdire, txttel;
    TextView cerrarsesion;
    ImageButton bperfil, btratamientos, bttelefono, btfacebook, btemail, bcitas;

    int contador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

        cerrarsesion = (TextView)findViewById(R.id.TVcerrarsesion);
        cerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(Pantalla_principal.this);
                alerta.setMessage("¿ESTA SEGURO QUE QUIERE CERRAR SESION?");
                alerta.setCancelable(false);
                alerta.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                alerta.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("CERRAR SESIÓN");
                titulo.show();

            }
        });
        txtnom = (EditText)findViewById(R.id.editText6);
        tedad = (EditText)findViewById(R.id.editText4);
        txtdire = (EditText)findViewById(R.id.editText3);
        txttel = (EditText)findViewById(R.id.editText2);
        bperfil = (ImageButton)findViewById(R.id.IBperfil);
        bperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Perfil.class);
                Pantalla_principal.this.startActivity(i);
                }

        });
        btratamientos = (ImageButton)findViewById(R.id.IBclinica);
        btratamientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Clinica.class);
                Pantalla_principal.this.startActivity(i);
            }
        });
        bcitas = (ImageButton)findViewById(R.id.IBcitas);
        bcitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Citas.class);
                Pantalla_principal.this.startActivity(i);
            }
        });
        bttelefono = (ImageButton)findViewById(R.id.IMtelefono);
        bttelefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:449 99 06 661"));
                startActivity(intent);
            }
        });
        btfacebook = (ImageButton)findViewById(R.id.IMfacebook);
        btfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Contacto.class);
                i.putExtra("direccion","www.facebook.com");
                Pantalla_principal.this.startActivity(i);
            }
        });
        btemail = (ImageButton)findViewById(R.id.IMemail);
        btemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Contacto.class);
                i.putExtra("direccion","www.gmail.com");
                Pantalla_principal.this.startActivity(i);
            }
        });

    }


    @Override
    public void onBackPressed() {
        if(contador==0){
            Toast.makeText(getApplicationContext(),"Presione de nuevo para salir", Toast.LENGTH_SHORT).show();
            contador++;
        }else{
            super.onBackPressed();
        }
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long l) {
            }
            @Override
            public void onFinish() {
                contador=0;
            }
        }.start();
    }
}
