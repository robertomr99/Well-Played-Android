package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Equipo_Juego;
import com.example.wellplayed.model.Equipo_Usuario;
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class EquipoDetalle extends AppCompatActivity {

    TextView lblNombreDetalle, lblVictoriasDetalle, lblDerrotasDetalle, lblWinRateDetalle;
    ImageView imgViewEquipoDetalle;
    Equipo oEquipo;
    Spinner spinnerJuegos;
    Integer iIdEquipoJuego, iIdJuego;
    ArrayList<String> lstJuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentEquipoJuego();
        setContentView(R.layout.activity_equipo_detalle);

        imgViewEquipoDetalle = findViewById(R.id.imgViewEquipoDetalle);
        lblNombreDetalle = findViewById(R.id.lblNombreDetalle);

        lblVictoriasDetalle = findViewById(R.id.lblVictoriasEquipo);
        lblDerrotasDetalle = findViewById(R.id.lblDerrotasEquipo);
        lblWinRateDetalle = findViewById(R.id.lblWinRateEquipo);

        spinnerJuegos = findViewById(R.id.spinnerJuego);
        lstEquipoJuego();
        devolverProducto();
        getEquipoJuego();

        oEquipo = ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected);
        Glide.with(getApplicationContext()).load(oEquipo.getsFoto()).circleCrop().into(imgViewEquipoDetalle);
        lblNombreDetalle.setText("" + oEquipo.getsNombre());

    }

    private void devolverProducto() {
        switch (String.valueOf(spinnerJuegos.getItemAtPosition(spinnerJuegos.getSelectedItemPosition()))){
            case "LOL":
                iIdJuego = 1;
                break;
            case "VALORANT":
                iIdJuego = 2;
                break;
            case "CSGO":
                iIdJuego = 3;
                break;
            case "FIFA":
                iIdJuego = 4;
                break;
        }
    }

    public void setearDatos(Equipo_Juego oEquipoJuego) {
        Log.d("Rob", oEquipoJuego.toString());

        lblVictoriasDetalle.setText("" + oEquipoJuego.getiVictorias());
        lblDerrotasDetalle.setText("" + oEquipoJuego.getiDerrotas());
        lblWinRateDetalle.setText("" + oEquipoJuego.getfWinRate());
        setearColoresWinRate(oEquipoJuego);
    }


    public void getEquipoJuego() {
        String sUrl = Utils.hosting + "equipo-juego/get-equipo-juego.php?txtEquipo=" + iIdEquipoJuego + "&txtJuego=" + iIdJuego;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        Equipo_Juego oEquipoJuego;
                        oEquipoJuego = new Gson().fromJson(s, new TypeToken<Equipo_Juego>() {
                        }.getType());
                        setearDatos(oEquipoJuego);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void lstEquipoJuego() {
        String sUrl = Utils.hosting + "equipo-juego/lst-equipos-juegos.php?txtEquipo=" + iIdEquipoJuego;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                       lstJuegos = new Gson().fromJson(s, new TypeToken<List<String>>() {
                        }.getType());
                        rellenarSpinnerJuegos(lstJuegos);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private void rellenarSpinnerJuegos(List<String> lstJuegos) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(EquipoDetalle.this, android.R.layout.simple_spinner_dropdown_item, lstJuegos);
        spinnerJuegos.setAdapter(adapter);
    }


    private void intentEquipoJuego() {
        try {
            if (getIntent().hasExtra("Equipo")) {
                Equipo oEquipo;
                oEquipo = (Equipo) getIntent().getSerializableExtra("Equipo");
                iIdEquipoJuego = oEquipo.getiIdEquipo();
                Log.d("PruebaEQUIPO", String.valueOf(iIdJuego));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setearColoresWinRate(Equipo_Juego oEquipoJuego) {
        if (oEquipoJuego.getfWinRate() >= 50 && oEquipoJuego.getfWinRate() <= 60) {
            lblWinRateDetalle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.AmarrilloWinRate));
        } else if (oEquipoJuego.getfWinRate() > 60) {
            lblWinRateDetalle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.VerdeWinRate));
        } else {
            lblWinRateDetalle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.RojoWinRate));
        }
    }
}
