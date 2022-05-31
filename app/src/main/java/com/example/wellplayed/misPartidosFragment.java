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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Partido_Equipo;
import com.example.wellplayed.model.Partido_Usuario;
import com.example.wellplayed.model.Producto;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class misPartidosFragment extends Fragment {


    RecyclerView Rv;
    ArrayList<String> lstCategoriasPartidos = new ArrayList<String>();
    public static String sNombreUser;
    public static Spinner spinnerCategoriaPartidos;
    String sVentana = "MisPartidos";
    TextView lblTieneEquipo, lblDeseaUnirseEquipo;
    Button btnUnirsePartido;
    Partido_Usuario oPartidoUsuario;
    Partido_Equipo oPartidoEquipo;


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
        MainActivity.ocultarBienvenida();
        View vista = inflater.inflate(R.layout.fragment_mis_partidos, container, false);
        Rv = vista.findViewById(R.id.recyclerViewPartidos);
        sNombreUser = MainActivity.oUsuario.getsUser();
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
        listarPartidos();
        siUsuarioTieneEquipo();
        return vista;
    }

    private void listarPartidos() {
        spinnerCategoriaPartidos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int iPosicion, long l) {
                if (iPosicion == 1) {
                    listarPartidosEquipo();
                } else {
                    listarPartidosUsuario();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void listarPartidosEquipo() {

        String sUrl = Utils.hosting + "partidos/partido_equipo/lst-partidos-completos.php?txtNombre=" + sNombreUser;

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        if (s.equals("[\"Vacio\"]")) {
                            Toast.makeText(getContext(), "Aún no te has unido a ningún partido", Toast.LENGTH_SHORT).show();
                        } else {
                            ListadoPartidosEquipo.lstPartidoEquipo.clear();
                            ListadoPartidosEquipo.lstPartidoEquipo = new Gson().fromJson(s, new TypeToken<List<Partido_Equipo>>() {
                            }.getType());
                            mostrarData(getContext());
                        }
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void listarPartidosUsuario() {
        String sUrl = Utils.hosting + "partidos/partido_usuario/lst-partidos-completos.php?txtNombre=" + sNombreUser;

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        ListadoPartidosUsuario.lstPartidoUsuario.clear();
                        ListadoPartidosUsuario.lstPartidoUsuario = new Gson().fromJson(s, new TypeToken<List<Partido_Usuario>>() {
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
        Rv.setLayoutManager(new LinearLayoutManager(context));
        UnirsePartidosAdapter adaptador = new UnirsePartidosAdapter(context, spinnerCategoriaPartidos.getSelectedItemPosition(), sVentana);

        adaptador.setOnClickListener(v -> {
            Intent intentEquipo = new Intent(getContext(), PartidoDetalle.class);

            if (spinnerCategoriaPartidos.getSelectedItemPosition() == 1) {
                ListadoPartidosEquipo.iPartidoEquipoSelected = Rv.getChildAdapterPosition(v);
                oPartidoEquipo = ListadoPartidosEquipo.lstPartidoEquipo.get(ListadoPartidosEquipo.iPartidoEquipoSelected);
                intentEquipo.putExtra("PartidoEquipo", oPartidoEquipo);
            } else {
                ListadoPartidosUsuario.iPartidoUsuarioSelected = Rv.getChildAdapterPosition(v);
                oPartidoUsuario = ListadoPartidosUsuario.lstPartidoUsuario.get(ListadoPartidosUsuario.iPartidoUsuarioSelected);
                intentEquipo.putExtra("PartidoUsuario", oPartidoUsuario);
            }
            startActivityForResult(intentEquipo, 1);
        });
        Rv.setAdapter(adaptador);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                // esta pantalla es cuando todo ha salido bien.
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                // esta pantalla es cuando ha salido mal.
            }
        }

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                int iTipo = data.getIntExtra("Tipo", 0);
                if (iTipo == 1) {
                    listarPartidosEquipo();
                } else {
                    listarPartidosUsuario();
                }
                // esta pantalla es cuando todo ha salido bien.
            }

            if (resultCode == Activity.RESULT_CANCELED) {
                // esta pantalla es cuando ha salido mal.
            }
        }
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


                        if (posicion == 1) {
                            Log.d("ENTRA EN EQUIPO", "ENTRA EN EQUIPO");
                            listarPartidosEquipo();
                        } else {
                            Log.d("ENTRA EN JUGADOR", "ENTRA EN JUGADOR");
                            listarPartidosUsuario();
                        }

                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                        // En caso de que no haya nada seleccionado.
                    }
                });
    }


    public void onClickUnirsePartido(View v) {
        Intent i = new Intent(getContext(), UnirsePartido.class);
        i.putExtra("Tipo", spinnerCategoriaPartidos.getSelectedItemPosition());
        startActivityForResult(i, 2);
    }


    public void onClickUnirseEquipo(View v) {
        Intent i = new Intent(getContext(), unirseEquipo.class);
        startActivity(i);
    }


}