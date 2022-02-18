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

import de.hdodenhof.circleimageview.CircleImageView;

public class PartidoDetalleAdapter extends RecyclerView.Adapter<PartidoDetalleAdapter.ViewHolder> implements View.OnClickListener {


    PartidoDetalleAdapterInterface pdInterface;
    LayoutInflater inflater;
    Context context;
    public static String sNombreUser = MainActivity.oUsuario.getsUser();
    private View.OnClickListener listener;

    // A las interfaces siempre se las pasa el objeto , no la vista
    public interface PartidoDetalleAdapterInterface {

    }

    public PartidoDetalleAdapter(Context context, PartidoDetalleAdapterInterface pdInterface) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.pdInterface = pdInterface;
    }

    public PartidoDetalleAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
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
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_partido_detalle_adapter, parent, false);

        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull PartidoDetalleAdapter.ViewHolder holder, int position) {

        Usuario oUsuario = PartidoDetalle.lstUsuarios.get(position); // Instanciamos el objeto de la lista con la posicion

        Glide.with(context).load(oUsuario.getsFoto()).circleCrop().into(holder.imageViewUsuario);
        holder.lblNombre.setText(oUsuario.getsUser());
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
    }


    public int getItemCount() {
        return PartidoDetalle.lstUsuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblNombre;
        CircleImageView imageViewUsuario;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombreUsuarioPartidoDetalle);
            imageViewUsuario = itemView.findViewById(R.id.imgViewUsuarioPartidoDetalle);
            cv = itemView.findViewById(R.id.cardViewPartidoDetalle);
        }
    }

}