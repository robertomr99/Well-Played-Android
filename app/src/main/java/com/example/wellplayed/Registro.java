package com.example.wellplayed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Registro extends AppCompatActivity {

    EditText txtUsuario,txtEmail,txtContrasenia,txtConfirmarContrasenia,txtFechaNacimiento;
    Spinner spinPaises;
    Button btnCrearCuenta;
    Usuario oUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtUsuario = findViewById(R.id.txtUsuarioRegistro);
        txtEmail = findViewById(R.id.txtEmail);
        txtContrasenia = findViewById(R.id.txtPass);
        txtConfirmarContrasenia = findViewById(R.id.txtConfirmPass);
        txtFechaNacimiento = findViewById(R.id.fchNac);
        spinPaises = findViewById(R.id.spinnerPais);
        findViewById(R.id.btnCrearCuenta).setOnClickListener(view -> {
            insertUsuario(agregarUsuario());
            onClickLogin(view);
        });
    }


    private void insertUsuario(Usuario oUser) {
        String sUrl = Utils.hosting + "ins-usuario.php?txtEmail="+oUser.getsEmail()+"&txtUsuario="+oUser.getsUser()+"&txtPass="+oUser.getsPassword()+"&txtFechaNacimiento="+oUser.getsFechaNacimiento()+"&txtPais="+oUser.getiPais()+"&txtMonedas="+oUser.getiMonedas()+"&txtAdministrador="+oUser.isBoAdmin();

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){
                        Toast.makeText(getApplicationContext(), "error al crear el usuario", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Usuario creado con éxito", Toast.LENGTH_LONG).show();
                    }
                }
                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));
    }

    private void obtenerEste() {
        String sUrl = Utils.hosting + "get-coche.php?txtId=1";
        Log.d("alacide", sUrl);
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.d("alacide", sUrl);
                        Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();
                        //laverdad = new Gson().fromJson(s,new TypeToken<LAVERDADERA>(){}.getType());
                        Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();
                        mostrarEste();
                    }
                }
                ,volleyError -> {
            Toast.makeText(this, "E", Toast.LENGTH_SHORT).show();
        }
        ));
    }

    private void mostrarEste() {

        //txtUsuario.setText(laverdad.toString());
    }


    private Usuario agregarUsuario() {
        Usuario oUser = new Usuario();
        oUser.setsEmail(txtEmail.getText().toString());
        oUser.setsUser(txtUsuario.getText().toString());
        oUser.setsPassword(txtContrasenia.getText().toString());
        oUser.setsFechaNacimiento(txtFechaNacimiento.getText().toString());
        oUser.setiPais(spinPaises.getSelectedItemPosition());
        oUser.setiMonedas(0);
        oUser.setBoAdmin(false);

        return oUser;
    }

    public void onClickLogin(View v){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}