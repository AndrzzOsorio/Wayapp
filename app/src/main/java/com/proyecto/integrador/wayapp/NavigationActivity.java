package com.proyecto.integrador.wayapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    private GoogleMap map;
    Location location;
    ArrayList<Punto> puntos = new ArrayList<>();
    ArrayList<Punto> via = new ArrayList<>();
    FloatingActionButton delimitar;
    FloatingActionButton elemento;
    FloatingActionButton encuesta;
    FloatingActionButton delimitarfin;
    FloatingActionButton acceso;
    int flag = 0;
    Double lat;
    Double lon;
    ArrayList<Punto> prueba = new ArrayList<>();

    Rancheria r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        supportMapFragment.getMapAsync(this);


        Intent intent = getIntent();
        prueba.add(new Punto(5.049300019378771,-75.47945895851801,"descripcion"));
        prueba.add(new Punto(5.049255319484387,-75.47953471146438,"descripcion1"));
        prueba.add(new Punto(5.049186817507857,-75.47945895851801,"descripcion2"));
        prueba.add(new Punto(5.049300019378771,-75.47945895851801,"descripcion"));

        r = (Rancheria) intent.getSerializableExtra("rancheria");
      //  nombrerancheria.setText(s);
        delimitar = (FloatingActionButton) findViewById(R.id.delimitar);
        elemento = (FloatingActionButton) findViewById(R.id.elemento);
        encuesta = (FloatingActionButton) findViewById(R.id.encuesta);
        delimitarfin = (FloatingActionButton) findViewById(R.id.delimitarfin);
        acceso = (FloatingActionButton) findViewById(R.id.acceso);
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final Localizacion localizacion = new Localizacion();
        localizacion.setMainActivity(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) localizacion);



        delimitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    lat=location.getLatitude();
                    lon=location.getLongitude();
                    flag = 1;
                }
                catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
                    builder.setMessage("No se ha podido establecer conexion con el GPS, por favor dirijase a un lugar despejado y espere un momento").setNegativeButton("Continuar", null).create().show();
                    flag = 0;
                }
                if(flag == 1){
                String descripcion = solicitardescripcion(location.getLatitude(),location.getLongitude());
                puntos.add(new Punto(location.getLatitude(),location.getLongitude(),descripcion));

                if(puntos.size()>1){
                BuiltPolylone(toLatlong(puntos));
                lat=null;
                lon=null;
                }


                Snackbar.make(view, "Punto agregado", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }}
        });

        delimitarfin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject JsonResponse = null;
                        try {
                            JsonResponse = new JSONObject(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            boolean success = JsonResponse.getBoolean("success");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


               /* try{

                    lat=location.getLatitude();
                    lon=location.getLongitude();
                    flag = 1;
                }
                catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
                    builder.setMessage("No se ha podido establecer conexion con el GPS, por favor dirijase a un lugar despejado y espere un momento").setNegativeButton("Continuar", null).create().show();
                    flag = 0;
                }*/
                //if(flag == 1){
                    ///puntos.add(puntos.get(0));
                    BuiltPolylone(toLatlong(prueba));
                    lat=null;
                    lon=null;
               // }

                NavigationRequest request = new NavigationRequest(listener,prueba,r.getId());
                RequestQueue queue = Volley.newRequestQueue(NavigationActivity.this);
                queue.add(request);
            }


        });


        acceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    lat=location.getLatitude();
                    lon=location.getLongitude();
                    flag = 1;
                }
                catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(NavigationActivity.this);
                    builder.setMessage("No se ha podido establecer conexion con el GPS, por favor dirijase a un lugar despejado y espere un momento").setNegativeButton("Continuar", null).create().show();
                    flag = 0;
                }
                if(flag == 1){
                    via.add(new Punto(location.getLatitude(),location.getLongitude(),""));
                    DrawRoad(via);
                }

            }
        });

        elemento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this,Objetoactivity.class);
                NavigationActivity.this.startActivity(intent);
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        TextView nombrerancheria = (TextView) header.findViewById(R.id.tvRancheria);
        nombrerancheria.setText("Rancheria "+r.getNombre());



    }

    public void setLocation(Location loc){
        location=loc;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            delimitar.setVisibility(View.VISIBLE);
            delimitarfin.setVisibility(View.VISIBLE);
            elemento.setVisibility(View.INVISIBLE);
            encuesta.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_gallery) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            elemento.setVisibility(View.VISIBLE);
            encuesta.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_slideshow) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            elemento.setVisibility(View.INVISIBLE);
            encuesta.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_manage) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            elemento.setVisibility(View.INVISIBLE);
            encuesta.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_share) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            elemento.setVisibility(View.INVISIBLE);
            encuesta.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
        } else if (id == R.id.nav_send) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            elemento.setVisibility(View.INVISIBLE);
            encuesta.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        map.setMyLocationEnabled(true);


    }
    private void setMarkerRed(LatLng position, String titulo, String info) {

        Marker myMaker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)
                .snippet(info)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
    }

    public ArrayList<LatLng> toLatlong(ArrayList<Punto> puntos){
        ArrayList<LatLng> p = new ArrayList<>();
        for (int i = 0; i <puntos.size() ; i++) {
            p.add(new LatLng(puntos.get(i).getLatitud(),puntos.get(i).getLongitud()));
        }
        return p;
    }

    public void DrawRoad(ArrayList<Punto> acceso){
        PolylineOptions polyLine = new PolylineOptions();
        polyLine.width(2);
        polyLine.color(Color.GREEN);
        polyLine.geodesic(true);
        for (int i = 0; i < acceso.size(); i++) {
            polyLine.add(new LatLng(acceso.get(i).getLatitud(), acceso.get(i).getLongitud()));
        }

        map.addPolyline(polyLine);
    }


    public void BuiltPolylone (ArrayList<LatLng> latlong){
        PolylineOptions rectOptions = new PolylineOptions();
        for (int i = 0; i < latlong.size(); i++) {

            rectOptions.add(latlong.get(i));
        }
        drawPolilyne(rectOptions);


    }
    private void drawPolilyne(PolylineOptions options){
        options.geodesic(true);
        map.addPolyline(options);

    }

    private String solicitardescripcion(final double lati, final double longi){
        final String[] m_Text = {""};
        final AlertDialog.Builder[] builder = {new AlertDialog.Builder(this)};
        builder[0].setTitle("Ingrese descripcion");

// Set up the input
        final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder[0].setView(input);

// Set up the buttons
        builder[0].setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text[0] = input.getText().toString();
                setMarkerRed(new LatLng(lati,longi),input.getText().toString(),"");
            }
        });
        builder[0].setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder[0].show();
        return String.valueOf(input);
    }


}





