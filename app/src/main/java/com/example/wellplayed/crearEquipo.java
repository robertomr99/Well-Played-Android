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
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Producto;
import com.example.wellplayed.model.Usuario;
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class crearEquipo extends AppCompatActivity {

    Producto oProducto;
    TextView lblNombreEquipo;
    CheckBox checkBoxLOL , checkBoxValorant,checkBoxCSGO,checkBoxFIFA;
    public static ImageView imgViewEquipo;
    public static final String sNombreUser = MainActivity.oUsuario.getsUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_equipo);
        imgViewEquipo = findViewById(R.id.imgViewEquipo);
        lblNombreEquipo = findViewById(R.id.txtNombreEquipo);
        checkBoxLOL = findViewById(R.id.checkBoxLOL);
        checkBoxValorant = findViewById(R.id.checkBoxValorant);
        checkBoxCSGO = findViewById(R.id.checkBoxCSGO);
        checkBoxFIFA = findViewById(R.id.checkBoxFIFA);

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

    public void insertEquipo(View view) {

            String sUrl = Utils.hosting + "equipo/ins-equipo.php?txtNombre="+lblNombreEquipo.getText().toString()+"&txtFoto="+oProducto.getsFoto();

            Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                    s ->{
                        if(s.equals("null")){
                        }else{
                            seleccionarIdUser();
                        }
                    }
                    ,volleyError -> {

                Log.d("ALACID",volleyError.getCause().toString());
            }
            ));

    }

    private void seleccionarIdUser() {

        String sUrl = Utils.hosting + "usuario/select-idUser.php?txtUsuario="+sNombreUser;
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Usuario oUsuarioId = new Usuario();
                        oUsuarioId = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());

                        selectiIdEquipo(lblNombreEquipo.getText().toString(), oUsuarioId.getiIdUsuario());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private void insertEquipoUsuario(int iIdEquipo, int iIdUsuario) {

        String sUrl = Utils.hosting + "equipo/ins-equipo-usuario.php?txtEquipo="+iIdEquipo+"&txtUsuario="+iIdUsuario+"&txtCreador="+true;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){
                        Toast.makeText(getApplicationContext(), "Error al crear el equipo", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Equipo creado con éxito", Toast.LENGTH_LONG).show();
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

    private void selectiIdEquipo(String sNombreEquipo, int iIdUsuario) {

        String sUrl = Utils.hosting + "equipo/select-idEquipo.php?txtEquipo="+sNombreEquipo;
        Log.d("selectIdEquipo",sUrl);
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){

                    }else{
                        Equipo oEquipo;
                        oEquipo = new Gson().fromJson(s, new TypeToken<Equipo>() {
                        }.getType());

                        contadorJuegos(oEquipo.getiIdEquipo());
                        insertEquipoUsuario(oEquipo.getiIdEquipo(), iIdUsuario);
                    }
                }
                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));
    }

    private void insertEquipoJuego(Integer iIdEquipo, Integer iIdJuego) {

        String sUrl = Utils.hosting + "equipo-juego/ins-equipo-juego.php?txtEquipo="+iIdEquipo+"&txtJuego="+iIdJuego+"&txtVictorias="+0+"&txtDerrotas="+0+"&txtWinRate="+0;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                }
                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));

    }

    private void contadorJuegos(int iIdEquipo){

        if(checkBoxLOL.isChecked()){
            insertEquipoJuego(iIdEquipo,1);
        }
        if(checkBoxValorant.isChecked()) {
            insertEquipoJuego(iIdEquipo, 2);
        }
        if(checkBoxCSGO.isChecked()){
            insertEquipoJuego(iIdEquipo,3);
        }
        if(checkBoxFIFA.isChecked()){
            insertEquipoJuego(iIdEquipo,4);
        }

    }

}