package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

public class Logos extends AppCompatActivity {

    Producto oProducto = new Producto();
    Button btnAceptarLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logos);
        traerLogos();


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
                        Log.d("listaAvatar",ListadoProductos.lstProductos.toString());
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
        LogosAdapter adaptador = new LogosAdapter(this);
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);
        adaptador.setOnClickListener(v -> {
            ListadoProductos.iProductoSelected = Rv.getChildAdapterPosition(v);
            oProducto = ListadoProductos.lstProductos.get(ListadoProductos.iProductoSelected);
            Intent i = new Intent();
            i.putExtra("producto", oProducto);
            setResult(Activity.RESULT_OK,i);
            finish();
        });




    }

    private void sendBackData() {
        Intent i = new Intent();
        i.putExtra("producto", oProducto);
        setResult(Activity.RESULT_OK,i);
        Toast.makeText(this, "El avatar se ha a??adido correctamente.", Toast.LENGTH_SHORT).show();
        finish();
    }


}