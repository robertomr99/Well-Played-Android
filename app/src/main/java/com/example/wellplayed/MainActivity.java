package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,0,0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        openFragment(new initFragment());
        
    }
    private void openFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()){
            case R.id.mnuPerfil: openFragment(new perfilFragment());break;
            case R.id.mnuMisJuegos: openFragment(new misJuegosFragment()); break;
            case R.id.mnuMisEquipos: openFragment(new misEquiposFragment()); break;
            case R.id.mnuMisPartidos: openFragment(new misPartidosFragment()); break;
            case R.id.mnuSoporte: openFragment(new soporteFragment()); break;
            case R.id.mnuAjustes: openFragment(new ajustesFragment()); break;
            case R.id.mnuCerrarSesion: salir(); break;
        }
        return false;
    }

    private void salir() {
        borrarPreferencias();
        Intent intentLogin = new Intent(this, Login.class);
        startActivity(intentLogin);

    }


    public  void borrarPreferencias(){
        Login.preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String sUsuario = "";
        String sPass = "";

        SharedPreferences.Editor editor = Login.preferences.edit();
        editor.putString("user", sUsuario);
        editor.putString("pass", sPass);

        editor.commit();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}