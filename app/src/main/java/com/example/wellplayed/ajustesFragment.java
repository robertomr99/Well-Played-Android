package com.example.wellplayed;

import android.content.Intent;
import android.os.Bundle;

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


/*
    public void onCreate(Bundle savedInstanceState, View view) {
        super.onCreate(savedInstanceState);


        view.findViewById(R.id.lblCuenta).setOnClickListener(v->{
        Intent ventana = new Intent(ajustesFragment.this,);
        startActivity(ventana);
        });





    }
    */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ajustes, container, false);
    }







}