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
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Soporte;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class soporteFragment extends Fragment {

    FloatingActionButton btnAgregarTicket;
    RecyclerView rvSoporte;
    Soporte oSoporte;
    SoporteAdapter adaptador;
    String sNombreUser;
    public soporteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View vista = inflater.inflate(R.layout.fragment_soporte, container, false);
       rvSoporte = vista.findViewById(R.id.rvSoporte);
       btnAgregarTicket = vista.findViewById(R.id.btnAddTicket);
       sNombreUser = MainActivity.oUsuario.getsUser();
       btnAgregarTicket.setOnClickListener(view -> {
           Intent i = new Intent(getContext(), Ticket.class);
           startActivity(i);
       });
       mostrarTickets();
       return vista;
    }



    public void mostrarTickets() {
        // Log.d("objetoUsuario", MainActivity.oUsuario.getsUser());
        String sUrl = Utils.hosting + "soporte/lstTickets.php?txtUsuario=" + sNombreUser.toUpperCase();

        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoSoporte.lstTickets = new Gson().fromJson(s, new TypeToken<List<Soporte>>() {
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
        rvSoporte.setLayoutManager(new GridLayoutManager(context, 1));
        adaptador = new SoporteAdapter(context);
        rvSoporte.setAdapter(adaptador);
        adaptador.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SoporteDetalle.class);
            ListadoSoporte.iTicketSelected = rvSoporte.getChildAdapterPosition(v);
            oSoporte = ListadoSoporte.lstTickets.get(ListadoSoporte.iTicketSelected);
            //intent.putExtra("")
            startActivity(intent);
        });
    }

}