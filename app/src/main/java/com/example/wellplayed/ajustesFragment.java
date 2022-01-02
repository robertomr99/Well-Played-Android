package com.example.wellplayed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class ajustesFragment extends Fragment {



    public ajustesFragment() {
        // Required empty public constructor
    }



    public void onCreate(Bundle savedInstanceState, View view) {
        super.onCreate(savedInstanceState);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_ajustes, container, false);
        abrirCuenta(vista);
        abrirInformacion(vista);
        cambiarTema(vista);
        return vista;
    }


    public void abrirCuenta(View view){
       view.findViewById(R.id.lblCuenta).setOnClickListener(v->{
            Intent ventana = new Intent(getContext(),Cuenta.class);
            startActivity(ventana);
        });
    }


    public void abrirInformacion(View view){
        view.findViewById(R.id.lblInformacion).setOnClickListener(v->{
            Intent ventana = new Intent(getContext(),Informacion.class);
            startActivity(ventana);
        });
    }


    public void cambiarTema(View view){
        view.findViewById(R.id.swBtnTema).setOnClickListener( v->{
            if(true){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }






}