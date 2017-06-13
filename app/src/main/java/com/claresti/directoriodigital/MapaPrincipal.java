package com.claresti.directoriodigital;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import com.getbase.floatingactionbutton.*;

import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MapaPrincipal extends FragmentActivity implements OnMapReadyCallback {

    // Variable del mapa
    private GoogleMap mMap;

    AlertDialog alert = null;

    // Permisos
    private final int MY_PERMISSION = 100;

    // Menu, Declaracion de variables
    private RelativeLayout ventana;
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

        // Asignacion de la variable del layout principal
        ventana = (RelativeLayout)findViewById(R.id.l_ventana);

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
        LatLng qro = new LatLng(20.5897233, -100.3915028);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(qro));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14.0f));

        //Validacion de permisos
        if (permisos()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            findMe();
        }
    }

    /**
     * funcion encargada de verificar los permisos de ubicacion y en caso de no tenerlos
     * solicita al usuario activarlos
     * @return true en caso de tener los permisos, false en caso contrario
     */
    private boolean permisos() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if((checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) && (checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)){
            return true;
        }
        if((shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) || (shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION))){
            Snackbar.make(ventana, "Los permisos son necesarios para poder usar la aplicación", Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(View v) {
                            requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, MY_PERMISSION);
                        }
                    }).show();
        }else{
            requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, MY_PERMISSION);
        }
        return false;
    }

    /**
     * funcion que consigue la ubicacion actual del usuario y lo posiciona en el mapa
     */
    private void findMe() {
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        latitud = location.getLatitude();
        longitud = location.getLongitude();
        LatLng myLocation = new LatLng(latitud, longitud);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 14));
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
