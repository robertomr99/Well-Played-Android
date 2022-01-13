package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.wellplayed.model.Usuario_Juego;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.YouTubePlayerInitListener;

public class JuegosDetalle extends AppCompatActivity {

    TextView lblWinRateDetalle, lblVictoriaDetalle, lblDerrotaDetalle;
    Usuario_Juego oUsuario_Juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentUsuarioJuego();
        setContentView(R.layout.activity_juegos_detalle);

        YouTubePlayerView videoViewJuegoDetalle = findViewById(R.id.videoViewJuegoDetalle);
        getLifecycle().addObserver(videoViewJuegoDetalle);

        videoViewJuegoDetalle.initialize (new YouTubePlayerInitListener(){
            @Override
            public void onInitSuccess(@NonNull final YouTubePlayer inicializedyouTubePlayer) {
                inicializedyouTubePlayer.addListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady() {
                        inicializedyouTubePlayer.loadVideo(ListadoJuegos.lstJuegos.get(ListadoJuegos.iJuegoSelected).getsVideo(),0);
                    }
                });
            }
        },true);


        TextView lblDescripcionDetalle = findViewById(R.id.lblDescripcionDetalle);
        lblWinRateDetalle = findViewById(R.id.lblRWinRateDetalle);
        lblVictoriaDetalle = findViewById(R.id.lblRVictoriasDetalle);
        lblDerrotaDetalle = findViewById(R.id.lblRDerrotasDetallle);
        Button btnEliminarJuego = findViewById(R.id.btnEliminarJuegoDetalle);
        lblDescripcionDetalle.setText("" + ListadoJuegos.lstJuegos.get(ListadoJuegos.iJuegoSelected).getsDescripcion());

        lblWinRateDetalle.setText(String.valueOf(oUsuario_Juego.getfWinRate()));
        lblVictoriaDetalle.setText(String.valueOf(oUsuario_Juego.getiVictorias()));
        lblDerrotaDetalle.setText(String.valueOf(oUsuario_Juego.getiDerrotas()));

        btnEliminarJuego.setOnClickListener(v -> {

        });
    }

    private void intentUsuarioJuego() {
        try {
            if (getIntent().hasExtra("Usuario_Juego")) {
                oUsuario_Juego = (Usuario_Juego) getIntent().getSerializableExtra("Usuario_Juego");
                Log.d("Objeto", oUsuario_Juego.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}