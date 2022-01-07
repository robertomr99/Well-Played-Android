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

    RecyclerView recyclerView;

    public tiendaFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_tienda, container, false);
        addProducto(vista);
        return vista;
    }

    private void mostrarData(Context context) {
        recyclerView = recyclerView.findViewById(R.id.recyclerViewTienda);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        JuegosAdapter adaptador = new JuegosAdapter(context);

        recyclerView.setAdapter(adaptador);

        adaptador.setOnClickListener(v -> {
            ListadoProductos.iProductoSelected = recyclerView.getChildAdapterPosition(v);

            Intent intentDetalle = new Intent(context, ProductosDetalle.class);
            startActivity(intentDetalle);
        });
    }
}