package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Producto;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Cuenta extends AppCompatActivity {


    Producto oProductoLogo = new Producto();
    Producto oProductoBanner = new Producto();
    public static String sNombreUser;
    Button btnActualizarPerfil;
    EditText txtUpdateUser,txtUpdatePass,txtUpdateConfirmPass;
    ImageButton imgBtnLogoUser,imgBtnBannerUser;
    ImageView imgLogoUser, imgBannerUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        sNombreUser = MainActivity.oUsuario.getsUser();
        btnActualizarPerfil = findViewById(R.id.btnActualizarPerfil);
        txtUpdateUser = findViewById(R.id.txtNombreUsuarioUPD);
        txtUpdatePass = findViewById(R.id.txtPasswordUPD);
        txtUpdateConfirmPass = findViewById(R.id.txtPasswordUPD2);
        imgBtnLogoUser = findViewById(R.id.btnEditarLogoUsuario);
        imgBtnBannerUser = findViewById(R.id.btnEditarBannerUsuario);
        imgLogoUser = findViewById(R.id.imgLogoUser);
        imgBannerUser = findViewById(R.id.imgBannerUser);
        traerDatosUser();


    }

    public void actualizarPerfil(View v){
        Log.d("producto",oProductoLogo.toString());
        String sUrl = Utils.hosting + "usuario/upd-user.php?txtUser=" + txtUpdateUser.getText() + "&txtPassword=" + txtUpdatePass.getText() + "&txtUsuario=" + sNombreUser;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha podido actualizar", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
                        Intent intentMain = new Intent(this, MainActivity.class);
                        Log.d("Ssssssssssssssssssssssssssssssssssssss",txtUpdateUser.getText().toString());
                        intentMain.putExtra("nombreUser",txtUpdateUser.getText().toString());
                        startActivity(intentMain);
                    }
                }
                , volleyError -> {
            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));

    }

    /*public void actualizarPerfil2(Producto oProductoBanner, Producto oProductoLogo){
        String sUrl = Utils.hosting + "usuario/upd-user.php?txtUser=" + txtUpdateUser.getText() + "&txtPassword=" + txtUpdatePass.getText() + "&txtLogo=" + oProductoLogo.getsFoto() + "&txtBanner=" + oProductoBanner.getsFoto();

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha podido actualizar", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "usuario actualizado correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
                , volleyError -> {
            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));

    }*/

    public void traerDatosUser(){
        String sUrl = Utils.hosting + "usuario/get-user.php?txtUsuario=" + sNombreUser;


        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Usuario oUsuario;
                        oUsuario = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());
                        setearValores(oUsuario);

                    }
                }
                , volleyError -> {
            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));

    }

    private void setearValores(Usuario oUsuario) {
        Glide.with(this).load(oUsuario.getsFoto()).circleCrop().into(imgLogoUser);
        Glide.with(this).load(oUsuario.getsBanner()).into(imgBannerUser);
        txtUpdateUser.setHint(oUsuario.getsUser());
        txtUpdatePass.setHint(oUsuario.getsPassword());
        txtUpdateConfirmPass.setHint(oUsuario.getsPassword());

    }

    public void cambiarLogoUser(View view) {
        Intent intentLogin = new Intent(this, Logos.class);
        intentLogin.putExtra("nombreUser", sNombreUser);
        startActivityForResult(intentLogin, 1);
    }

    public void cambiarBannerUser(View view) {
        Intent intentLogin = new Intent(this, Banner.class);
        intentLogin.putExtra("banner","banner");
        startActivityForResult(intentLogin, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode != RESULT_CANCELED) {
            if (resultCode == Activity.RESULT_OK) {
                oProductoLogo = (Producto) data.getExtras().get("producto");
                oProductoBanner = (Producto) data.getExtras().get("productoBanner");
                if(requestCode == 2){
                    Glide.with(this).load(oProductoBanner.getsFoto()).into(imgBannerUser);
                    updBanner(oProductoBanner);
                }else{
                    Glide.with(this).load(oProductoLogo.getsFoto()).circleCrop().into(imgLogoUser);
                    Log.d("adawdkajnwdhakjldhajkdwhadhkajdhwajkhdkjajdkahjkwdakjhwdawd",oProductoLogo.toString());
                    updLogo(oProductoLogo);
                }



                // el parametro intent data lo que hace es



            }
        } else {
            Toast.makeText(this, "No se ha podido añadir el avatar seleccionado", Toast.LENGTH_SHORT).show();

        }
    } //onActivityResult

    private void updLogo(Producto oProductoLogo) {
        String sUrl = Utils.hosting + "usuario/upd-logo.php?txtLogo=" + oProductoLogo.getsFoto() + "&txtUser=" + sNombreUser;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", sUrl);
                    if (s.equals("")) {
                        Toast.makeText(this, "No se ha actualizado", Toast.LENGTH_SHORT).show();
                    }
                }
                , volleyError -> {
            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }

    private void updBanner(Producto oProductoBanner) {
        String sUrl = Utils.hosting + "usuario/upd-banner.php?txtBanner=" + oProductoBanner.getsFoto() + "&txtUser=" + sNombreUser;

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    }
                }
                , volleyError -> {
            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }


}