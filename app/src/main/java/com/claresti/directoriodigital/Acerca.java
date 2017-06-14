package com.claresti.directoriodigital;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Acerca extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_acerca);

        // Declaracion de variables de layout
        ImageView claresTi;
        ImageView btnRegresar;

        // Asignacion de variables de layout
        claresTi = (ImageView)findViewById(R.id.acerca);
        btnRegresar = (ImageView)findViewById(R.id.Btnregresar);

        // Asignacion de listeners
        claresTi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.claresti.com");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //Seccion para cambiar tipografias
        // Seteamos en una Variable donde tenemos la fuente (podemos omitir este paso y ponerla directamente cuando cargamos la fuente)
        String carpetaFuente = "fonts/Quattrocento-Regular.otf";
        // Obtenemos la id del TextView donde queremos cambiar la fuente
        TextView vistaFuente = (TextView) findViewById(R.id.tit_acerca);
        TextView desarrolado = (TextView) findViewById(R.id.txt_DesarrolladoPor);
        TextView acerca = (TextView) findViewById(R.id.txt_claresTI);
        // Cargamos la fuente
        Typeface fuente = Typeface.createFromAsset(getAssets(), carpetaFuente);
        // Aplicamos la fuente
        vistaFuente.setTypeface(fuente);
        desarrolado.setTypeface(fuente);
        acerca.setTypeface(fuente);
    }
}
