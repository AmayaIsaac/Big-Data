package com.example.alinequintana.clinica_dental;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import OpenHelper.SQLiteCitas;
import OpenHelper.SQLite_OpenHelper;

public class Citas extends AppCompatActivity implements View.OnClickListener {

    Button bhora, bfecha, b_agendar;
    EditText txt_hora, txt_fecha, txt_archivo;
    int dia, mes, ano, hora, minutos;
    SQLiteCitas helpercitas = new SQLiteCitas(this,"BDClinica",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);

        b_agendar = (Button)findViewById(R.id.BTNagendar2);

        bfecha =(Button)findViewById(R.id.BTNfecha);
        bhora =(Button)findViewById(R.id.BTNhora);
        txt_fecha =(EditText) findViewById(R.id.TXTfecha);
        txt_hora =(EditText) findViewById(R.id.TXThora);

        txt_archivo=(EditText)findViewById(R.id.TXTarchivo);

        String[] archivos = fileList();

        if (existe(archivos, "notas.txt"))
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput("notas.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                txt_archivo.setText(todo);
            } catch (IOException e) {
            }

        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);

        b_agendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txt_fecha.getText().toString().isEmpty() &&
                        !txt_hora.getText().toString().isEmpty()) {
                    helpercitas.abrircitas();
                    helpercitas.insertarRegcitas(String.valueOf(txt_fecha.getText()),
                            String.valueOf(txt_hora.getText()));
                    helpercitas.cerrarcitas();
                    Toast.makeText(getApplicationContext(), "SU CITA FUE AGENDADA", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(), "SELECCIONE FECHA Y HORA", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==bfecha){
            final Calendar calendario = Calendar.getInstance();
            ano=calendario.get(Calendar.YEAR);
            mes=calendario.get(Calendar.MONTH);
            dia =calendario.get(Calendar.DAY_OF_MONTH);



            @SuppressLint({"NewApi", "LocalSuppress"}) DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
                        txt_fecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            },ano,mes,dia);
            datePickerDialog.show();


        }


        if(view==bhora){
            final Calendar calendario = Calendar.getInstance();
            hora=calendario.get(Calendar.HOUR_OF_DAY);
            minutos=calendario.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    txt_hora.setText(hourOfDay+":"+minute);

                }
            },hora,minutos,false);
            timePickerDialog.show();

        }
    }
    private boolean existe(String[] archivos, String archBusca){
        for(int f=0; f<archivos.length; f++)
            if(archBusca.equals(archivos[f]))
                return true;
        return false;
    }

    public void grabar(View v) {
        String nomarchivo=txt_fecha.getText().toString();
        nomarchivo=nomarchivo.replace('/','-');
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    nomarchivo, Activity.MODE_PRIVATE));
            archivo.write(txt_archivo.getText().toString());
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",
                Toast.LENGTH_SHORT);
        t.show();
        txt_fecha.setText("");
        txt_archivo.setText("");
    }

    public void recuperar(View v) {
        String nomarchivo=txt_fecha.getText().toString();
        nomarchivo=nomarchivo.replace('/','-');
        boolean enco=false;
        String[] archivos = fileList();
        for (int f = 0; f < archivos.length; f++)
            if (nomarchivo.equals(archivos[f]))
                enco= true;
        if (enco==true) {
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(nomarchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                txt_archivo.setText(todo);
            } catch (IOException e) {
            }
        } else
        {
            Toast.makeText(this,"No hay datos grabados para dicha fecha", Toast.LENGTH_LONG).show();
            txt_archivo.setText("");
        }
    }
}

