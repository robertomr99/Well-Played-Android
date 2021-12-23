package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPassword);




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
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();

        }

    }

    public boolean loginAdmin(EditText txtUsuario, EditText txtPass){
        boolean boAdmin = false;

        if(txtUsuario.getText().toString().equals(txtPass.getText().toString())){
            boAdmin = true;
        }

        return boAdmin;
    }

}