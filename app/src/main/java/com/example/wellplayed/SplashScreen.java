package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) ; // Para que se vea fullscreen
        setContentView(R.layout.activity_splash_screen);

        // Animaciones

        Animation ani1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation ani2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView txtDe = findViewById(R.id.txtDe);
        TextView txtTituloApp = findViewById(R.id.txtTituloApp);
        ImageView imgApp = findViewById(R.id.imgViewApp);

        txtDe.setAnimation(ani2);
        txtTituloApp.setAnimation(ani2);
        imgApp.setAnimation(ani1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, Login.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }



}