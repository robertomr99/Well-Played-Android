package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class JuegosDetalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juegos_detalle);

        ImageView imgViewJuegoDetalle = findViewById(R.id.imgViewEquipoDetalle);
        TextView lblNombreJuegoDetalle = findViewById(R.id.lblNombreDetalle);
        TextView lblDescripcionDetalle = findViewById(R.id.lblDescripcionDetalle);

        imgViewJuegoDetalle.setImageResource(ListadoJuegos.lstJuegos.get(ListadoJuegos.iJuegoSelected).getiFoto());
        lblNombreJuegoDetalle.setText(""+ ListadoJuegos.lstJuegos.get(ListadoJuegos.iJuegoSelected).getsNombre());
        lblDescripcionDetalle.setText(""+ ListadoJuegos.lstJuegos.get(ListadoJuegos.iJuegoSelected).getsDescripcion());
    }
}