package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Equipo_Usuario;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Locale;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder> implements View.OnClickListener {


    UsuarioAdapterInterface uaInterface;
    LayoutInflater inflater;
    Context context;
    public static String sNombreUser = MainActivity.oUsuario.getsUser();
    public static int iIdEquipoJuego;
    private View.OnClickListener listener;

    // A las interfaces siempre se las pasa el objeto , no la vista
    public interface UsuarioAdapterInterface {
        void deleteUser(Usuario oUsuario);
    }

    public UsuariosAdapter(Context context, UsuarioAdapterInterface uaInterface) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.uaInterface = uaInterface;
    }

    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull

    public UsuariosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_usuarios_adapter, parent, false);

        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull UsuariosAdapter.ViewHolder holder, int position) {

        Usuario oUsuario = ListadoUsuarios.lstUsuarios.get(position); // Instanciamos el objeto de la lista con la posicion

        Glide.with(context).load(oUsuario.getsFoto()).circleCrop().into(holder.imageViewUsuario);
        holder.imageViewLider.setVisibility(View.GONE);


        if (EquipoDetalle.sNombreCreador.equals(sNombreUser.toUpperCase())) {

            // CASO -> CREADOR

            if (EquipoDetalle.sNombreCreador.equals(oUsuario.getsUser())) {
                holder.imageViewLider.setVisibility(View.VISIBLE);
                holder.imgBtnEliminarUsuario.setVisibility(View.GONE);
            } else {
                holder.imgBtnEliminarUsuario.setVisibility(View.VISIBLE);
            }

        } else {

            // CASO -> USUARIO NORMAL

            if (EquipoDetalle.sNombreCreador.equals(oUsuario.getsUser())) {
                holder.imageViewLider.setVisibility(View.VISIBLE);
                holder.imgBtnEliminarUsuario.setVisibility(View.GONE);
            } else {
                holder.imgBtnEliminarUsuario.setVisibility(View.GONE);
            }
        }


        holder.imgBtnEliminarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uaInterface.deleteUser(oUsuario);
            }
        });

        holder.lblNombre.setText(oUsuario.getsUser());
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));


    }


    public int getItemCount() {
        return ListadoUsuarios.lstUsuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblNombre;
        ImageView imageViewUsuario, imageViewLider;
        ImageButton imgBtnEliminarUsuario;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombreUsuario);
            imageViewUsuario = itemView.findViewById(R.id.imgViewUsuario);
            imageViewLider = itemView.findViewById(R.id.imgVIewLider);
            imgBtnEliminarUsuario = itemView.findViewById(R.id.imgBtnEliminarUsuario);
            cv = itemView.findViewById(R.id.cardViewUsuarios);

        }
    }

}