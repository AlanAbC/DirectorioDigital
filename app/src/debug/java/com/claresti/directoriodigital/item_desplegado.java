package com.claresti.directoriodigital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class item_desplegado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_item_desplegado);
    }
}
