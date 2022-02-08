package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.wellplayed.model.Usuario;
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class UnirsePartido extends AppCompatActivity {

    RecyclerView Rv;
    int iTipo;
    public static String sNombreUser;


    public static AlertDialog.Builder dialogBuilder;
    public static AlertDialog dialog;
    public static Button btnSI, btnNO;
    public static UnirsePartido context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unirse_partido);
        sNombreUser = MainActivity.oUsuario.getsUser();
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

    public void updatePartidoEquipo(Partido_Equipo oPartidoEquipo) {
        String sUrl;

        if (oPartidoEquipo.getsNombreEquipo1().isEmpty() & oPartidoEquipo.getsFotoEquipo1().isEmpty()) {
            sUrl = Utils.hosting + "partidos/partido_equipo/updatePartidoEquipo1.php?txtNombre=" + sNombreUser + "&txtIdPartido=" + oPartidoEquipo.getiIdPartido();
        } else {
            sUrl = Utils.hosting + "partidos/partido_equipo/updatePartidoEquipo2.php?txtNombre=" + sNombreUser + "&txtIdPartido=" + oPartidoEquipo.getiIdPartido();
        }

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        mostrarData(getApplicationContext());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }



    public void PopupUnirse(Partido_Equipo oPartidoEquipo) {

        dialogBuilder = new AlertDialog.Builder(this);
        final View PopupUnirsePartido = getLayoutInflater().inflate(R.layout.popup_unirsepartido, null);
        btnSI = (Button) PopupUnirsePartido.findViewById(R.id.btnSiUnirse);
        btnNO = (Button) PopupUnirsePartido.findViewById(R.id.btnNoUnirse);

        dialogBuilder.setView(PopupUnirsePartido);
        dialog = dialogBuilder.create();
        dialog.setCancelable(false); // Para que tenga que pulsar una opcion si o si (btn atras del movil)
        dialog.setCanceledOnTouchOutside(false); // Para que tenga que pulsar una opcion si o si (en la pantalla)
        dialog.show();

        btnSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePartidoEquipo(oPartidoEquipo);
                Toast.makeText(getApplicationContext(), "Este equipo se ha unido al partido", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

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
                        ListadoPartidosUsuario.lstPartidoUsuario = new Gson().fromJson(s, new TypeToken<List<Partido_Usuario>>() {
                        }.getType());
                        mostrarData(getApplicationContext());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    public void updatePartidoUsuario(Partido_Usuario oPartidoUsuario) {
        String sUrl;

        if (oPartidoUsuario.getsFotoJugador1().isEmpty() & oPartidoUsuario.getsNombreJugador1().isEmpty()) {
            sUrl = Utils.hosting + "partidos/partido_usuario/updatePartidoUsuario1.php?txtNombre=" + sNombreUser + "&txtIdPartido=" + oPartidoUsuario.getiIdPartido();
        } else {
            sUrl = Utils.hosting + "partidos/partido_usuario/updatePartidoUsuario2.php?txtNombre=" + sNombreUser + "&txtIdPartido=" + oPartidoUsuario.getiIdPartido();
        }

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        mostrarData(getApplicationContext());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    public void PopupUnirse(Partido_Usuario oPartidoUsuario) {

        dialogBuilder = new AlertDialog.Builder(this);
        final View PopupUnirsePartido = getLayoutInflater().inflate(R.layout.popup_unirsepartido, null);
        btnSI = (Button) PopupUnirsePartido.findViewById(R.id.btnSiUnirse);
        btnNO = (Button) PopupUnirsePartido.findViewById(R.id.btnNoUnirse);

        dialogBuilder.setView(PopupUnirsePartido);
        dialog = dialogBuilder.create();
        dialog.setCancelable(false); // Para que tenga que pulsar una opcion si o si (btn atras del movil)
        dialog.setCanceledOnTouchOutside(false); // Para que tenga que pulsar una opcion si o si (en la pantalla)
        dialog.show();

        btnSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePartidoUsuario(oPartidoUsuario);
                Toast.makeText(getApplicationContext(), "Este usuario se ha unido al partido", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    private void mostrarData(Context context) {
        Rv.setLayoutManager(new LinearLayoutManager(context));
        UnirsePartidosAdapter adaptador = new UnirsePartidosAdapter(context, new UnirsePartidosAdapter.UnirsePartidosAdapterInterface() {
            @Override
            public void unirseAlPartidoEquipo(Partido_Equipo oPartidoEquipo) {
                PopupUnirse(oPartidoEquipo);
            }

            @Override
            public void unirseAlPartidoUsuario(Partido_Usuario oPartidoUsuario) {
                PopupUnirse(oPartidoUsuario);
            }



        }, iTipo, "");

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