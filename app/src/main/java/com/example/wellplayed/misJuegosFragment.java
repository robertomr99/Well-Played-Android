package com.example.wellplayed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class misJuegosFragment extends Fragment {

    RecyclerView Rv;

    public misJuegosFragment() {
        // Required empty public constructor
    }


    public void onCreate(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View vista = inflater.inflate(R.layout.fragment_mis_juegos, container, false);
      Rv = vista.findViewById(R.id.recyclerViewJuegos);
      extraerJuego();
      addJuego(vista);
        return vista;
    }

    private void mostrarData(Context context) {
        Rv.setLayoutManager(new GridLayoutManager(context,2));
        JuegosAdapter adaptador = new JuegosAdapter(context);
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);

       /* adaptador.setOnClickListener(v -> {
            ListadoJuegos.iJuegoSelected = Rv.getChildAdapterPosition(v);
            Intent intentDetalle = new Intent(context, JuegosDetalle.class);
            startActivity(intentDetalle);
       })*/
    }

    public void addJuego(View view) {
        view.findViewById(R.id.floatingAddBtnJuegos).setOnClickListener(v -> {
           // Intent intentLogin = new Intent(getContext(), addJuego.class);
            //startActivity(intentLogin);
        });
    }


    public void extraerJuego() {
        String sUrl = Utils.hosting + "get-juego.php";
        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                         ListadoJuegos.lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
                        }.getType());
                        mostrarData(getContext());
                    }
                }

                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

}