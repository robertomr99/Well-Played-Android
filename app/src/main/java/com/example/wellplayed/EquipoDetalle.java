package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Equipo_Juego;
import com.example.wellplayed.model.Equipo_Usuario;
import com.example.wellplayed.model.Juego;
import com.example.wellplayed.model.Usuario;
import com.example.wellplayed.model.Usuario_Juego;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.Inflater;

public class EquipoDetalle extends AppCompatActivity {


    TextView lblNombreDetalle, lblVictoriasDetalle, lblDerrotasDetalle, lblWinRateDetalle;
    ImageView imgViewEquipoDetalle;
    public static Equipo oEquipo;
    public static Equipo_Usuario oEquipoUsuario;
    Spinner spinnerJuegos;
    Integer iIdJuego;
    ArrayList<Juego> lstJuegos;
    ArrayList<String> lstNombreJuegos = new ArrayList<String>();
    RecyclerView rv;
    UsuariosAdapter usuariosAdapter;
    ImageButton imgBtnAdminUserDetalle;
    Button btnEliminarSalirEquipoDetalle;

    public static Integer iIdEquipoJuego;
    public static LayoutInflater inflaterDetalle;
    public static AlertDialog.Builder dialogBuilder;
    public static AlertDialog dialog;
    public static Button btnSI, btnNO;
    public static EquipoDetalle context;
    public static String sNombreUser, sNombreCreador;

    Usuario oUsuario;


    public EquipoDetalle() {
        context = this;
    }

    public static EquipoDetalle getInstance() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intentEquipoJuego();
        sNombreUser = MainActivity.oUsuario.getsUser();
        comprobarCreadorEquipo();
        setContentView(R.layout.activity_equipo_detalle);
        imgViewEquipoDetalle = findViewById(R.id.imgViewEquipoDetalle);
        lblNombreDetalle = findViewById(R.id.lblNombreDetalle);
        lblVictoriasDetalle = findViewById(R.id.lblRVictoriasEquipo);
        lblDerrotasDetalle = findViewById(R.id.lblRDerrotasEquipo);
        lblWinRateDetalle = findViewById(R.id.lblRWinRateEquipo);
        imgBtnAdminUserDetalle = findViewById(R.id.imgBtnAdminUserDetalle);
        btnEliminarSalirEquipoDetalle = findViewById(R.id.btnEliminarEquipoDetalle);

        btnEliminarSalirEquipoDetalle.setOnClickListener(view -> {
            if (sNombreUser.equals(sNombreCreador)) {
                delEquipo();
            } else {
                salirEquipo();
            }
        });

        rv = findViewById(R.id.recyclerViewUserEquipo);
        getUsuariosEquipo();

        Log.d("Usuario", MainActivity.oUsuario.toString());

        imgBtnAdminUserDetalle.setOnClickListener(view -> {
            view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clickanimation));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cambiarListaPendiente();
        });

        spinnerJuegos = findViewById(R.id.spinnerJuego);
        lstEquipoJuego();
        spinnerJuegos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                devolverProducto();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        oEquipo = ListadoEquipos.lstEquipos.get(ListadoEquipos.iEquipoSelected);
        Glide.with(getApplicationContext()).load(oEquipo.getsFoto()).circleCrop().into(imgViewEquipoDetalle);
        lblNombreDetalle.setText("" + oEquipo.getsNombre());
    }


    @Override
    protected void onResume() {
        super.onResume();
        comprobarCreadorEquipo();
        getUsuariosEquipo();
    }

    public void PopupEliminar(Usuario oUsuario) {

        dialogBuilder = new AlertDialog.Builder(context);
        final View PopupEliminarUsuario = getLayoutInflater().inflate(R.layout.popupusuario, null);
        btnSI = (Button) PopupEliminarUsuario.findViewById(R.id.btnSiEliminar);
        btnNO = (Button) PopupEliminarUsuario.findViewById(R.id.btnNoEliminar);

        dialogBuilder.setView(PopupEliminarUsuario);
        dialog = dialogBuilder.create();
        dialog.setCancelable(false); // Para que tenga que pulsar una opcion si o si (btn atras del movil)
        dialog.setCanceledOnTouchOutside(false); // Para que tenga que pulsar una opcion si o si (en la pantalla)
        dialog.show();

        btnSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarEquipoUsuario();
                restarMiembros(context,iIdEquipoJuego);
                Toast.makeText(EquipoDetalle.this, "Usuario eliminado con éxito", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        btnNO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EquipoDetalle.this, "No se elimina", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }


    private void devolverProducto() {
        switch (String.valueOf(spinnerJuegos.getItemAtPosition(spinnerJuegos.getSelectedItemPosition()))) {
            case "LOL":
                iIdJuego = 1;
                break;
            case "VALORANT":
                iIdJuego = 2;
                break;
            case "CSGO":
                iIdJuego = 3;
                break;
            case "FIFA":
                iIdJuego = 4;
                break;
        }
        getEquipoJuego();
    }

    public void setearDatos(Equipo_Juego oEquipoJuego) {
        Log.d("Rob", oEquipoJuego.toString());

        lblVictoriasDetalle.setText("" + oEquipoJuego.getiVictorias());
        lblDerrotasDetalle.setText("" + oEquipoJuego.getiDerrotas());
        lblWinRateDetalle.setText("" + oEquipoJuego.getfWinRate());
        setearColoresWinRate(oEquipoJuego);
    }

    public void restarMiembros(Context context, int iIdEquipo) {
        String sUrl = Utils.hosting + "equipo/restar-miembros.php?txtEquipo="+iIdEquipo;
        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                    } else {
                        finish();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void eliminarEquipoUsuario() {
        String sUrl = Utils.hosting + "equipo-usuario/eliminar-equipo-usuario.php?txtUsuario=" + sNombreUser + "&txtEquipo=" + iIdEquipoJuego;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("Rob", s);
                    if (s.equals("null")) {
                        Toast.makeText(this, "Error al eliminar el usuario", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario eliminado con éxito", Toast.LENGTH_LONG).show();
                        getUsuariosEquipo();
                    }
                }
                , volleyError -> {

            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }

    public void salirEquipo() {
        String sUrl = Utils.hosting + "equipo-usuario/eliminar-equipo-usuario.php?txtUsuario=" + sNombreUser + "&txtEquipo=" + iIdEquipoJuego;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("Rob", s);
                    if (s.equals("null")) {
                        Toast.makeText(this, "no te has podido salir del equipo", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "te has salido del equipo", Toast.LENGTH_LONG).show();
                        restarMiembros(this,iIdEquipoJuego);
                    }
                }
                , volleyError -> {

            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }

    public void delEquipo(){
        String sUrl = Utils.hosting + "equipo/del-equipo.php?txtEquipo=" + iIdEquipoJuego;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    if (s.equals("null")) {
                        Toast.makeText(this, "Error al eliminar el equipo", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Equipo eliminado con éxito", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                , volleyError -> {

            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }


    public  void comprobarCreadorEquipo() {
        String sUrl = Utils.hosting + "equipo-usuario/comprobar-creador.php?txtUsuario=" + sNombreUser + "&txtEquipo=" + iIdEquipoJuego;

        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    if (s.equals("")) {
                        Toast.makeText(context, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean boCreador = false;
                        Log.d("Rob", sUrl);
                        oEquipoUsuario = new Gson().fromJson(s, new TypeToken<Equipo_Usuario>() {
                        }.getType());
                        ocultarElementos();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void ocultarElementos() {
        if (oEquipoUsuario.getiCreador() == 0) {
            imgBtnAdminUserDetalle.setVisibility(View.GONE);
            btnEliminarSalirEquipoDetalle.setText(R.string.btn_SalirEquipo);
        } else {
            btnEliminarSalirEquipoDetalle.setText(R.string.btn_EliminarEquipo);
        }
    }

    public void getUsuariosEquipo() {
        String sUrl = Utils.hosting + "equipo-usuario/lst-usuarios-equipo.php?txtEquipo=" + iIdEquipoJuego;
        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        ListadoUsuarios.lstUsuarios = new Gson().fromJson(s, new TypeToken<List<Usuario>>() {
                        }.getType());
                        mostrarUsuarios();
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }

        ));

    }


    public void getEquipoJuego() {
        String sUrl = Utils.hosting + "equipo-juego/get-equipo-juego.php?txtEquipo=" + iIdEquipoJuego + "&txtJuego=" + iIdJuego;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        Equipo_Juego oEquipoJuego;
                        oEquipoJuego = new Gson().fromJson(s, new TypeToken<Equipo_Juego>() {
                        }.getType());
                        setearDatos(oEquipoJuego);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void lstEquipoJuego() {
        String sUrl = Utils.hosting + "equipo-juego/lst-equipos-juegos.php?txtEquipo=" + iIdEquipoJuego;

        Volley.newRequestQueue(getApplicationContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        lstJuegos = new Gson().fromJson(s, new TypeToken<List<Juego>>() {
                        }.getType());
                        rellenarSpinnerJuegos(lstJuegos);
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    private void rellenarSpinnerJuegos(List<Juego> lstJuegos) {

        for (int i = 0; i < lstJuegos.size(); i++) {
            lstNombreJuegos.add(lstJuegos.get(i).getsNombre());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(EquipoDetalle.this, android.R.layout.simple_spinner_dropdown_item, lstNombreJuegos);
        spinnerJuegos.setAdapter(adapter);
        devolverProducto();
    }

    private void mostrarUsuarios() {
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
    }


    private void cambiarListaPendiente() {
        Intent iListaPendientes = new Intent(getApplicationContext(), PeticionesEquipo.class);
        iListaPendientes.putExtra("Equipo", iIdEquipoJuego);
        startActivity(iListaPendientes);
    }


    private void intentEquipoJuego() {
        try {
            if (getIntent().hasExtra("Equipo")) {
                Equipo oEquipo;

                oEquipo = (Equipo) getIntent().getSerializableExtra("Equipo");
                sNombreCreador = getIntent().getStringExtra("sNombreCreador");
                iIdEquipoJuego = oEquipo.getiIdEquipo();
                Log.d("IDEQUIPO", iIdEquipoJuego.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setearColoresWinRate(Equipo_Juego oEquipoJuego) {
        if (oEquipoJuego.getfWinRate() >= 50 && oEquipoJuego.getfWinRate() <= 60) {
            lblWinRateDetalle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.NaranjaWinRate));
        } else if (oEquipoJuego.getfWinRate() > 60) {
            lblWinRateDetalle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.VerdeWinRate));
        } else {
            lblWinRateDetalle.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.RojoWinRate));
        }
    }
}
