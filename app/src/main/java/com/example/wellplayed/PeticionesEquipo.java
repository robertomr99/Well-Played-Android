package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.wellplayed.model.Equipo_Usuario;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class

PeticionesEquipo extends AppCompatActivity {

    RecyclerView rv;
    PeticionesAdapter peticionesAdapter;
    Integer iIdEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peticiones_equipo);
        intentEquipoDetalle();
        rv = findViewById(R.id.recyclerViewPeticiones);
        seleccionarPeticion(iIdEquipo);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void seleccionarPeticion(int iIdEquipo) {
        String sUrl = Utils.hosting + "peticiones/select-peticion.php?txtEquipo=" + iIdEquipo;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoPeticionesUsuarios.lstPeticionesUsuarios = new Gson().fromJson(s, new TypeToken<List<Usuario>>() {
                        }.getType());
                        Log.d("Lista", ListadoPeticionesUsuarios.lstPeticionesUsuarios.toString());
                        mostrarUsuarios();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public static void unirseEquipo(Context context, int iIdEquipo, int iIdUsuario) {
        String sUrl = Utils.hosting + "equipo/ins-equipo-usuario.php?txtEquipo="+iIdEquipo+"&txtUsuario="+iIdUsuario+"&txtCreador="+0;
        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                    } else {
                        Toast.makeText(context,"Te has unido al equipo", Toast.LENGTH_SHORT).show();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void delPeticion(int iIdEquipo, int iIdUsuario) {
        String sUrl = Utils.hosting + "peticiones/delete-peticion.php?txtUsuario=" + iIdUsuario + "&txtEquipo=" + iIdEquipo;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        seleccionarPeticion(iIdEquipo);

                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    public static void sumarMiembros(Context context, int iIdEquipo) {
        String sUrl = Utils.hosting + "equipo/sumar-miembros.php?txtEquipo="+iIdEquipo;
        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                    } else {
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }



    private void mostrarUsuarios() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        peticionesAdapter = new PeticionesAdapter(this, new PeticionesAdapter.PeticionesInterface() {
            @Override
            public void insertPeticion(Usuario oUsuario) {
                unirseEquipo(PeticionesEquipo.this, iIdEquipo, oUsuario.getiIdUsuario());
                sumarMiembros(getApplicationContext(), iIdEquipo);
                delPeticion(iIdEquipo, oUsuario.getiIdUsuario());
            }

            @Override
            public void borrarPeticion(Usuario oUsuario) {
                delPeticion(iIdEquipo, oUsuario.getiIdUsuario());
            }

        });
        rv.setAdapter(peticionesAdapter);
    }

    private void intentEquipoDetalle() {
        try {
            if (getIntent().hasExtra("Equipo")) {
                iIdEquipo = getIntent().getIntExtra("Equipo", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}