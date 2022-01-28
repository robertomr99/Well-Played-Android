package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Usuario;
import com.example.wellplayed.model.PassGenerator;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Login extends AppCompatActivity {
    EditText txtUsuario;
    EditText txtPass;
    CheckBox checkBoxRecuerdame;
    public static String SCORREOGOOGLE = "";
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    public static Usuario oUsuarioSalida, oUsuarioEntrada = new Usuario();
    public static SharedPreferences preferences;
    private GoogleSignInClient mGoogleSignInClient;
    private static Usuario oUsuarioGoogle = new Usuario();
    private static Usuario oUsuarioGoogle2;
    private FirebaseAuth mAuth;
    public static Login context;

    public Login() {
        context = this;
    }

    public static Login getInstance() {
        return context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        cargarPreferencias();

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPassword);
        checkBoxRecuerdame = findViewById(R.id.checkBoxRecuerdame);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();

    }

    public void signIn(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            SCORREOGOOGLE = user.getEmail();

                            Log.d("holaholahola", SCORREOGOOGLE);
                            oUsuarioGoogle.setsEmail(SCORREOGOOGLE);
                            // Sign in success, update UI with the signed-in user's information


                            String[] parts = SCORREOGOOGLE.split("@");
                            String sPart1 = parts[0];

                            oUsuarioGoogle.setsUser(sPart1);
                            comprobarUserGoogle();
                            oUsuarioGoogle.setsPassword(PassGenerator.getPassword());

                            Intent i = new Intent(Login.this, MainActivity.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onClickRegistro(View v) {
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void onClickIniciarSesion(View v) {
        loginApp();
    }

    public boolean comprobarUsuario() {
        boolean boCorrecto = false;
        if (oUsuarioSalida == null) {
            boCorrecto = false;
        } else if (oUsuarioSalida.getsUser().equals(oUsuarioEntrada.getsUser()) && oUsuarioSalida.getsPassword().equals(oUsuarioEntrada.getsPassword())) {
            boCorrecto = true;
        }

        return boCorrecto;
    }


    private void usuarioLogeado() {
        oUsuarioEntrada.setsUser(txtUsuario.getText().toString().toUpperCase());
        oUsuarioEntrada.setsPassword(txtPass.getText().toString());
    }

    public void comprobarUserGoogle() {

        String sUrl = Utils.hosting + "usuario/get-user.php?txtUsuario=" + oUsuarioGoogle.getsUser();
        Log.d("alacide", sUrl);
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("alacide", sUrl);
                        oUsuarioGoogle2 = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());

                        if (oUsuarioGoogle2 == null) {
                            Registro.insertUsuario(oUsuarioGoogle, false);
                        }
                    }
                }
                , volleyError -> {

            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }

    public void loginApp() {
        usuarioLogeado();
        String sUrl = Utils.hosting + "usuario/login-users.php?txtUsuario=" + oUsuarioEntrada.getsUser() + "&txtPass=" + oUsuarioEntrada.getsPassword();
        Log.d("alacide", sUrl);
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("alacide", sUrl);
                        oUsuarioSalida = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());

                        extraerObjetoUsuario();
                    }
                }
                , volleyError -> {

            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }

    private void extraerObjetoUsuario() {
        iniciarSesion();
    }

    private void iniciarSesion() {
        if (comprobarUsuario()) {
            if (checkBoxRecuerdame.isChecked()) {
                guardarPreferencias();
            }
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("User", oUsuarioSalida);
            startActivity(i);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void guardarPreferencias() {
        preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String sUsuario = txtUsuario.getText().toString();
        String sPass = txtPass.getText().toString();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user", sUsuario);
        editor.putString("pass", sPass);

        editor.commit();
    }

    public void cargarPreferencias() {
        preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        String sUsuario = preferences.getString("user", "");
        String sPass = preferences.getString("pass", "");

        if (!sUsuario.equals("") && !sPass.equals("")) {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("Name", sUsuario);
            startActivity(i);
        }
    }

    public void restartPassIntent(View v) {
        Intent i = new Intent(this, restartPass.class);
        startActivity(i);
    }


}



