package com.claresti.directoriodigital;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Buscar extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ListView lv;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    String [] data ={"Este","Es","Una","Prueba","De","SearchView"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_buscar);
        //Todo esto es una prueba para ver como funiona searchview
        lv=(ListView)findViewById(R.id.listview);
        searchView=(SearchView)findViewById(R.id.inp_layout_busqueda);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data);
        lv.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);
    }
    public boolean onQueryTextSubmit(String query){
        return false;

    }
    public boolean onQueryTextChange(String newText){
        String text = newText;
        adapter.getFilter().filter(newText);
        return false;
    }
}
