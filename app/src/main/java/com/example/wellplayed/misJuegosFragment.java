package com.example.wellplayed;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Usuario;
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class misJuegosFragment extends Fragment {

    RecyclerView Rv;
    JuegosAdapter adaptador;
    public static Usuario_Juego oUsuario_Juego;
    public static final String sNombreUser = MainActivity.oUsuario.getsUser();

    public misJuegosFragment() {
        // Required empty public constructor
    }

    public void onCreate(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mostrarJuegos();
    }

    @Override
    public void onResume() {
        super.onResume();
        mostrarJuegos();
        Rv.setAdapter(adaptador);
        Log.d("ALACID", "Entra aqui");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_mis_juegos, container, false);
        Rv = vista.findViewById(R.id.recyclerViewJuegos);
        mostrarJuegos();
        addJuego(vista);

        return vista;
    }

    public void selectJuegoUsuario() {
        String sUrl = Utils.hosting + "usuario-juego/get-usuario-juego.php?txtJuego=" + ListadoJuegos.lstJuegos.get(ListadoJuegos.iJuegoSelected).getsNombre().toUpperCase() + "&txtUsuario=" + sNombreUser;

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        oUsuario_Juego = new Gson().fromJson(s, new TypeToken<Usuario_Juego>() {
                        }.getType());
                        Log.d("UsuarioJuego", oUsuario_Juego.toString());
                        pasarUsuarioJuego();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    public void mostrarJuegos() {
        // Log.d("objetoUsuario", MainActivity.oUsuario.getsUser());
        String sUrl = Utils.hosting + "usuario-juego/JuegoQueSiTieneUser.php?txtUsuario=" + sNombreUser.toUpperCase();

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoJuegos.lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
                        }.getType());
                        for (Juego oJuego : ListadoJuegos.lstJuegos) {
                            Log.d("LISTADOJUEGOUSER", oJuego.toString());
                        }
                        mostrarData(getContext());


                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    private void mostrarData(Context context) {
        Rv.setLayoutManager(new GridLayoutManager(context, 2));
        adaptador = new JuegosAdapter(context);
        Rv.setAdapter(adaptador);
        //Rv.setHasFixedSize(true);
        adaptador.setOnClickListener(v -> {
            ListadoJuegos.iJuegoSelected = Rv.getChildAdapterPosition(v);
            selectJuegoUsuario();
        });
    }

    private void pasarUsuarioJuego() {
        Intent iDetalleJuego = new Intent(getContext(), JuegosDetalle.class);
        Log.d("Usuario", oUsuario_Juego.toString());
        iDetalleJuego.putExtra("Usuario_Juego", oUsuario_Juego);
        startActivity(iDetalleJuego);
    }


    public void addJuego(View view) {
        view.findViewById(R.id.floatingAddBtnJuegos).setOnClickListener(v -> {
            Intent intentLogin = new Intent(getContext(), addJuego.class);
            startActivityForResult(intentLogin, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                mostrarJuegos();
                adaptador.notifyDataSetChanged();
                // esta pantalla es cuando todo ha salido bien.
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                // esta pantalla es cuando ha salido mal.
                //Toast.makeText(getContext(), "No se ha podido añadir ningun juego", Toast.LENGTH_SHORT).show();
            }
        }
    } //onActivityResult
}