package com.example.alinequintana.clinica_dental;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import OpenHelper.SQLite_OpenHelper;

public class Perfil extends AppCompatActivity {
    Button actualizar;
    EditText txtnom, txtusu, txtpass, tedad, txtdire, txttel;

    int contador = 0;
    public SQLite_OpenHelper.dbadapter db;
    long usuario=1;
    int posicion;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        db = new SQLite_OpenHelper.dbadapter(getApplication());
        db.open();
        cursor = db.getalldata();


        txtnom = (EditText)findViewById(R.id.editText6);
        tedad = (EditText)findViewById(R.id.editText4);
        txtdire = (EditText)findViewById(R.id.editText3);
        txttel = (EditText)findViewById(R.id.editText2);

        actualizar=(Button)findViewById(R.id.BTactualizar);
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnom.setText(cursor.getString(0));
                tedad.setText(cursor.getString(3));
                txtdire.setText(cursor.getString(4));
                txttel.setText(cursor.getString(5));
            }

        });

    }

}
