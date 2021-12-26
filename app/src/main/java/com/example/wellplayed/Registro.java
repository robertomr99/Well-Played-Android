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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecurityPermission;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Registro extends AppCompatActivity {

    EditText txtUsuario,txtEmail,txtContrasenia,txtConfirmarContrasenia,txtFechaNacimiento;
    Spinner spinPaises;
    Button btnCrearCuenta;
    LAVERDADERA laverdad;
    List<LAVERDADERA> lstVerdad;
    String hosting = "http://wellplayed.atwebpages.com/";
    //String sUrl = "http://well-played.infinityfreeapp.com/pruebas_Miguel/ins-coche.php?";


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
           crearInsert();

        });


    }

    public class consultarDatos extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to download the requested page.";
            }
        }

        protected void onPostExecute(String result) {

            JSONArray ja = null;
            try {
                ja = new JSONArray(result);
                txtUsuario.setText(ja.getString(1));
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

    private void crearInsert() {
        String sUrl = hosting + "ins-usuario.php?PORFAVOR=DIOSMIO6";

        Toast.makeText(getApplicationContext(), sUrl,Toast.LENGTH_SHORT).show();
        Log.d("ALACID","a");
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_LONG).show();
                    }else{
                        Log.d("ALACID", s);

                       //coche = new Gson().fromJson(s,new TypeToken<Coche>(){}.getType());
                        //mostrarEste();
                    }
                }


                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));
    }

    private void obtenerEste() {
        String sUrl = hosting + "get-coche.php?txtId=1";
        Log.d("alacide", sUrl);
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    }else{
                        Log.d("alacide", sUrl);
                        Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();
                        laverdad = new Gson().fromJson(s,new TypeToken<LAVERDADERA>(){}.getType());
                        Toast.makeText(getApplicationContext(), "hola", Toast.LENGTH_SHORT).show();
                        mostrarEste();
                    }
                }


                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));
    }

    private void mostrarEste() {

        txtUsuario.setText(laverdad.toString());
    }

    public class cargarDatos extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            // params comes from the execute() call: params[0] is the url.
            try {
                return downloadUrl(urls[0]);
            } catch (IOException e) {
                return "Unable to download the requested page.";
            }
        }

        protected void onPostExecute(String result) {
           Toast.makeText(getApplicationContext(), "creado correctamente",Toast.LENGTH_LONG).show();
        }
    }


    private String downloadUrl(String myurl)throws IOException {
        InputStream is = null;
        myurl = myurl.replace(" ", "%20");
        int length = 500;

        try{
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);

            conn.connect();

            int iResponse = conn.getResponseCode();
            Log.d("respuesta", "la respuesta es: " + iResponse);
            is = conn.getInputStream();

            String contentAsString = readIt(is,length);
            return contentAsString;

        }finally {
           if(is != null){
               is.close();
           }
        }
    }

    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException{
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
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