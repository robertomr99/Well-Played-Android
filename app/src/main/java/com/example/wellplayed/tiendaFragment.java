package com.example.wellplayed;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class tiendaFragment extends Fragment {

    RecyclerView Rv;

    public tiendaFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_mis_juegos, container, false);
        Rv = vista.findViewById(R.id.recyclerViewJuegos);
        addProducto(vista);
        return vista;
    }

    public void addProducto(View view){
        view.findViewById(R.id.floatingAddBtnJuegos).setOnClickListener(v -> {

        });
    }

    private void mostrarData(Context context) {
        Rv.setLayoutManager(new GridLayoutManager(context, 2));
        JuegosAdapter adaptador = new JuegosAdapter(context);
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);
    }
}