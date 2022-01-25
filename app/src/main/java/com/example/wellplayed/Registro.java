package com.example.wellplayed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

import com.example.wellplayed.model.Data;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Registro extends AppCompatActivity {

    EditText txtUsuario,txtEmail,txtContrasenia,txtConfirmarContrasenia,txtFechaNacimiento;
    Spinner spinPaises;
    Button btnCrearCuenta;
    Usuario oUsuario;
    Calendar c = Calendar.getInstance();
    LocalDate c2 = LocalDate.now();
    DatePickerDialog dpd;
    public static Data oData = new Data();
    public static Registro context;

    public Registro(){
        context = this;
    }
    public static Registro getInstance(){
        return context;
    }

    //List<LAVERDADERA> lstVerdad;
    //String sUrl = "http://well-played.infinityfreeapp.com/pruebas_Miguel/ins-coche.php?";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final int iDia = c.get(Calendar.DAY_OF_MONTH);
        final int iMes = c.get(Calendar.MONTH);
        final int iAnnio = c.get(Calendar.YEAR);

        txtUsuario = findViewById(R.id.txtUsuarioRegistro);
        txtEmail = findViewById(R.id.txtEmail);
        txtContrasenia = findViewById(R.id.txtPass);
        txtConfirmarContrasenia = findViewById(R.id.txtConfirmPass);
        txtFechaNacimiento = findViewById(R.id.fchNac);
        spinPaises = findViewById(R.id.spinnerPais);


        findViewById(R.id.btnCrearCuenta).setOnClickListener(view -> {
            comprobarUser();
            onClickLogin(view);
        });

        txtFechaNacimiento.setOnClickListener(view -> {

            dpd = new DatePickerDialog(Registro.this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker datePicker, int iYear, int iMonth, int iDay) {
                    txtFechaNacimiento.setText(iDay + "/" + (iMonth+1) + "/" + iYear);
                }
            },iAnnio, iMes, iDia);
            dpd.show();
        });


    }


    public void comprobarUser(){

        Usuario oUsuario = new Usuario();
        oUsuario = agregarUsuario();
        String sUrl = Utils.hosting + "usuario/comprobar-user.php?txtUsuario="+oUsuario.getsUser()+"&txtEmail="+oUsuario.getsEmail();

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {

                        Log.d("alacide", sUrl);
                        oData = new Gson().fromJson(s, new TypeToken<Data>() {
                        }.getType());
                        existeUsuario(oData);

                    }
                }

                , volleyError -> {

            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }

    private void existeUsuario(Data oData) {
        boolean boExito = false;
        if(oData.getiContador() > 0){
            boExito = true;
        }
        insertUsuario(agregarUsuario(),boExito);
    }

    public static void insertUsuario(Usuario oUser, boolean boExito) {

        if(!boExito){
            String sUrl = Utils.hosting + "usuario/ins-usuario.php?txtEmail="+oUser.getsEmail()+"&txtUsuario="+oUser.getsUser()+"&txtPass="+oUser.getsPassword()+"&txtFechaNacimiento="+oUser.getsFechaNacimiento()+"&txtPais="+oUser.getiPais()+"&txtMonedas="+oUser.getiMonedas()+"&txtAdministrador="+oUser.isBoAdmin();

            Volley.newRequestQueue(Login.getInstance().getApplicationContext()).add(new StringRequest(Request.Method.GET,sUrl,
                    s ->{
                        if(s.equals("null")){
                            Toast.makeText(Login.getInstance().getApplicationContext(), "error al crear el usuario", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Login.getInstance().getApplicationContext(), "Usuario creado con éxito", Toast.LENGTH_LONG).show();
                        }
                    }
                    ,volleyError -> {

                Log.d("ALACID",volleyError.getCause().toString());
            }
            ));
        }else{
            Toast.makeText(Login.getInstance().getApplicationContext(), "El nombre de usuario o email no esta disponible", Toast.LENGTH_SHORT).show();
        }

    }


    private Usuario agregarUsuario() {
        Usuario oUser = new Usuario();
        oUser.setsEmail(txtEmail.getText().toString().toUpperCase());
        oUser.setsUser(txtUsuario.getText().toString().toUpperCase());
        oUser.setsPassword(txtContrasenia.getText().toString().toUpperCase());
        oUser.setsFechaNacimiento(txtFechaNacimiento.getText().toString());
        oUser.setiPais(spinPaises.getSelectedItemPosition());
        oUser.setiMonedas(0);
        oUser.setBoAdmin(false);

        return oUser;
    }

    public void onClickLogin(View v){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}