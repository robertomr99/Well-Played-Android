package com.example.wellplayed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class misEquiposFragment extends Fragment {

    RecyclerView recyclerView;

    public misEquiposFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_mis_equipos, container, false);
        crearEquipo(view);
        unirseEquipo(view);
        return view;
    }

    private void mostrarData(Context context) {
        recyclerView.findViewById(R.id.recyclerViewEquipos);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        EquiposAdapter adaptador = new EquiposAdapter(context);
        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            ListadoEquipos.iEquipoSelected = recyclerView.getChildAdapterPosition(v);

            Intent intentDetalle = new Intent(context, EquipoDetalle.class);
            startActivity(intentDetalle);
        });
    }

    public void crearEquipo(View view) {
        view.findViewById(R.id.btnCrearEquipo).setOnClickListener(v -> {
            Intent intentLogin = new Intent(getContext(), crearEquipo.class);
            startActivity(intentLogin);
        });
    }

    public void unirseEquipo(View view) {
        view.findViewById(R.id.btnUnirseEquipo).setOnClickListener(v -> {
            Intent intentLogin = new Intent(getContext(), unirseEquipo.class);
            startActivity(intentLogin);
        });
    }
}

