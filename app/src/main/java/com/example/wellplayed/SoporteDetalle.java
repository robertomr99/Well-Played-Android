package com.example.wellplayed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Soporte;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class SoporteDetalle extends AppCompatActivity {

    TextView lblSoporte;
    public static String sNombreUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soporte_detalle);
        lblSoporte = findViewById(R.id.lblTexto);
        sNombreUser = MainActivity.oUsuario.getsUser();
    }

    public void mostrarTickets() {
        // Log.d("objetoUsuario", MainActivity.oUsuario.getsUser());
        String sUrl = Utils.hosting + "soporte/lstTickets.php?txtUsuario=" + sNombreUser.toUpperCase();

        Volley.newRequestQueue(this).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(this, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("Rob", sUrl);
                        Soporte oSoporte = new Soporte();
                        oSoporte = new Gson().fromJson(s, new TypeToken<List<Soporte>>() {
                        }.getType());

                      //  mostrarData();

                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

 /*   private void mostrarUsuarios() {
      lblSoporte.setText(ListadoSoporte.lstTickets.get());
    }
*/
}