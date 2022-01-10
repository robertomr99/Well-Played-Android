package com.example.wellplayed;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class perfilFragment extends Fragment {


    public perfilFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        getUser(view);
        return view;
    }

    public void getUser(View view) {
        String sUrl = Utils.hosting + "usuario/get-user.php?txtUsuario=" + MainActivity.oUsuario.getsUser();
        Volley.newRequestQueue(getContext()).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    Log.d("vacio", s);
                    if (s.equals("")) {
                        Toast.makeText(getContext(), "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        MainActivity.oUsuario = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());
                        extraerObjetoUsuario(MainActivity.oUsuario,view);

                    }
                }
                , volleyError -> {
            Log.d("ALACID", volleyError.getCause().toString());
        }
        ));
    }

    // Seteamos los datos del usuario extraido en el fragment de perfil

    private void extraerObjetoUsuario(Usuario oUsuario, View view) {
        TextView lblUsuario,lblEmail, lblFechaNacimiento, lblPais;
        Resources res = getResources();  // Extrapolamos recursos a una variable para poder acceder a su contenido.
        String[] sPaises = res.getStringArray(R.array.paises);
        lblUsuario = view.findViewById(R.id.lblRUsuario);
        lblUsuario.setText(oUsuario.getsUser().toLowerCase());
        lblEmail = view.findViewById(R.id.lblREmail);
        lblEmail.setText(oUsuario.getsEmail().toLowerCase());
        lblFechaNacimiento = view.findViewById(R.id.lblRFechaNacimiento);
        lblFechaNacimiento.setText(oUsuario.getsFechaNacimiento());
        lblPais = view.findViewById(R.id.lblRPais);
        lblPais.setText(sPaises[oUsuario.getiPais()]);
    }
}