package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.wellplayed.model.Usuario;

public class PartidoDetalle extends AppCompatActivity {

    ImageView imgEquipo1, imgEquipo2;
    ImageButton imgBtnWin, imgBtnLose;
    RecyclerView rvEquipo1;
    RecyclerView rvEquipo2;
    PartidoDetalleAdapter partidoDetalleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partido_detalle);

        imgEquipo1 = findViewById(R.id.imgEquipo1);
        imgEquipo2 = findViewById(R.id.imgEquipo2);
        imgBtnWin = findViewById(R.id.imgBtnWin);
        imgBtnLose = findViewById(R.id.imgBtnLose);
        rvEquipo1 = findViewById(R.id.rvEquipo1);
        rvEquipo2 = findViewById(R.id.rvEquipo2);


        imgBtnWin.setOnClickListener(view -> {

        });
    }

    private void mostrarEquipo1() {
        rvEquipo1.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        partidoDetalleAdapter = new PartidoDetalleAdapter(getApplicationContext());
        rvEquipo1.setAdapter(partidoDetalleAdapter);
    }

   /* private void mostrarUsuarios() {
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        usuariosAdapter = new UsuariosAdapter(getApplicationContext(), new UsuariosAdapter.UsuarioAdapterInterface( // Para implementar la interfaz
        ) {
            @Override
            public void deleteUser(Usuario oUsuario) {
                // Booleano para hacer cambios de ese objeto en concreto
                PopupEliminar(oUsuario);
            }
        });
        rv.setAdapter(usuariosAdapter);
    }*/

}