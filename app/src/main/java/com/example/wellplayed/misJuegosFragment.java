package com.example.wellplayed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class misJuegosFragment extends Fragment {

    RecyclerView recyclerView;

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
      addJuego(vista);
        return vista;
    }

    private void mostrarData(Context context) {
        recyclerView = recyclerView.findViewById(R.id.recyclerViewJuegos);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        JuegosAdapter adaptador = new JuegosAdapter(context);

        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            ListadoJuegos.iJuegoSelected = recyclerView.getChildAdapterPosition(v);

            Intent intentDetalle = new Intent(context, JuegosDetalle.class);
            startActivity(intentDetalle);
        });
    }

    public void addJuego(View view) {
        view.findViewById(R.id.floatingAddBtnJuegos).setOnClickListener(v -> {
           // Intent intentLogin = new Intent(getContext(), addJuego.class);
            //startActivity(intentLogin);
        });
    }


}