package com.example.alinequintana.clinica_dental;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import OpenHelper.SQLite_OpenHelper;

public class Registro extends AppCompatActivity {
    Button b_registrar;
    EditText et_nombre, et_usuario, et_password, et_edad, et_direccion, et_telefono;
    Spinner sexo;
    SQLite_OpenHelper helper = new SQLite_OpenHelper(this,"BDClinica",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        b_registrar = (Button)findViewById(R.id.BTNregistar);
        et_nombre = (EditText)findViewById(R.id.TXTnombre);
        et_usuario = (EditText)findViewById(R.id.TXTusuario);
        et_password = (EditText)findViewById(R.id.TXTr_password);
        et_edad = (EditText)findViewById(R.id.TXTedad);
        et_direccion = (EditText)findViewById(R.id.TXTdireccion);
        et_telefono = (EditText)findViewById(R.id.TXTtelefono);

        b_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!et_nombre.getText().toString().isEmpty() &&
                        !et_usuario.getText().toString().isEmpty() &&
                        !et_password.getText().toString().isEmpty() &&
                        !et_edad.getText().toString().isEmpty() &&
                        !et_direccion.getText().toString().isEmpty() &&
                        !et_telefono.getText().toString().isEmpty()) {
                    helper.abrir();
                    helper.insertarReg(String.valueOf(et_nombre.getText()),
                            String.valueOf(et_usuario.getText()),
                            String.valueOf(et_password.getText()),
                            String.valueOf(et_edad.getText()),
                            String.valueOf(et_direccion.getText()),
                            String.valueOf(et_telefono.getText()));
                    helper.cerrar();

                    Toast.makeText(getApplicationContext(), "USUARIO REGISTRADO CON EXITO"
                            , Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "LLENE TODOS LOS CAMPOS"
                            , Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
