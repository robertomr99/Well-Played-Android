package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class crearEquipo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);
        cancelar();
    }

    public void cancelar(){
        findViewById(R.id.btnCancelar).setOnClickListener( v ->{
            Intent intentMisEquipos = new Intent(this, misEquiposFragment.class);
            startActivity(intentMisEquipos);
        });
    }

}