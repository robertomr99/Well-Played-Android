package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    final long EXECUTION_TIME = 2000; // 1 minuto
    private Handler handler = new Handler();
    private Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unirse_equipo);
        swipeRefreshLayout = findViewById(R.id.swipe);
        mostrarEquiposQueNoTieneUser();
        swipeRefreshLayout.setColorSchemeResources(R.color.GrisApp);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.AzulApp);
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


    public static void unirseEquipo(Context context, int iIdEquipo, int iIdUsuario) {
        String sUrl = Utils.hosting + "equipo/ins-equipo-usuario.php?txtEquipo="+iIdEquipo+"&txtUsuario="+iIdUsuario+"&txtCreador="+false;
        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(context, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context,"Equipo creado con exito", Toast.LENGTH_SHORT).show();
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