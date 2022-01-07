package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class addJuego extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_juego);
        mostrarJuegosQueNoTieneUser();
    }

    public void mostrarJuegosQueNoTieneUser() {
        String sUrl = Utils.hosting + "JuegoQueNoTieneUser.php?txtUsuario="+MainActivity.oUsuario.getsUser();
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoJuegos.lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
                        }.getType());
                        if(ListadoJuegos.lstJuegos.size() <= 0){
                         mostrarJuegos();
                        }else{
                            mostrarData();
                        }
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    public void mostrarJuegos() {
        String sUrl = Utils.hosting + "get-juego.php";
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoJuegos.lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
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
        RecyclerView Rv = findViewById(R.id.recyclerViewAddJuego);
        Rv.setLayoutManager(new GridLayoutManager(this,2));
        JuegosAdapter adaptador = new JuegosAdapter(this);
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);

       adaptador.setOnClickListener(v -> {
            ListadoJuegos.iJuegoSelected = Rv.getChildAdapterPosition(v);
           Toast.makeText(this, "El juego se ha añadido correctamente.", Toast.LENGTH_SHORT).show();
           onBackPressed();
       });
    }

}