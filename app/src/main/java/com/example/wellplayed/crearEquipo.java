package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class crearEquipo extends AppCompatActivity {

    Producto oProducto;
    public static ImageView imgViewEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);
        imgViewEquipo = findViewById(R.id.imgViewEquipo);
        cambiarLogo();


        cancelar();
    }

    public void cancelar(){

        // Para volver de una actividad a la ventana anterior.

        findViewById(R.id.btnCancelar).setOnClickListener( v ->{
            super.onBackPressed();
        });
    }

    public void cambiarLogo(){

        imgViewEquipo.setOnClickListener(v->{
            Intent i = new Intent(this,Logos.class);
            startActivity(i);
        });
    }

    public void traerLogos() {
        String sUrl = Utils.hosting + "productos/get-productos.php?txtCategoria="+1;
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoProductos.lstProductos = new Gson().fromJson(s, new TypeToken<List<Producto>>() {
                        }.getType());
                        mostrarData();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    private void mostrarData() {
        RecyclerView Rv = findViewById(R.id.recyclerViewLogos);
        Rv.setLayoutManager(new GridLayoutManager(this,3));
        JuegosAdapter adaptador = new JuegosAdapter(this);
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);
        adaptador.setOnClickListener(v -> {
            ListadoProductos.iProductoSelected = Rv.getChildAdapterPosition(v);
            oProducto = ListadoProductos.lstProductos.get(ListadoProductos.iProductoSelected);
            Toast.makeText(this, "El juego se ha añadido correctamente.", Toast.LENGTH_SHORT).show();



        });

    }

}