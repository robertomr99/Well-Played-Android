package com.example.wellplayed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class tiendaFragment extends Fragment {

    public static final String sNombreUser = MainActivity.oUsuario.getsUser();
    RecyclerView Rv;
    public static Spinner spinnerCategoriaProducto;

    public tiendaFragment() {
    }


    public void onCreate(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mostrarProductos();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_tienda, container, false);
        Rv = vista.findViewById(R.id.recyclerViewTienda);
        spinnerCategoriaProducto = vista.findViewById(R.id.spinnerCategoriaProducto);
        cambiarCategoriaProducto();
        return vista;
    }

    private void mostrarProductos() {
        int iSeleccionado = devolverProducto();
        String sUrl = Utils.hosting + "productos/get-productos.php?txtCategoria=" + iSeleccionado;

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoProductos.lstProductos = new Gson().fromJson(s, new TypeToken<List<Producto>>() {
                        }.getType());
                        mostrarData(getContext(),iSeleccionado);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private int devolverProducto() {
        int iResultado = 0;
        if (spinnerCategoriaProducto.getSelectedItemPosition() == 0) {
            iResultado = 1;
        } else if (spinnerCategoriaProducto.getSelectedItemPosition() == 1) {
            iResultado = 2;
        }
        return iResultado;
    }


    public void cambiarCategoriaProducto() {
        spinnerCategoriaProducto.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {
                        mostrarProductos();
                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                        // En caso de que no haya nada seleccionado.
                    }
                });
    }


    private void mostrarData(Context context, int iSeleccionado) {
        Log.d("Seleccionado", String.valueOf(iSeleccionado));
        int iGridSize=0;
        if(iSeleccionado == 1){
            iGridSize=2;
        }else{
            iGridSize=1;
        }
        Log.d("Size", String.valueOf(iGridSize));
        Rv.setLayoutManager(new GridLayoutManager(context, iGridSize));
        ProductosAdapter adaptador = new ProductosAdapter(context);
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);

        adaptador.setOnClickListener(v -> {
            ListadoProductos.iProductoSelected = Rv.getChildAdapterPosition(v);
            Intent intentLogin = new Intent(getContext(), ProductosDetalle.class);
            startActivity(intentLogin);
        });
    }
}