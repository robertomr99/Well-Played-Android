package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class unirseEquipo extends AppCompatActivity {

    Equipo oEquipo;
    public static final String sNombreUser = MainActivity.oUsuario.getsUser();
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unirse_equipo);
        swipeRefreshLayout = findViewById(R.id.swipe);
        mostrarEquiposQueNoTieneUser();
        swipeRefreshLayout.setColorSchemeResources(R.color.AzulApp,R.color.black);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.AmarilloApp);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new waitReload().execute();

            }

        });

    }

    private class waitReload extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(500);
                mostrarEquiposQueNoTieneUser();
                swipeRefreshLayout.setRefreshing(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


        public void mostrarEquiposQueNoTieneUser() {
        String sUrl = Utils.hosting + "equipo-usuario/EquipoQueNoTieneUser.php?txtUsuario="+sNombreUser;
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoEquipos.lstEquipos = new Gson().fromJson(s, new TypeToken<List<Equipo>>() {
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
        RecyclerView Rv = findViewById(R.id.recyclerViewUnirseEquipo);
        Rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        UnirseEquipoAdapter adaptador = new UnirseEquipoAdapter(this);
        Rv.setAdapter(adaptador);
        adaptador.setOnClickListener(v -> {
            ListadoEquipos.iEquipoSelected = Rv.getChildAdapterPosition(v);
            oEquipo = ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected);

        });

    }

}