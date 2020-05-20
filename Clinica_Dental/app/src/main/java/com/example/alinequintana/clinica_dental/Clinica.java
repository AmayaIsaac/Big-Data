package com.example.alinequintana.clinica_dental;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TreeMap;

public class Clinica extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner tratamientos;
    TreeMap<String, String> descripcion;
    EditText etdescrip;

    final static String[] names = { "LIMPIEZA","RESINAS", "BRACKETS","BLANQUEAMIETO","ENDODONCIA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinica);

        etdescrip = (EditText) findViewById(R.id.ETdescripcion);


        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, names);
        tratamientos = (Spinner)findViewById(R.id.SPinformacion);
        tratamientos.setAdapter(adapter);
        tratamientos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapterView.getContext(),(String)"USTED SELECCIONO: "+ adapterView.getItemAtPosition(i), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void llenarDescripcion(){
        descripcion = new TreeMap<String, String>();
        descripcion.put("LIMPIEZA","Una limpieza dental es un tratamiento que consiste en eliminar sarro y la placa dento-bacteriana para mantener la salud de las encías. En nuestra clinica, hacemos la limpieza usando ultrasonido, que funciona a base de vibración y agua a presión.");
        descripcion.put("RESINAS","La resina es un procedimiento en el que se restaura la imagen y función de un diente después de eliminar caries. Se puede utilizar cuando un diente tiene alguna de estas condiciones: \n* Cavidades ocasionadas por caries. \n* Como alternativa estética para una amalgama.");
        descripcion.put("BRACKETS","Los usos más frecuentes son: \n* Corrección de malposición dental o apiñamiento que impida la correcta función al masticar. \n* Enfermedades de encías por mala higiene debido a la posición incorrecta de los dientes. \n* Relación correcta de maxilar y mandíbula.");
        descripcion.put("BLANQUEAMIENTO","Un blanqueamiento dental es un tratamiento que mejora la apariencia de los dientes al aclararlos varios tonos, según las necesidades del paciente. \nAlgunos de sus beneficios son: \n* Proporciona una sonrisa estética y agradable. \n* Aumenta tu confianza y te devuelve la seguridad.");
        descripcion.put("ENDODONCIA","¿CUANDO ES NECESARIA UN ENDODONCIA? \n* Infección del nervio dental por caries.\n" +
                "* Fracturas dentales en algunos casos.\n" +
                "* Lesiones por bacterias en la raíz del diente. \n" +
                "La ventaja es que permite mantener un diente en la boca a largo plazo.");
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
