package com.example.wellplayed;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class addJuego extends AppCompatActivity {

    Juego oJuego;
    Usuario oUsuario;
    public static String sNombreUser = MainActivity.oUsuario.getsUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_juego);
        sNombreUser = MainActivity.oUsuario.getsUser();
        mostrarJuegosQueNoTieneUser();
    }

    public void mostrarJuegosQueNoTieneUser() {
        String sUrl = Utils.hosting + "usuario-juego/JuegoQueNoTieneUser.php?txtUsuario="+sNombreUser;
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoJuegos.lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
                        }.getType());
                        if(ListadoJuegos.lstJuegos.size() <= 0){
                         mostrarJuegos();
                        }else{
                            mostrarData();
                        }
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }


    public void mostrarJuegos() {
        String sUrl = Utils.hosting + "juego/get-juego.php";
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoJuegos.lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
                        }.getType());
                        mostrarData();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private void mostrarData() {
        RecyclerView Rv = findViewById(R.id.recyclerViewAddJuego);
        Rv.setLayoutManager(new GridLayoutManager(this,2));
        JuegosAdapter adaptador = new JuegosAdapter(this);
        Rv.setAdapter(adaptador);
        Rv.setHasFixedSize(true);
        adaptador.setOnClickListener(v -> {
            ListadoJuegos.iJuegoSelected = Rv.getChildAdapterPosition(v);
            oJuego = ListadoJuegos.lstJuegos.get(ListadoJuegos.iJuegoSelected);
            seleccionarIdJuego();
            Toast.makeText(this, "El juego se ha añadido correctamente.", Toast.LENGTH_SHORT).show();

        });

    }

    private void seleccionarIdJuego(){

        String sUrl = Utils.hosting + "juego/select-idJuego.php?txtJuego="+oJuego.getsNombre();
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        oJuego = new Gson().fromJson(s, new TypeToken<Juego>() {
                        }.getType());
                        seleccionarIdUser();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private void seleccionarIdUser() {

        String sUrl = Utils.hosting + "usuario/select-idUser.php?txtUsuario="+sNombreUser;
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Usuario oUsuarioId = new Usuario();
                        Log.d("Rob", sUrl);
                        oUsuarioId = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());
                        insertUsuarioJuego(oUsuarioId);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));

    }

    private void insertUsuarioJuego(Usuario oUsuarioId) {

        String sUrl = Utils.hosting + "usuario-juego/insert-usuario-juego.php?txtJuego="+oJuego.getiIdJuego()+"&txtUsuario="+oUsuarioId.getiIdUsuario()+"&txtVictorias="+0+"&txtDerrotas="+0+"&txtWinRate="+0;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){
                        Toast.makeText(getApplicationContext(), "Error al insertar el juego", Toast.LENGTH_LONG).show();
                        Intent i = new Intent();
                        setResult(Activity.RESULT_CANCELED,i);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Juego insertado con éxito", Toast.LENGTH_LONG).show();
                        Intent i = new Intent();
                        setResult(Activity.RESULT_OK,i);
                        finish();
                    }
                }
                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));
    }

}