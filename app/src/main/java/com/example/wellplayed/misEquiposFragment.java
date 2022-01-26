package com.example.wellplayed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class misEquiposFragment extends Fragment {

    RecyclerView recyclerView;
    EquiposAdapter adaptador;
    public static String sNombreUser = MainActivity.oUsuario.getsUser();
    public static Equipo_Usuario oEquipo_Usuario;
    public static Equipo oEquipo;

    public misEquiposFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mis_equipos, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewEquipos);
        sNombreUser = MainActivity.oUsuario.getsUser();
        mostrarEquipos();
        crearEquipo(view);
        unirseEquipo(view);
        return view;
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
                        Log.d("pruebabawjebajwe",ListadoEquipos.lstEquipos.toString());
                        mostrarData(getContext());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void contarMiembros() {
        String sUrl = Utils.hosting + "equipo-usuario/count-miembros.php?txtNombre=" + sNombreUser;

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        Integer iMiembros = 0;
                        iMiembros = new Gson().fromJson(s, new TypeToken<Integer>() {
                        }.getType());
                        mostrarData(getContext());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private void mostrarData(Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adaptador = new EquiposAdapter(context);
        recyclerView.setAdapter(adaptador);
        adaptador.setOnClickListener(v -> {
            ListadoEquipos.iEquipoSelected = recyclerView.getChildAdapterPosition(v);
            oEquipo = ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected);
            pasarEquipo(context, oEquipo);
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

    public void pasarEquipo(Context context, Equipo oEquipo) {
        Intent iDetalleEquipo = new Intent(context, EquipoDetalle.class);
        iDetalleEquipo.putExtra("Equipo", oEquipo);
        startActivity(iDetalleEquipo);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                mostrarEquipos();
                adaptador.notifyDataSetChanged();
                // esta pantalla es cuando todo ha salido bien.
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                // esta pantalla es cuando ha salido mal.
            }
        }
    }

}

