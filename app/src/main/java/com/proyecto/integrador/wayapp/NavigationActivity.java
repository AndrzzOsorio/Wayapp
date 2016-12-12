package com.proyecto.integrador.wayapp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    private GoogleMap map;
    Location location;
    ArrayList<Punto> puntos = new ArrayList<>();
    ArrayList<Punto> via = new ArrayList<>();
    FloatingActionButton delimitar;

    FloatingActionButton encuesta;
    FloatingActionButton delimitarfin;
    FloatingActionButton acceso;
    FloatingActionButton loadn;
    FloatingActionButton caminofin;
    int flag = 0;
    Double lat;
    Double lon;
    ArrayList<Punto> prueba = new ArrayList<>();
    Rancheria r;
    View casalayout;
    View escuelalayout;
    View corrallayout;
    View cultivolayout;
    View encuestalayout;

//controles casa
    Button agregarc;
    EditText cantidadhabitacionescasa;
    EditText observacion;
    CheckBox cocina;

    //Controles escuela
    EditText nombreescuela;
    EditText cantidadestudiantes;
    Button agregarE;

    //controles corral

    EditText cantidadanimales;
    Button agregarco;

    //controles cultivo
    EditText dueno;
    EditText area;
    Button agregarcu;

    //controles encuesta
    EditText encnombre;
    EditText enccedula;
    EditText encedad;
    EditText encgenero;
    EditText enctelefono;
    EditText enccargo;
    EditText encdescripcion;
    Button agregarencu;

    ArrayList<Casa> casas;
    ArrayList<Escuela> escuelas;
    ArrayList<Cultivo> cultivos;
    ArrayList<Corral> corrales;
    ArrayList<encuesta> encuestas;


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
       /* prueba.add(new Punto(5.049300019378771,-75.47945895851801,"descripcion"));
        prueba.add(new Punto(5.049255319484387,-75.47953471146438,"descripcion1"));
        prueba.add(new Punto(5.049186817507857,-75.47945895851801,"descripcion2"));
        prueba.add(new Punto(5.049300019378771,-75.47945895851801,"descripcion"));*/

        r = (Rancheria) intent.getSerializableExtra("rancheria");

      //  Listas de elementos que pertenecen a las rancherias
        casas = new ArrayList<>();
        escuelas = new ArrayList<>();
        cultivos = new ArrayList<>();
        corrales = new ArrayList<>();
        encuestas = new ArrayList<>();

        //paneles de vistas
        casalayout =  findViewById(R.id.casalayout);
        escuelalayout =  findViewById(R.id.escuelalayout);
        corrallayout = findViewById(R.id.corrallayout);
        cultivolayout = findViewById(R.id.cultivolayout);
        encuestalayout = findViewById(R.id.encuestalayout);


        delimitar = (FloatingActionButton) findViewById(R.id.delimitar);
        encuesta = (FloatingActionButton) findViewById(R.id.encuesta);
        delimitarfin = (FloatingActionButton) findViewById(R.id.delimitarfin);
        acceso = (FloatingActionButton) findViewById(R.id.acceso);
        loadn = (FloatingActionButton) findViewById(R.id.loadn);
        caminofin = (FloatingActionButton) findViewById(R.id.caminofin);

        //definicion de los controles de la casa
        agregarc = (Button) findViewById(R.id.btnagregarC);
        List<String> spinnerArraycasa =  new ArrayList<String>();
        spinnerArraycasa.add("Madera");
        spinnerArraycasa.add("Arcilla");
        spinnerArraycasa.add("Jaguey");
        spinnerArraycasa.add("Guadua");
        spinnerArraycasa.add("Ladrillo");
        ArrayAdapter<String> adaptercasa = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArraycasa);

        adaptercasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        List<String> spinnerArrayhabitantes =  new ArrayList<String>();
        spinnerArrayhabitantes.add("1");
        spinnerArrayhabitantes.add("2");
        spinnerArrayhabitantes.add("3");
        spinnerArrayhabitantes.add("4");
        spinnerArrayhabitantes.add("5");
        spinnerArrayhabitantes.add("6");
        spinnerArrayhabitantes.add("7");
        spinnerArrayhabitantes.add("8");
        spinnerArrayhabitantes.add("9");
        spinnerArrayhabitantes.add("10");
        ArrayAdapter<String> adapterhabitantes = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArrayhabitantes);

        adaptercasa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner sItemscasa = (Spinner) findViewById(R.id.spinnermaterialcasa);
        final Spinner sItemhabitantes = (Spinner) findViewById(R.id.spinnercantidadpersonas);
        sItemscasa.setAdapter(adaptercasa);
        sItemhabitantes.setAdapter(adapterhabitantes);

        cantidadhabitacionescasa= (EditText) findViewById(R.id.txtcantidadh);

        observacion = (EditText) findViewById(R.id.observaciones);
        cocina = (CheckBox) findViewById(R.id.cbcocina);

        //definicion de los controles de la escuela
        List<String> spinnerArrayescuela =  new ArrayList<String>();
        spinnerArrayescuela.add("Madera");
        spinnerArrayescuela.add("Arcilla");
        spinnerArrayescuela.add("Jaguey");
        spinnerArrayescuela.add("Guadua");
        spinnerArrayescuela.add("Ladrillo");
        ArrayAdapter<String> adapterescuela = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArrayescuela);

        adapterescuela.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItemmaterialescuela = (Spinner) findViewById(R.id.spinnermaterialescuela);
        sItemmaterialescuela.setAdapter(adapterescuela);

        nombreescuela = (EditText) findViewById(R.id.txtnombre);
        cantidadestudiantes = (EditText) findViewById(R.id.txtcantidadEst);
        agregarE = (Button) findViewById(R.id.btnAgregarE);

        //definicion de los controles del corral
        List<String> spinnerArrayanimal =  new ArrayList<String>();
        spinnerArrayanimal.add("Chivo");
        spinnerArrayanimal.add("Cabra");
        spinnerArrayanimal.add("Oveja");
        spinnerArrayanimal.add("Vaca");
        spinnerArrayanimal.add("Caballo");
        spinnerArrayanimal.add("Cerdo");
        spinnerArrayanimal.add("Gallina");
        spinnerArrayanimal.add("Pato");
        ArrayAdapter<String> adapteranimal = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArrayanimal);

        adapteranimal.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sIanimal = (Spinner) findViewById(R.id.spinneranimal);
        sIanimal.setAdapter(adapteranimal);
        cantidadanimales= (EditText) findViewById(R.id.txtcantidadA);
        agregarco = (Button) findViewById(R.id.btnagregarAnimal);

        //definicion de los controles del cultivo
        List<String> spinnerArrayfruto =  new ArrayList<String>();
        spinnerArrayfruto.add("Frijol");
        spinnerArrayfruto.add("Habichuela");
        spinnerArrayfruto.add("Papa");
        spinnerArrayfruto.add("Platano");

        ArrayAdapter<String> adapterfruto = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArrayfruto);

        adapterfruto.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sIfruto = (Spinner) findViewById(R.id.spinnerfruto);
        sIfruto.setAdapter(adapterfruto);
        dueno = (EditText) findViewById(R.id.txtdueno);
        area = (EditText) findViewById(R.id.txtAreaC);
        agregarcu = (Button) findViewById(R.id.btnagregarcultivo);

        //definicion de los controles de la encuesta
        encnombre = (EditText) findViewById(R.id.encNombre);
        enccedula = (EditText) findViewById(R.id.encCedula);
        enccargo = (EditText) findViewById(R.id.encCargo);
        enctelefono = (EditText) findViewById(R.id.encTelefono);
        encedad = (EditText) findViewById(R.id.encEdad);
        encdescripcion = (EditText) findViewById(R.id.encDescripcion);
        encgenero = (EditText) findViewById(R.id.encGenero);
        agregarencu = (Button) findViewById(R.id.btnEncuesta);


        //metodo para la geolocalizacion
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        final Localizacion localizacion = new Localizacion();
        localizacion.setNavActivity(this);
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

        caminofin.setOnClickListener(new View.OnClickListener() {
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
                            if(success){

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                NavigationRequest request = new NavigationRequest(listener,r.getId(),prueba);
                RequestQueue queue = Volley.newRequestQueue(NavigationActivity.this);
                queue.add(request);

            }
        });



        

        loadn.setOnClickListener(new View.OnClickListener() {
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
                            if(success){

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                NavigationRequest request = new NavigationRequest(listener,r.getId());
                RequestQueue queue = Volley.newRequestQueue(NavigationActivity.this);
                queue.add(request);

            }
        });


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
                    puntos.add(puntos.get(0));
                    BuiltPolylone(toLatlong(puntos));
                    lat=null;
                    lon=null;
                }

                NavigationRequest request = new NavigationRequest(listener,puntos,r.getId());
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



        agregarc.setOnClickListener(new View.OnClickListener() {

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
                if(flag ==1){

                    Casa c = null;
                    if(cocina.isChecked()){
                        c = new Casa(location.getLatitude(),location.getLongitude(),sItemscasa.getSelectedItem().toString(),"Si tiene",cantidadhabitacionescasa.getText().toString(),sItemhabitantes.getSelectedItem().toString(),observacion.getText().toString());
                    }else{
                        c = new Casa(location.getLatitude(),location.getLongitude(),sItemscasa.getSelectedItem().toString(),"No tiene",cantidadhabitacionescasa.getText().toString(),sItemhabitantes.getSelectedItem().toString(),observacion.getText().toString());
                    }

              casas.add(c);
              for (int i = 0; i<casas.size();i++){
                  setMarkerCasa(new LatLng(casas.get(i).getLatitud(),casas.get(i).getLongitud()),"Casa",casas.get(i).ToInformation());
              }
                casalayout.setVisibility(View.INVISIBLE);
                supportMapFragment.getView().setVisibility(View.VISIBLE);


                    cantidadhabitacionescasa.setText("");
                    observacion.setText("");
                }

            }
        });

        agregarE.setOnClickListener(new View.OnClickListener() {
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
                if(flag ==1) {
                    Escuela e = new Escuela(location.getLatitude(), location.getLongitude(), sItemmaterialescuela.getSelectedItem().toString(), cantidadestudiantes.getText().toString(), nombreescuela.getText().toString());
                    escuelas.add(e);
                    for (int i = 0; i < escuelas.size(); i++) {
                        setMarkerEscuela(new LatLng(escuelas.get(i).getLatitud(), escuelas.get(i).getLongitud()), "Centro Educativo", escuelas.get(i).ToInformation());
                    }
                    escuelalayout.setVisibility(View.INVISIBLE);
                    supportMapFragment.getView().setVisibility(View.VISIBLE);

                    nombreescuela.setText("");
                    cantidadestudiantes.setText("");

                }
            }

        });

        agregarco.setOnClickListener(new View.OnClickListener() {
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
                if(flag ==1){
               Corral co = new Corral(location.getLatitude(),location.getLongitude(),sIanimal.getSelectedItem().toString(),cantidadanimales.getText().toString());
               corrales.add(co);
                for (int i = 0; i<corrales.size();i++){
                    setMarkerCorral(new LatLng(corrales.get(i).getLatitud(),corrales.get(i).getLongitud()),"Corral ganadero",corrales.get(i).ToInformation());
                }
                corrallayout.setVisibility(View.INVISIBLE);
                supportMapFragment.getView().setVisibility(View.VISIBLE);
                    cantidadanimales.setText("");
                }
            }
        });

        agregarcu.setOnClickListener(new View.OnClickListener() {
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
                if(flag ==1){
               Cultivo cu = new Cultivo(location.getLatitude(),location.getLongitude(),dueno.getText().toString(),sIanimal.getSelectedItem().toString(),area.getText().toString());
                cultivos.add(cu);
                for (int i = 0; i<cultivos.size();i++){
                    setMarkerCultivo(new LatLng(cultivos.get(i).getLatitud(),cultivos.get(i).getLongitud()),"Cultivo agricola",cultivos.get(i).ToInformation());
                }
                cultivolayout.setVisibility(View.INVISIBLE);
                supportMapFragment.getView().setVisibility(View.VISIBLE);
                    dueno.setText("");
                    area.setText("");
                }
            }
        });

        agregarencu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    encuesta Encuesta = new encuesta(encnombre.getText().toString(),enccedula.getText().toString(),encedad.getText().toString(),enctelefono.getText().toString(),encgenero.getText().toString(),enccargo.getText().toString(),encdescripcion.getText().toString());
                    encuestas.add(Encuesta);
                    Log.i("Encuesta",Encuesta.ImprimirEncuesta());
                    encuestalayout.setVisibility(View.INVISIBLE);
                    supportMapFragment.getView().setVisibility(View.VISIBLE);
                    encnombre.setText("");
                    enccedula.setText("");
                    enccargo.setText("");
                    enctelefono.setText("");
                    encedad.setText("");
                    encdescripcion.setText("");
                    encgenero.setText("");

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

        if (id == R.id.delimitacion) {
            delimitar.setVisibility(View.VISIBLE);
            delimitarfin.setVisibility(View.VISIBLE);
            encuestalayout.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
            loadn.setVisibility(View.INVISIBLE);
            casalayout.setVisibility(View.INVISIBLE);
            escuelalayout.setVisibility(View.INVISIBLE);
            corrallayout.setVisibility(View.INVISIBLE);
            cultivolayout.setVisibility(View.INVISIBLE);
            supportMapFragment.getView().setVisibility(View.VISIBLE);

        } else if (id == R.id.fin_delimitacion) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            encuestalayout.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.VISIBLE);
            loadn.setVisibility(View.VISIBLE);
            casalayout.setVisibility(View.INVISIBLE);
            escuelalayout.setVisibility(View.INVISIBLE);
            corrallayout.setVisibility(View.INVISIBLE);
            cultivolayout.setVisibility(View.INVISIBLE);
            supportMapFragment.getView().setVisibility(View.VISIBLE);

        } else if (id == R.id.acasa) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            encuestalayout.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
            loadn.setVisibility(View.INVISIBLE);
            casalayout.setVisibility(View.VISIBLE);
            escuelalayout.setVisibility(View.INVISIBLE);
            corrallayout.setVisibility(View.INVISIBLE);
            cultivolayout.setVisibility(View.INVISIBLE);
            supportMapFragment.getView().setVisibility(View.INVISIBLE);

        } else if (id == R.id.aescuela) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            encuestalayout.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
            loadn.setVisibility(View.INVISIBLE);
            casalayout.setVisibility(View.INVISIBLE);
            escuelalayout.setVisibility(View.VISIBLE);
            corrallayout.setVisibility(View.INVISIBLE);
            cultivolayout.setVisibility(View.INVISIBLE);
            supportMapFragment.getView().setVisibility(View.INVISIBLE);

        } else if (id == R.id.acorral) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            encuestalayout.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
            loadn.setVisibility(View.INVISIBLE);
            casalayout.setVisibility(View.INVISIBLE);
            escuelalayout.setVisibility(View.INVISIBLE);
            corrallayout.setVisibility(View.VISIBLE);
            cultivolayout.setVisibility(View.INVISIBLE);
            supportMapFragment.getView().setVisibility(View.INVISIBLE);

        } else if (id == R.id.acultivo) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            encuestalayout.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
            loadn.setVisibility(View.INVISIBLE);
            casalayout.setVisibility(View.INVISIBLE);
            escuelalayout.setVisibility(View.INVISIBLE);
            corrallayout.setVisibility(View.INVISIBLE);
            cultivolayout.setVisibility(View.VISIBLE);
            supportMapFragment.getView().setVisibility(View.INVISIBLE);

        }
        else if (id == R.id.aencuesta) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            encuestalayout.setVisibility(View.VISIBLE);
            acceso.setVisibility(View.INVISIBLE);
            loadn.setVisibility(View.INVISIBLE);
            casalayout.setVisibility(View.INVISIBLE);
            escuelalayout.setVisibility(View.INVISIBLE);
            corrallayout.setVisibility(View.INVISIBLE);
            cultivolayout.setVisibility(View.INVISIBLE);
            supportMapFragment.getView().setVisibility(View.INVISIBLE);

        }
        else if (id == R.id.refrescar) {
            delimitar.setVisibility(View.INVISIBLE);
            delimitarfin.setVisibility(View.INVISIBLE);
            encuestalayout.setVisibility(View.INVISIBLE);
            acceso.setVisibility(View.INVISIBLE);
            loadn.setVisibility(View.INVISIBLE);
            casalayout.setVisibility(View.INVISIBLE);
            escuelalayout.setVisibility(View.INVISIBLE);
            corrallayout.setVisibility(View.INVISIBLE);
            cultivolayout.setVisibility(View.INVISIBLE);
            supportMapFragment.getView().setVisibility(View.VISIBLE);

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



    private void setMarkerCasa(LatLng position, String titulo, String info) {

        Bitmap bmp = getMarkerBitmapFromView(R.drawable.casa,0);
        Marker myMaker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)
                .snippet(info)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp)));
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(NavigationActivity.this);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(NavigationActivity.this);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(NavigationActivity.this);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }

    private void setMarkerEscuela(LatLng position, String titulo, String info) {
        Bitmap bmp = getMarkerBitmapFromView(R.drawable.escuela,1);
        Marker myMaker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)
                .snippet(info)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp)));
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(NavigationActivity.this);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(NavigationActivity.this);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(NavigationActivity.this);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }

    private void setMarkerCorral(LatLng position, String titulo, String info) {
        Bitmap bmp = getMarkerBitmapFromView(R.drawable.corral,2);
        Marker myMaker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)
                .snippet(info)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp)));
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(NavigationActivity.this);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(NavigationActivity.this);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(NavigationActivity.this);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
    }

    private void setMarkerCultivo(LatLng position, String titulo, String info) {
        Bitmap bmp = getMarkerBitmapFromView(R.drawable.cultivo,3);
        Marker myMaker = map.addMarker(new MarkerOptions()
                .position(position)
                .title(titulo)
                .snippet(info)
                .icon(BitmapDescriptorFactory.fromBitmap(bmp)));
        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(NavigationActivity.this);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(NavigationActivity.this);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(NavigationActivity.this);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
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
        return m_Text[0].toString();
    }

    private Bitmap getMarkerBitmapFromView(@DrawableRes int resId, int elemento) {
        View customMarkerView = null;
        ImageView markerImageView=null;
        switch (elemento){
            case 0: customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.imagen_casa, null);
                markerImageView = (ImageView) customMarkerView.findViewById(R.id.icono_casa);
                break;
            case 1: customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.imagen_escuela, null);
                markerImageView = (ImageView) customMarkerView.findViewById(R.id.icono_escuela);
                break;
            case 2: customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.imagen_corral, null);
                markerImageView = (ImageView) customMarkerView.findViewById(R.id.icono_corral);
                break;
            case 3: customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.imagen_cultivo, null);
                markerImageView = (ImageView) customMarkerView.findViewById(R.id.icono_cultivo);
        }
        markerImageView.setImageResource(resId);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }




}





