package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
    private View.OnClickListener listener;

    public EquiposAdapter(Context context){
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

    public EquiposAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_equipos_adapter, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull EquiposAdapter.ViewHolder holder, int position) {
        String sNombre = ListadoEquipos.lstEquipos.get(position).getsNombre();
        int iFoto = ListadoEquipos.lstEquipos.get(position).getiFoto();

        holder.lblNombre.setText(sNombre);
        holder.imageViewEquipo.setImageResource(iFoto);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));

    }


    public int getItemCount() {
        return ListadoEquipos.lstEquipos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblNombre;
        ImageView imageViewEquipo;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombreEquipo);
            imageViewEquipo = itemView.findViewById(R.id.imagenEquipo);
            cv = itemView.findViewById(R.id.cardViewEquipos);
        }
    }
}