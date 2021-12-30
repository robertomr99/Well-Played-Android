package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class crearEquipo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);
        cancelar();
    }

    public void cancelar(){

        // Para volver de una actividad a la ventana anterior.

        findViewById(R.id.btnCancelar).setOnClickListener( v ->{
            super.onBackPressed();
        });
    }

}