package com.claresti.directoriodigital;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class Buscar extends AppCompatActivity implements SearchView.OnQueryTextListener{
    ListView lv;
    SearchView searchView;
    ArrayAdapter<String> adapter;
    String [] data ={"Este","Es","Una","Prueba","De","SearchView"};

    private final String LOGTAG = "DVB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_buscar);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.placeAutocompleteFragment);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();

        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.e(LOGTAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                Log.e(LOGTAG, "Error: " + status);
            }
        });


        /*//Todo esto es una prueba para ver como funiona searchview
        lv=(ListView)findViewById(R.id.listview);
        searchView=(SearchView)findViewById(R.id.inp_layout_busqueda);
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, data);
        lv.setAdapter(adapter);
        searchView.setOnQueryTextListener(this);*/

        //Seccion de cambio de tipografia
        //Seccion para cambiar tipografias
        // Seteamos en una Variable donde tenemos la fuente (podemos omitir este paso y ponerla directamente cuando cargamos la fuente)
        String carpetaFuente = "fonts/Quattrocento-Regular.otf";
        // Obtenemos la id del TextView donde queremos cambiar la fuente
        TextView vistaFuente = (TextView) findViewById(R.id.tit_busqueda);
        // Cargamos la fuente
        Typeface fuente = Typeface.createFromAsset(getAssets(), carpetaFuente);
        // Aplicamos la fuente
        vistaFuente.setTypeface(fuente);

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
