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
  //  Button btnCrearEquipo;


    public misEquiposFragment() {
        // Required empty public constructor
    }

    public void onCreate(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mostrarData(getContext());
        crearEquipo(view, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mis_equipos, container, false);
    }


    private void mostrarData(Context context) {
        recyclerView.findViewById(R.id.recyclerViewEquipos);

        Toast.makeText(context, "El recycler view es null", Toast.LENGTH_SHORT).show();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        EquiposAdapter adaptador = new EquiposAdapter(context);

        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            ListadoEquipos.iEquipoSelected = recyclerView.getChildAdapterPosition(v);

            Intent intentDetalle = new Intent(context, EquipoDetalle.class);
            startActivity(intentDetalle);
        });
    }

    public void crearEquipo(View view, Context context) {
        view.findViewById(R.id.btnCrearEquipo).setOnClickListener(v -> {
            Intent intentLogin = new Intent(context, crearEquipo.class);
            startActivity(intentLogin);
        });
    }


}
