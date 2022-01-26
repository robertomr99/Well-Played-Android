package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Properties;

public class restartPass extends AppCompatActivity {
    String sCorreo, sPass;
    EditText txtEmailRestart;
    Button btnRestartPass;
    TextView lblInfoRestartPass;
    Session session;
    public static Usuario oUsuario = new Usuario();
    public static Usuario oUsuarioPassRestart = new Usuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_pass);
        txtEmailRestart = findViewById(R.id.txtEmailRestart);
        btnRestartPass = findViewById(R.id.btnRestartPass);
        lblInfoRestartPass = findViewById(R.id.lblInfoRestartPass);
        sCorreo = "appwellplayed@gmail.com";
        sPass = "20WellPlayed21";


    }

    public void seleccionarToken(View v) {
        settearCorreoUser();
        String sUrl = Utils.hosting + "usuario/seleccionar-token.php?txtEmail=" + oUsuario.getsEmail();
        Log.d("alacide", sUrl);
        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getApplicationContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("alacide", sUrl);
                        oUsuarioPassRestart = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());

                        restartPassUser();
                    }
                }

                , volleyError -> {

            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));




    }

    public void settearCorreoUser(){
        oUsuario.setsEmail(txtEmailRestart.getText().toString());
    }

    private void restartPassUser() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.googlemail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        try {

         session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sCorreo,sPass);
                    }
                });


            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sCorreo));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtEmailRestart.getText().toString()));
            message.setSubject("Cambio de contraseña de usuario en wellplayed");
            message.setContent("<FONT SIZE = 3 COLOR = BLACK> Has socilitado un cambio de contraseña <br><br> " +
                    "<FONT SIZE = 4 COLOR = black>Token necesario para restablecer contraseña: </font><FONT COLOR = RED><H1><b>" + oUsuarioPassRestart.getsCodigo()+"</b></H1></font><br>" +
                    "Haga click en el siguiente enlace para restablecerla: <br>"
                    +"http://wellplayed.atwebpages.com/restart-pass.html </font>","text/html;charset=utf-8");
            Transport.send(message);

            Toast.makeText(this, "Correo enviado correctamente", Toast.LENGTH_LONG).show();
            finish();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

}