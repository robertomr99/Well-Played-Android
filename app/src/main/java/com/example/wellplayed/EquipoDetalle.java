package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class EquipoDetalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageViewDetalle = findViewById(R.id.imageViewEquipoDetalle);
        TextView lblNombreDetalle = findViewById(R.id.lblNombreDetalle);
        TextView lblVictoriasDetalle = findViewById(R.id.lblVictoriasEquipo);
        TextView lblDerrotasDetalle = findViewById(R.id.lblDerrotasEquipo);
        TextView lblWinRateDetalle = findViewById(R.id.lblWinRateEquipo);

        imageViewDetalle.setImageResource(ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected).getiFoto());
        lblNombreDetalle.setText(""+ ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected).getsNombre());
        lblVictoriasDetalle.setText(""+ ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected).getsNombre());
        lblDerrotasDetalle.setText(""+ ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected).getsNombre());
        lblWinRateDetalle.setText(""+ ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected).getsNombre());

    }
}