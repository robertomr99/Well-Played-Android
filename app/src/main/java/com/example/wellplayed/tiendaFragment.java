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
    public tiendaFragment() {
    }


    public void onCreate(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_tienda, container, false);
        Rv = vista.findViewById(R.id.recyclerViewTienda);
        mostrarProductos();
        return vista;
    }

   private void mostrarProductos(){
       String sUrl = Utils.hosting + "productos/get-productos.php";

       Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
               s -> {
                   Log.d("vacio", s);
                   if (s.equals("")) {
                       Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                   } else {
                       Log.d("Rob", sUrl);
                       ListadoProductos.lstProductosBanner = new Gson().fromJson(s, new TypeToken<List<Producto>>() {
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
        Rv.setLayoutManager(new GridLayoutManager(context,1));
        ProductosAdapter adaptador = new ProductosAdapter(context);
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);

        adaptador.setOnClickListener(v -> {
            ListadoProductos.iProductoBannerSelected = Rv.getChildAdapterPosition(v);
            Intent intentLogin = new Intent(getContext(), ProductosDetalle.class);
            startActivity(intentLogin);
        });
    }
}