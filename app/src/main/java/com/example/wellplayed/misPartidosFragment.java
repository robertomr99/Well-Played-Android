package com.example.wellplayed;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Producto;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class misPartidosFragment extends Fragment {


    RecyclerView Rv;
    ArrayList<String> lstCategoriasPartidos = new ArrayList<String>();
    public static final String sNombreUser = MainActivity.oUsuario.getsUser();
    public static Spinner spinnerCategoriaPartidos;
    TextView lblTieneEquipo, lblDeseaUnirseEquipo;
    Button btnUnirsePartido;


    public misPartidosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_mis_partidos, container, false);
        Rv = vista.findViewById(R.id.recyclerViewPartidos);
        btnUnirsePartido = vista.findViewById(R.id.btnUnirsePartido);
        lblTieneEquipo = vista.findViewById(R.id.lblNoTieneEquipo);
        lblDeseaUnirseEquipo = vista.findViewById(R.id.lblDeseasUnirteEquipo);
        lblTieneEquipo.setVisibility(View.GONE);
        lblDeseaUnirseEquipo.setVisibility(View.GONE);

        btnUnirsePartido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUnirsePartido(view);
            }
        });

        lblDeseaUnirseEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUnirseEquipo(view);
            }
        });

        lstCategoriasPartidos.clear();
        spinnerCategoriaPartidos = vista.findViewById(R.id.spinnerCategoriaPartidos);
        rellenarCategorias();
        siUsuarioTieneEquipo();


        return vista;
    }

    private void rellenarCategorias() {
        lstCategoriasPartidos.add("Individual");
        lstCategoriasPartidos.add("En equipo");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.item_spinner, lstCategoriasPartidos);
        spinnerCategoriaPartidos.setAdapter(adapter);
    }

    public void siUsuarioTieneEquipo() {

        String sUrl = Utils.hosting + "partidos/siUsuarioTieneEquipo.php?txtNombre=" + sNombreUser;

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        String sNumeroEquipos;
                        sNumeroEquipos = s;
                        cambiarCategoriaPartido(Integer.parseInt(sNumeroEquipos));
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void cambiarCategoriaPartido(Integer iNumeroEquipos) {
        spinnerCategoriaPartidos.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {
                        if (iNumeroEquipos > 0 && posicion == 1) {
                            lblTieneEquipo.setVisibility(View.GONE);
                            lblDeseaUnirseEquipo.setVisibility(View.GONE);
                        } else if (iNumeroEquipos == 0 && posicion == 1) {
                            lblTieneEquipo.setVisibility(View.VISIBLE);
                            lblDeseaUnirseEquipo.setVisibility(View.VISIBLE);
                        } else {
                            lblTieneEquipo.setVisibility(View.GONE);
                            lblDeseaUnirseEquipo.setVisibility(View.GONE);
                        }
                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                        // En caso de que no haya nada seleccionado.
                    }
                });
    }


    public void onClickUnirsePartido(View v) {
        Intent i = new Intent(getContext(), UnirsePartido.class);
        startActivity(i);
    }


    public void onClickUnirseEquipo(View v) {
        Intent i = new Intent(getContext(), unirseEquipo.class);
        startActivity(i);
    }


}