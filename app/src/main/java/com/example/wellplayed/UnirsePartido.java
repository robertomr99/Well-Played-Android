package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Equipo_Juego;
import com.example.wellplayed.model.Equipo_Usuario;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Partido_Equipo;
import com.example.wellplayed.model.Partido_Usuario;
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class UnirsePartido extends AppCompatActivity {


    RecyclerView Rv;
    int iTipo;
    public static final String sNombreUser = MainActivity.oUsuario.getsUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unirse_partido);
        intentPartidos();
        Rv = findViewById(R.id.recyclerViewUnirsePartidos);

        if (iTipo == 1) {
            JuegosQueTieneEquipo();
        } else {
            JuegosQueTieneUsuario();
        }
    }


    // --------- EQUIPO -----------

    public void JuegosQueTieneEquipo() {

        String sUrl = Utils.hosting + "partidos/partido_equipo/JuegosQueTieneEquipo.php?txtNombre=" + sNombreUser;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<Integer> aJuegos = new ArrayList<Integer>();
                        List<Juego> lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
                        }.getType());

                        for (Juego j : lstJuegos) {
                            aJuegos.add(j.getiIdJuego());
                        }

                        listarPartidosJuegoEquipo(aJuegos);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void listarPartidosJuegoEquipo(ArrayList<Integer> aJuegos) {
        String sUrl;

        if (aJuegos.size() == 1) {
            sUrl = Utils.hosting + "partidos/partido_equipo/lst-partidos-juegosequipo.php?txtJuego1=" + aJuegos.get(0);
        } else if (aJuegos.size() == 2) {
            sUrl = Utils.hosting + "partidos/partido_equipo/lst-partidos-juegosequipo.php?txtJuego1=" + aJuegos.get(0) + "&txtJuego2=" + aJuegos.get(1);
        } else if (aJuegos.size() == 3) {
            sUrl = Utils.hosting + "partidos/partido_equipo/lst-partidos-juegosequipo.php?txtJuego1=" + aJuegos.get(0) + "&txtJuego2=" + aJuegos.get(1) + "&txtJuego3=" + aJuegos.get(2);
        } else {
            sUrl = Utils.hosting + "partidos/partido_equipo/lst-partidos-juegosequipo.php?txtJuego1=" + aJuegos.get(0) + "&txtJuego2=" + aJuegos.get(1) + "&txtJuego3=" + aJuegos.get(2) + "&txtJuego4=" + aJuegos.get(3);
        }

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        ListadoPartidosEquipo.lstPartidoEquipo = new Gson().fromJson(s, new TypeToken<List<Partido_Equipo>>() {
                        }.getType());
                        mostrarData(getApplicationContext());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    // --------- USUARIO -----------


    public void JuegosQueTieneUsuario() {

        String sUrl = Utils.hosting + "partidos/partido_usuario/JuegosQueTieneUsuario.php?txtNombre=" + sNombreUser;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        ArrayList<Integer> aJuegos = new ArrayList<Integer>();
                        List<Juego> lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
                        }.getType());

                        Log.d("ASDASDASDASDASDASDASDASDASDASDASD", lstJuegos.toString());

                        for (Juego j : lstJuegos) {
                            aJuegos.add(j.getiIdJuego());
                        }

                        listarPartidosJuegoUsuario(aJuegos);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void listarPartidosJuegoUsuario(ArrayList<Integer> aJuegos) {
        String sUrl;

        if (aJuegos.size() == 1) {
            sUrl = Utils.hosting + "partidos/partido_usuario/lst-partidos-juegosusuario.php?txtJuego1=" + aJuegos.get(0);
        } else if (aJuegos.size() == 2) {
            sUrl = Utils.hosting + "partidos/partido_usuario/lst-partidos-juegosusuario.php?txtJuego1=" + aJuegos.get(0) + "&txtJuego2=" + aJuegos.get(1);
        } else if (aJuegos.size() == 3) {
            sUrl = Utils.hosting + "partidos/partido_usuario/lst-partidos-juegosusuario.php?txtJuego1=" + aJuegos.get(0) + "&txtJuego2=" + aJuegos.get(1) + "&txtJuego3=" + aJuegos.get(2);
        } else {
            sUrl = Utils.hosting + "partidos/partido_usuario/lst-partidos-juegosusuario.php?txtJuego1=" + aJuegos.get(0) + "&txtJuego2=" + aJuegos.get(1) + "&txtJuego3=" + aJuegos.get(2) + "&txtJuego4=" + aJuegos.get(3);
        }

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        ListadoPartidosUsuario.lstPartidoUsuario= new Gson().fromJson(s, new TypeToken<List<Partido_Usuario>>() {
                        }.getType());
                        mostrarData(getApplicationContext());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void mostrarData(Context context) {
        Rv.setLayoutManager(new LinearLayoutManager(context));
        UnirsePartidosAdapter adaptador = new UnirsePartidosAdapter(context, new UnirsePartidosAdapter.UnirsePartidosAdapterInterface() {
            @Override
            public void prueba() {

            }
        }, iTipo);

        Rv.setAdapter(adaptador);
    }


    private void intentPartidos() {
        try {
            if (getIntent().hasExtra("Tipo")) {
                iTipo = getIntent().getIntExtra("Tipo", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}