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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
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
    TextView lblNombreEquipoCrear;
    CheckBox chechBoxLOL;
    public static ImageView imgViewEquipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);
        imgViewEquipo = findViewById(R.id.imgViewEquipo);
        lblNombreEquipoCrear = findViewById(R.id.lblNombreEquipoCrear);
        chechBoxLOL = findViewById(R.id.checkBoxLOL);

        cancelar();
    }

    public void cancelar() {

        // Para volver de una actividad a la ventana anterior.

        findViewById(R.id.btnCancelar).setOnClickListener(v -> {
            super.onBackPressed();
        });
    }

    public void cambiarLogo(View view) {
        Intent intentLogin = new Intent(this, Logos.class);
        startActivityForResult(intentLogin, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != RESULT_CANCELED) {
            if (resultCode == Activity.RESULT_OK) {
                oProducto = (Producto) data.getExtras().get("producto"); // el parametro intent data lo que hace es
                Glide.with(getApplicationContext()).load(oProducto.getsFoto()).circleCrop().into(imgViewEquipo);


            }
        } else {
            Toast.makeText(this, "No se ha podido añadir el avatar seleccionado", Toast.LENGTH_SHORT).show();

        }
    } //onActivityResult

    private void insertEquipo() {

            String sUrl = Utils.hosting + "equipo/ins-equipo.php?txtNombre="+lblNombreEquipoCrear.getText().toString()+"&txtFoto="+oProducto.getsFoto();

            Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                    s ->{
                        if(s.equals("null")){
                            Toast.makeText(getApplicationContext(), "error al crear el equipo", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Equipo creado con éxito", Toast.LENGTH_LONG).show();
                            selectiIdEquipo();
                            insertEquipoJuego();
                        }
                    }
                    ,volleyError -> {

                Log.d("ALACID",volleyError.getCause().toString());
            }
            ));

    }

    private void insertEquipoJuego() {
        int iIdJuego;

        if(chechBoxLOL.isChecked()){
            iIdJuego = 1;
        }
        String sUrl = Utils.hosting + "equipo-juego/ins-equipoJuego.php?txtEquipo="+oEquipo.getiIdEquipo.toString()+"&txtFoto="+oProducto.getsFoto();

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){
                        Toast.makeText(getApplicationContext(), "error al crear el equipo", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Equipo creado con éxito", Toast.LENGTH_LONG).show();
                        insertEquipoJuego();
                    }
                }
                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));

    }

}