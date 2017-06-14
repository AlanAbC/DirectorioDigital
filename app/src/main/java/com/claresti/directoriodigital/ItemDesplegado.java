package com.claresti.directoriodigital;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ItemDesplegado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item_desplegado);

        //Seccion para cambiar tipografias
        // Seteamos en una Variable donde tenemos la fuente (podemos omitir este paso y ponerla directamente cuando cargamos la fuente)
        String carpetaFuente = "fonts/Quattrocento-Regular.otf";
        // Obtenemos la id del TextView donde queremos cambiar la fuente
        TextView vistaFuente = (TextView) findViewById(R.id.tit_desplegado);
        TextView nombre = (TextView) findViewById(R.id.nombre);
        TextView txt_nombre = (TextView) findViewById(R.id.txt_nombre);
        TextView descripcion = (TextView) findViewById(R.id.descripcion);
        TextView txt_descripcion = (TextView) findViewById(R.id.txt_descripcion);
        TextView telefono = (TextView) findViewById(R.id.telefono);
        TextView txt_telefono = (TextView) findViewById(R.id.txt_telefono);
        TextView correo = (TextView) findViewById(R.id.correo);
        TextView txt_correo = (TextView) findViewById(R.id.txt_correo);
        TextView sitio = (TextView) findViewById(R.id.sitio);
        TextView txt_sitio = (TextView) findViewById(R.id.txt_sitio);
        TextView direccion = (TextView) findViewById(R.id.direccion);
        TextView txt_direccion = (TextView) findViewById(R.id.txt_direccion);
        // Cargamos la fuente
        Typeface fuente = Typeface.createFromAsset(getAssets(), carpetaFuente);
        // Aplicamos la fuente
        vistaFuente.setTypeface(fuente);
        nombre.setTypeface(fuente);
        txt_nombre.setTypeface(fuente);
        descripcion.setTypeface(fuente);
        txt_descripcion.setTypeface(fuente);
        telefono.setTypeface(fuente);
        txt_telefono.setTypeface(fuente);
        correo.setTypeface(fuente);
        txt_correo.setTypeface(fuente);
        sitio.setTypeface(fuente);
        txt_sitio.setTypeface(fuente);
        direccion.setTypeface(fuente);
        txt_direccion.setTypeface(fuente);
    }
}
