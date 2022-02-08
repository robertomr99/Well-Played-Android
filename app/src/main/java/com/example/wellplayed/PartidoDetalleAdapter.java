package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Usuario;

public class PartidoDetalleAdapter extends RecyclerView.Adapter<PartidoDetalleAdapter.ViewHolder> implements View.OnClickListener {


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

    public PartidoDetalleAdapter(Context context) {
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

    public PartidoDetalleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_usuarios_adapter, parent, false);

        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull PartidoDetalleAdapter.ViewHolder holder, int position) {

        Usuario oUsuario = ListadoUsuarios.lstUsuarios.get(position); // Instanciamos el objeto de la lista con la posicion

        Glide.with(context).load(oUsuario.getsFoto()).circleCrop().into(holder.imageViewUsuario);

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