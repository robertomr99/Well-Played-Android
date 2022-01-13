package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Producto;
import com.example.wellplayed.model.Usuario;
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class crearEquipo extends AppCompatActivity {

    Producto oProducto;
    public static ImageView imgViewEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);
        imgViewEquipo = findViewById(R.id.imgViewEquipo);

        cambiarLogo();

        cancelar();
    }

    public void cancelar(){

        // Para volver de una actividad a la ventana anterior.

        findViewById(R.id.btnCancelar).setOnClickListener( v ->{
            super.onBackPressed();
        });
    }

    public void cambiarLogo() {

        imgViewEquipo.setOnClickListener(v -> {
            Intent intentLogin = new Intent(this, Logos.class);
            startActivityForResult(intentLogin, 1);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {
                intentProducto();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "No se ha podido añadir el avatar seleccionado", Toast.LENGTH_SHORT).show();
            }
        }
    } //onActivityResult

    private void intentProducto() {
        try {
            if (getIntent().hasExtra("producto")) {
                oProducto = (Producto) getIntent().getSerializableExtra("producto");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}