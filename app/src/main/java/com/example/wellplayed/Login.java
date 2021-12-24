package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtPass;
    CheckBox checkBoxRecuerdame;
    String sUserAdmin = "admin";
    String sPassAdmin = "admin";
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



    @Override public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onClickRegistro (View v){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void onClickIniciarSesion(View v){

        if(loginAdmin(txtUsuario, txtPass)){
            if(checkBoxRecuerdame.isChecked()){
                guardarPreferencias();
            }
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();

        }

    }

    public boolean loginAdmin(EditText txtUsuario, EditText txtPass){
        boolean boAdmin = false;

        if(txtUsuario.getText().toString().equals(sUserAdmin) && txtPass.getText().toString().equals(sPassAdmin)){
            boAdmin = true;
        }

        return boAdmin;
    }

    public void guardarPreferencias(){
        preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String sUsuario = txtUsuario.getText().toString();
        String sPass = txtPass.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", sUsuario);
        editor.putString("pass", sPass);

        editor.commit();
    }

    public void cargarPreferencias(){
        preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String sUsuario = preferences.getString("user","");
        String sPass = preferences.getString("pass","");

        if(sUsuario.equals(sUserAdmin) && sPass.equals(sPassAdmin)){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }



}







