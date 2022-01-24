package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UnirseEquipoAdapter extends RecyclerView.Adapter<UnirseEquipoAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
    public static final String sNombreUser = MainActivity.oUsuario.getsUser();
    private View.OnClickListener listener;

    public UnirseEquipoAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }


    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @NonNull

    public UnirseEquipoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_unirse_equipo_adapter, parent, false);
        view.setOnClickListener(this);
        return new UnirseEquipoAdapter.ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull UnirseEquipoAdapter.ViewHolder holder, int position) {
        Equipo oEquipo = ListadoEquipos.lstEquipos.get(position); // Instanciamos el objeto de la lista con la posicion

        Glide.with(context).load(oEquipo.getsFoto()).circleCrop().into(holder.imageViewEquipo);
        String sNombre = oEquipo.getsNombre();
        String sMiembros = String.valueOf(oEquipo.getiMiembros());

        holder.lblNombre.setText(sNombre);
        holder.imgBtnUnirseEquipo.setOnClickListener(v ->{
            seleccionarIdUser(sNombre);

        });

        holder.lblMiembros.setText(sMiembros);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));

    }


    public int getItemCount() {
        return ListadoEquipos.lstEquipos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblNombre;
        TextView lblMiembros;
        ImageView imageViewEquipo;
        ImageButton imgBtnUnirseEquipo;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMiembros = itemView.findViewById(R.id.lblUMiembros);
            lblNombre = itemView.findViewById(R.id.lblUNombreEquipo);
            imageViewEquipo = itemView.findViewById(R.id.imgViewUEquipo);
            imgBtnUnirseEquipo = itemView.findViewById(R.id.imgBtnUnirseEquipo);
            cv = itemView.findViewById(R.id.cardViewUEquipos);
        }

    }

    private void selectiIdEquipo(String sNombreEquipo, int iIdUsuario) {

        String sUrl = Utils.hosting + "equipo/select-idEquipo.php?txtEquipo="+sNombreEquipo;
        Log.d("selectIdEquipo",sUrl);
        Volley.newRequestQueue(context.getApplicationContext()).add(new StringRequest(Request.Method.GET,sUrl,
                s ->{
                    if(s.equals("null")){

                    }else{
                        Equipo oEquipo;
                        oEquipo = new Gson().fromJson(s, new TypeToken<Equipo>() {
                        }.getType());
                        prueba(oEquipo, iIdUsuario);
                    }
                }
                ,volleyError -> {

            Log.d("ALACID",volleyError.getCause().toString());
        }
        ));
    }

    private void seleccionarIdUser(String sNombreEquipo) {

        String sUrl = Utils.hosting + "usuario/select-idUser.php?txtUsuario="+sNombreUser;
        Volley.newRequestQueue(context).add(new StringRequest(Request.Method.GET, sUrl,
                s -> {
                    if (s.equals("")) {
                        Toast.makeText(context, "no se ha encontrado", Toast.LENGTH_SHORT).show();
                    } else {
                        Usuario oUsuarioId = new Usuario();
                        oUsuarioId = new Gson().fromJson(s, new TypeToken<Usuario>() {
                        }.getType());
                        selectiIdEquipo(sNombreEquipo, oUsuarioId.getiIdUsuario());
                    }
                }
                , volleyError -> {
            Log.d("Rob", volleyError.getCause().toString());
        }
        ));
    }

    public void prueba(Equipo oEquipo, int iIdUsuario){
        unirseEquipo.unirseEquipo(context.getApplicationContext(), oEquipo.getiIdEquipo(), iIdUsuario );
    }
}