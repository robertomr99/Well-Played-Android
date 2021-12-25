package com.example.wellplayed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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
import com.android.volley.toolbox.AsyncHttpStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.wellplayed.model.LAVERDADERA;
import com.example.wellplayed.model.Usuario;
import com.loopj.android.http.*;


import java.security.SecurityPermission;
import java.util.HashMap;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class Registro extends AppCompatActivity {

    EditText txtUsuario,txtEmail,txtContrasenia,txtConfirmarContrasenia,txtFechaNacimiento;
    Spinner spinPaises;
    Button btnCrearCuenta;
    //String sUrl = "http://well-played.infinityfreeapp.com/pruebas_Miguel/ins-coche.php?";
    public static AsyncHttpClient client = new AsyncHttpClient();

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

            ejecutarInsert(agregarLaVerdad());

        });


    }

    /*private void ejecutarInsert(Usuario oUsuario) {
        String sUrl = "https://cp1.awardspace.net/file-manager/www/wellplayed.atwebpages.com#ins-usuario.php?";
        String sParametros = "txtEmail=" + oUsuario.getsEmail() + "&txtUsuario=" + oUsuario.getsUser() + "&txtPass=" + oUsuario.getsPassword() + "&txtFechaNac=" + oUsuario.getsFechaNacimiento() + "&txtPais=" + oUsuario.getiPais() + "&txtMonedas=" + oUsuario.getiMonedas() + "&txtAdministrador=" + oUsuario.isBoAdmin();
        Log.v("babu",sParametros);
        client.get(sUrl + sParametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Toast.makeText(Registro.this, sParametros, Toast.LENGTH_LONG).show();
                Intent i = new Intent(Registro.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //Toast.makeText(Registro.this, statusCode, Toast.LENGTH_LONG).show();


                if(statusCode == 302){
                    Toast.makeText(Registro.this, "error de ser mongolo", Toast.LENGTH_LONG).show();
                }

                if(statusCode == 500){
                    Toast.makeText(Registro.this, "SERVER ERROR", Toast.LENGTH_LONG).show();
                }






            }
        });
    }*/

    /*private void ejecutarInsert(LAVERDADERA oVERDAD) {
        String sUrl = "http://well-played.infinityfreeapp.com/pruebas_Miguel/ins-coche.php?PORFAVOR=DIOSMIO2";
        //String sParametros = "PORFAVOR="+oVERDAD.getPORFAVOR();

        client.post(sUrl, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Toast.makeText(Registro.this, "INSERTADO", Toast.LENGTH_LONG).show();
                Intent i = new Intent(Registro.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //Toast.makeText(Registro.this, statusCode, Toast.LENGTH_LONG).show();


                if(statusCode == 302){
                    Toast.makeText(Registro.this, "error de ser mongolo", Toast.LENGTH_LONG).show();
                }

                if(statusCode == 500){
                    Toast.makeText(Registro.this, "SERVER ERROR", Toast.LENGTH_LONG).show();
                }






            }
        });
    }*/

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

    private LAVERDADERA agregarLaVerdad() {
        LAVERDADERA oVERDAD = new LAVERDADERA();

        oVERDAD.setPORFAVOR("diosmio123");
        return oVERDAD;
    }


    public void onClickLogin(View v){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }

    private void ejecutarInsert(LAVERDADERA oVERDAD){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://well-played.infinityfreeapp.com/pruebas_Miguel/ins-coche.php?", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Usuario creado", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("PORFAVOR",oVERDAD.getPORFAVOR());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }



}