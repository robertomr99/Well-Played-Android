package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Login extends AppCompatActivity {
    EditText txtUsuario;
    EditText txtPass;
    CheckBox checkBoxRecuerdame;
    public static Usuario oUsuarioSalida, oUsuarioEntrada = new Usuario();
    public static SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarPreferencias();

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPassword);
        checkBoxRecuerdame = findViewById(R.id.checkBoxRecuerdame);

    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onClickRegistro(View v) {

        Intent i = new Intent(this, Registro.class);
        startActivity(i);

    }

    public void onClickIniciarSesion(View v) {
        loginApp();
    }

    public boolean comprobarUsuario() {
        boolean boCorrecto = false;
        if (oUsuarioSalida == null) {
            boCorrecto = false;
        } else if (oUsuarioSalida.getsUser().equals(oUsuarioEntrada.getsUser()) && oUsuarioSalida.getsPassword().equals(oUsuarioEntrada.getsPassword())) {
            boCorrecto = true;
        }

        return boCorrecto;
    }


    private void usuarioLogeado() {
        oUsuarioEntrada.setsUser(txtUsuario.getText().toString().toUpperCase());
        oUsuarioEntrada.setsPassword(txtPass.getText().toString().toUpperCase());

    }

    public void loginApp() {
        usuarioLogeado();
        String sUrl = Utils.hosting + "login-users.php?txtUsuario=" + oUsuarioEntrada.getsUser() + "&txtPass=" + oUsuarioEntrada.getsPassword();
        Log.d("alacide", sUrl);
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("alacide", sUrl);
                        oUsuarioSalida = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());

                        extraerObjetoUsuario();

                    }
                }

                , volleyError -> {

            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }

    private void extraerObjetoUsuario() {
        iniciarSesion();
    }

    private void iniciarSesion() {
        if (comprobarUsuario()) {
            if (checkBoxRecuerdame.isChecked()) {
                guardarPreferencias();
            }
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("User", oUsuarioSalida);
            startActivity(i);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarPreferencias() {
        preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String sUsuario = txtUsuario.getText().toString();
        String sPass = txtPass.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", sUsuario);
        editor.putString("pass", sPass);

        editor.commit();
    }

    public void cargarPreferencias() {
        preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String sUsuario = preferences.getString("user", "");
        String sPass = preferences.getString("pass", "");
        if (!sUsuario.equals("") && !sPass.equals("")) {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("Name", sUsuario);
            startActivity(i);
        }

    }

}







