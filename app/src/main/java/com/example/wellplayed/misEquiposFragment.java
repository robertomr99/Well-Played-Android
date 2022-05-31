package com.example.wellplayed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Equipo_Usuario;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Usuario;
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class misEquiposFragment extends Fragment {

    RecyclerView rv;
    EquiposAdapter adaptador;
    public static String sNombreUser;
    public static Equipo_Usuario oEquipo_Usuario;
    public static Equipo oEquipo;
    public static List<Equipo> lstEquiposComparativa = new ArrayList<Equipo>();

    public misEquiposFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainActivity.ocultarBienvenida();
        View view = inflater.inflate(R.layout.fragment_mis_equipos, container, false);
        rv = view.findViewById(R.id.recyclerViewEquipos);
        lstEquiposComparativa.clear();

        sNombreUser = MainActivity.oUsuario.getsUser();
        crearEquipo(view);
        unirseEquipo(view);

        new Thread(new Runnable() {
            public void run() {
                try {
                    while (true) {
                        mostrarEquipos();
                        Thread.sleep(60000);
                    }

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mostrarEquipos();
    }

    public void mostrarEquipos() {
        String sUrl = Utils.hosting + "equipo-usuario/EquipoQueSiTieneUser.php?txtUsuario=" + sNombreUser;

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoEquipos.lstEquipos = new Gson().fromJson(s, new TypeToken<List<Equipo>>() {
                        }.getType());
                        compararArrays();


                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    private void compararArrays() {

        if (lstEquiposComparativa.size() != ListadoEquipos.lstEquipos.size()) {
            mostrarData(getContext());
            lstEquiposComparativa.clear();
            lstEquiposComparativa.addAll(ListadoEquipos.lstEquipos);
        }

    }

    private void mostrarData(Context context) {
        rv.setLayoutManager(new LinearLayoutManager(context));
        adaptador = new EquiposAdapter(context);
        rv.setAdapter(adaptador);
        adaptador.setOnClickListener(v -> {
            ListadoEquipos.iEquipoSelected = rv.getChildAdapterPosition(v);
            oEquipo = ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected);
            comprobarLider(oEquipo);
        });
    }

    public void crearEquipo(View view) {
        view.findViewById(R.id.btnCrearEquipo).setOnClickListener(v -> {
            Intent intentCrearEquipo = new Intent(getContext(), crearEquipo.class);
            startActivityForResult(intentCrearEquipo, 1);
        });
    }

    public void unirseEquipo(View view) {
        view.findViewById(R.id.btnUnirseEquipo).setOnClickListener(v -> {
            Intent intentLogin = new Intent(getContext(), unirseEquipo.class);
            startActivity(intentLogin);
        });
    }

    public void pasarEquipo(Context context, Equipo oEquipo, String sNombreCreado) {
        Intent iDetalleEquipo = new Intent(context, EquipoDetalle.class);
        iDetalleEquipo.putExtra("Equipo", oEquipo);
        iDetalleEquipo.putExtra("sNombreCreador", sNombreCreado);
        startActivity(iDetalleEquipo);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                mostrarEquipos();
                // esta pantalla es cuando todo ha salido bien.
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                // esta pantalla es cuando ha salido mal.
            }
        }
    }

    public void comprobarLider(Equipo oEquipo) {
        String sUrl = Utils.hosting + "equipo-usuario/comprobar-lider.php?txtEquipo=" + oEquipo.getiIdEquipo();

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean boCreador = false;
                        Log.d("Rob", sUrl);
                        Usuario oUsuario;
                        oUsuario = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());
                        pasarEquipo(getContext(), oEquipo, oUsuario.getsUser());
                        Log.d("Hola", oUsuario.getsUser().toString());

                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

}

