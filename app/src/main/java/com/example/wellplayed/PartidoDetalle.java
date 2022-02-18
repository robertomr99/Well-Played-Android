package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Partido_Equipo;
import com.example.wellplayed.model.Partido_Usuario;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PartidoDetalle extends AppCompatActivity {

    CircleImageView imgC1Detalle, imgC2Detalle;
    ImageButton imgBtnWin, imgBtnLose;
    TextView lblNombreC1Detalle, lblNombreC2Detalle;
    RecyclerView rvEquipoDetalle1, rvEquipoDetalle2;
    PartidoDetalleAdapter partidoDetalleAdapter;
    Partido_Equipo oPartidoEquipo;
    Partido_Usuario oPartidoUsuario;
    public static List<Usuario> lstUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentMisPartidos();
        setContentView(R.layout.activity_partido_detalle);
        imgC1Detalle = findViewById(R.id.imgViewC1Detalle);
        imgC2Detalle = findViewById(R.id.imgViewC2Detalle);
        lblNombreC1Detalle = findViewById(R.id.lblNombreC1Detalle);
        lblNombreC2Detalle = findViewById(R.id.lblNombreC2Detalle);
        imgBtnWin = findViewById(R.id.imgBtnWin);
        imgBtnLose = findViewById(R.id.imgBtnLose);
        rvEquipoDetalle1 = findViewById(R.id.rvEquipo1Detalle);
        rvEquipoDetalle2 = findViewById(R.id.rvEquipo2Detalle);

        rellenarComponentes();

        imgBtnWin.setOnClickListener(view -> {

        });

        imgBtnLose.setOnClickListener(view -> {

        });

    }


    private void rellenarComponentes() {

        if (oPartidoUsuario == null && oPartidoEquipo != null) {
            Log.d("ASDASDASDASDASDASDASDASDASDASDASDAS", "EQUIPO");
            componentesDelEquipo(oPartidoEquipo.getsNombreEquipo1(), 1);
            componentesDelEquipo(oPartidoEquipo.getsNombreEquipo2(), 2);
        } else {
            Log.d("ASDASDASDASDASDASDASDASDASDASDASDAS", "USUARIO");
            mostrarUsuarios();
        }
    }


    private void mostrarUsuarios() {
        lblNombreC1Detalle.setText(oPartidoUsuario.getsNombreJugador1());
        lblNombreC2Detalle.setText(oPartidoUsuario.getsNombreJugador2());

        Glide.with(getApplicationContext()).load(oPartidoUsuario.getsFotoJugador1()).circleCrop().into(imgC1Detalle);
        Glide.with(getApplicationContext()).load(oPartidoUsuario.getsFotoJugador2()).circleCrop().into(imgC2Detalle);
    }

    private void mostrarEquipo1() {
        Glide.with(getApplicationContext()).load(oPartidoEquipo.getsFotoEquipo1()).circleCrop().into(imgC1Detalle);
        lblNombreC1Detalle.setText(oPartidoEquipo.getsNombreEquipo1());

        rvEquipoDetalle1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        partidoDetalleAdapter = new PartidoDetalleAdapter(getApplicationContext());
        rvEquipoDetalle1.setAdapter(partidoDetalleAdapter);
    }

    private void mostrarEquipo2() {
        Glide.with(getApplicationContext()).load(oPartidoEquipo.getsFotoEquipo2()).circleCrop().into(imgC2Detalle);
        lblNombreC2Detalle.setText(oPartidoEquipo.getsNombreEquipo2());

        rvEquipoDetalle2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        partidoDetalleAdapter = new PartidoDetalleAdapter(getApplicationContext());
        rvEquipoDetalle2.setAdapter(partidoDetalleAdapter);
    }

    private void componentesDelEquipo(String sNombreEquipo, int iEquipo) {
        String sUrl = Utils.hosting + "partidos/partido_equipo/lst-jugadores-equipo.php?txtEquipo="+sNombreEquipo;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        lstUsuarios = new Gson().fromJson(s, new TypeToken<List<Usuario>>() {
                        }.getType());

                         Log.d("ASLDKJNALSKDJALKSJDLKASJDLKAJSDKL", ""+lstUsuarios.size());

                        if (iEquipo == 1) {
                            mostrarEquipo1();
                        } else {
                            mostrarEquipo2();
                        }

                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    private void intentMisPartidos() {
        try {
            if (getIntent().hasExtra("PartidoEquipo")) {
                oPartidoEquipo = (Partido_Equipo) getIntent().getSerializableExtra("PartidoEquipo");
            }

            if (getIntent().hasExtra("PartidoUsuario")) {
                oPartidoUsuario = (Partido_Usuario) getIntent().getSerializableExtra("PartidoUsuario");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}