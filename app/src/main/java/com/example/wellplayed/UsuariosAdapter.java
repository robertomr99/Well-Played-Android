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

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder> implements View.OnClickListener {

    // A las interfaces siempre se las pasa el objeto , no la vista
    public interface UsuarioAdapterInterface {
        void deleteUser(Usuario oUsuario);
    }

    UsuarioAdapterInterface uaInterface;
    LayoutInflater inflater;
    Context context;
    private View.OnClickListener listener;


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
        String sNombre = oUsuario.getsUser();

        holder.imgBtnEliminarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uaInterface.deleteUser(oUsuario);
            }
        });

        holder.lblNombre.setText(sNombre);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
    }


    public int getItemCount() {
        return ListadoUsuarios.lstUsuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblNombre;
        ImageView imageViewUsuario;
        ImageButton imgBtnEliminarUsuario;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombreUsuario);
            imageViewUsuario = itemView.findViewById(R.id.imgViewUsuario);
            imgBtnEliminarUsuario = itemView.findViewById(R.id.imgBtnEliminarUsuario);
            cv = itemView.findViewById(R.id.cardViewUsuarios);

        }
    }
}