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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo;

public class UnirseEquipoAdapter extends RecyclerView.Adapter<UnirseEquipoAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
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
        //String sMiembros = ListadoEquipos.lstEquipos.get(position).get

        holder.lblNombre.setText(sNombre);
        //holder.lblMiembros.setText(sMiembros);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));

    }


    public int getItemCount() {
        return ListadoEquipos.lstEquipos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblNombre;
        TextView lblMiembros;
        ImageView imageViewEquipo;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMiembros = itemView.findViewById(R.id.lblUMiembros);
            lblNombre = itemView.findViewById(R.id.lblUNombreEquipo);
            imageViewEquipo = itemView.findViewById(R.id.imgViewUEquipo);
            cv = itemView.findViewById(R.id.cardViewUEquipos);
        }
    }
}