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
import java.util.Properties;

public class restartPass extends AppCompatActivity {
    String sCorreo, sPass;
    EditText txtEmailRestart;
    Button btnRestartPass;
    Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restart_pass);
        txtEmailRestart = findViewById(R.id.txtEmailRestart);
        btnRestartPass = findViewById(R.id.btnRestartPass);

        sCorreo = "appwellplayed@gmail.com";
        sPass = "20WellPlayed21";




    }

    public void restartPass(View v) {

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
            message.setContent("Has socilitado un cambio de contraseña, haga click en el siguiente enlace para restablecerla/n" +
                    "Token necesario para restablecer contraseña: " +,"text/html;charset=utf-8");
            Transport.send(message);




        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}