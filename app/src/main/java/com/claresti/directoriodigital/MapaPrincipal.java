package com.claresti.directoriodigital;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.getbase.floatingactionbutton.*;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapaPrincipal extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    AlertDialog alert = null;
    //Menu, Declaracion de variables
    private DrawerLayout drawerLayout;
    final List<MenuItem> items = new ArrayList<>();
    private Menu menu;
    private Menu menuCat;
    private Menu menuAcerca;
    private MenuItem acerca;
    private ImageView btnMenu;
    private NavigationView nav;
    FloatingActionButton buscar;
    FloatingActionButton prueba;

    private final String LOGTAG = "DVMP";

    //Location Variables
    private LocationManager locationManager;
    private Criteria criteria;
    private double latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_principal);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        drawerLayout = (DrawerLayout) findViewById(R.id.dLayout);

        //Asignacion de vistas a las variables de Menu
        nav = (NavigationView) findViewById(R.id.navigation);
        menu = nav.getMenu();

        menuCat= menu.getItem(0).getSubMenu();

        //Get Location
        locationManager = (LocationManager) getSystemService(getApplicationContext().LOCATION_SERVICE);
        criteria = new Criteria();

        //Location Update Request
        getLastLocationUpdate();


        /*Todo esto es temproal solo lo puse para probar como se veia el Acerca de */
        menuAcerca=menu.getItem(1).getSubMenu();
        acerca=menuAcerca.getItem(0);
        acerca.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent i = new Intent(MapaPrincipal.this, Acerca.class);
                startActivity(i);
                return false;
            }
        });
        //Aqui Acaba

        // Seteamos en una Variable donde tenemos la fuente (podemos omitir este paso y ponerla directamente cuando cargamos la fuente)
        String carpetaFuente = "fonts/Quattrocento-Regular.otf";

        // Obtenemos la id del TextView donde queremos cambiar la fuente
                TextView vistaFuente = (TextView) findViewById(R.id.titMenu);

        // Cargamos la fuente
                Typeface fuente = Typeface.createFromAsset(getAssets(), carpetaFuente);

        // Aplicamos la fuente
                vistaFuente.setTypeface(fuente);

        buscar=(FloatingActionButton)findViewById(R.id.accion_Buscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapaPrincipal.this, Buscar.class);
                startActivity(i);
            }
        });
        prueba = (FloatingActionButton)findViewById(R.id.accion_verTodo);
        prueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapaPrincipal.this, ItemDesplegado.class);
                startActivity(i);
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        LatLng lastLocation = new LatLng(latitud, longitud);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lastLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
    }

    //Funcion para agregar items al menu solo resta establecer como se manejaran los iconos
    public void agregarMenuItem(String nombreItem){
        menuCat.add(nombreItem).setIcon(R.drawable.menu);
    }

    //Request to enable GPS
    private void checkLocationSettings() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }

    private void getLastLocationUpdate()
    {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(5000, 100, criteria, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                latitud = location.getLatitude();
                longitud = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        }, null);

        Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
        if(location != null) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();
            Log.e(LOGTAG, "" + latitud + longitud);
        }
    }

    //The application switch to first plane
    @Override
    public void onResume()
    {
        super.onResume();
    }
}
