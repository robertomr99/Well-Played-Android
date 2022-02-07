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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ImageView imgBannerUser;
    public static Usuario oUsuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        View header = ((NavigationView) findViewById(R.id.navigationView)).getHeaderView(0);
        oUsuario = intentDataUsuario();
        recogerDatos();
        seleccionarFotoMonedas(header, oUsuario.getsUser());
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        openFragment(new initFragment());
    }


    @Override
    protected void onResume() {
        super.onResume();
        recogerDatos();
        View header = ((NavigationView) findViewById(R.id.navigationView)).getHeaderView(0);
        seleccionarFotoMonedas(header,oUsuario.getsUser());
    }

    public void recogerDatos(){

        if(getIntent().hasExtra("nombreUser")){
            oUsuario = new Usuario();
            String sNombreUser = getIntent().getStringExtra("nombreUser");
            oUsuario.setsUser(sNombreUser);
        }

    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.mnuPerfil:
                openFragment(new perfilFragment());
                break;
            case R.id.mnuMisJuegos:
                openFragment(new misJuegosFragment());
                break;
            case R.id.mnuMisEquipos:
                openFragment(new misEquiposFragment());
                break;
            case R.id.mnuMisPartidos:
                openFragment(new misPartidosFragment());
                break;
            case R.id.mnuTienda:
                openFragment(new tiendaFragment());
                break;
            case R.id.mnuSoporte:
                openFragment(new soporteFragment());
                break;
            case R.id.mnuAjustes:
                openFragment(new ajustesFragment());
                break;
            case R.id.mnuCerrarSesion:
                salir();
                break;
        }
        return false;
    }

    private void salir() {
        lstAllClear();
        borrarPreferencias();
        Intent intentLogin = new Intent(this, Login.class);
        startActivity(intentLogin);
    }

    public void lstAllClear() {
        ListadoEquipos.lstEquipos.clear();
        ListadoUsuarios.lstUsuarios.clear();
        ListadoProductos.lstProductos.clear();
        ListadoJuegos.lstJuegos.clear();
        oUsuario.setsUser("");
        misEquiposFragment.sNombreUser = "";
        misJuegosFragment.sNombreUser = "";
        addJuego.sNombreUser = "";
        EquipoDetalle.sNombreUser = "";
    }


    public void borrarPreferencias() {
        Login.preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();

        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FirebaseAuth.getInstance().signOut(); // importante si estamos usando firebase
                }
            }
        });
        String sUsuario = "";
        String sPass = "";

        SharedPreferences.Editor editor = Login.preferences.edit();
        editor.putString("user", sUsuario);
        editor.putString("pass", sPass);

        editor.commit();
    }

    // Setea el nombre del usuario logeado en el menú lateral

    private Usuario intentDataUsuario() {
        Usuario oUser = new Usuario();
        try {
            if (getIntent().hasExtra("Name")) {
                oUser.setsUser(getIntent().getStringExtra("Name"));
            } else {
                oUser = (Usuario) getIntent().getSerializableExtra("User");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oUser;
    }

    private void seleccionarFotoMonedas(View header,String sUsuario) {

        String sUrl = Utils.hosting + "usuario/get-user.php?txtUsuario=" + sUsuario;
        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("URL", sUrl);
                        Usuario oUserLleno = new Usuario();
                        oUserLleno = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());

                        Log.d("Funca ", oUserLleno.toString());
                        rellenarCabecera(header, oUserLleno);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void rellenarCabecera(View header,Usuario oUserLLeno){
        ((TextView) header.findViewById(R.id.lblNombreUsuario)).setText(oUserLLeno.getsUser().toUpperCase());
        Glide.with(getApplicationContext()).load(oUserLLeno.getsFoto()).into((CircleImageView
                ) header.findViewById(R.id.imgViewUsuario));
        Glide.with(getApplicationContext()).load(oUserLLeno.getsBanner()).into((ImageView) header.findViewById(R.id.imgBannerUserHeader));
        ((TextView) header.findViewById(R.id.lblMonedas)).setText(String.valueOf(oUserLLeno.getiMonedas()));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}