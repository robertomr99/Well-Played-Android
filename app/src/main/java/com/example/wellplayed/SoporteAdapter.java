package com.example.wellplayed;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Soporte;

import java.util.ArrayList;

public class SoporteAdapter extends RecyclerView.Adapter<SoporteAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
    String[] aStados = {"Enviado","En revision","Cerrado"};
    private View.OnClickListener listener;


    public SoporteAdapter(Context context) {
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

    public SoporteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_soporte_adapter, parent, false);
        view.setOnClickListener(this);
        return new SoporteAdapter.ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull SoporteAdapter.ViewHolder holder, int position) {
        Soporte oSoporte = ListadoSoporte.lstTickets.get(position); // Instanciamos el objeto de la lista con la posicion

        Integer iStatus = oSoporte.getiStatus();

        holder.lblStatus.setText(aStados[iStatus]+"    |");
        holder.lblAsunto.setText(oSoporte.getsAsunto());

        if(iStatus == 0){
            Glide.with(context).load(R.drawable.circuloverde).circleCrop().into(holder.imgViewStatus);
        }else if(iStatus == 1){
            Glide.with(context).load(R.drawable.circulonaranja).circleCrop().into(holder.imgViewStatus);
        }else{
            Glide.with(context).load(R.drawable.circulorojo).circleCrop().into(holder.imgViewStatus);


        }
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));

    }


    public int getItemCount() {
        return ListadoSoporte.lstTickets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblStatus,lblAsunto;
        ImageView imgViewStatus;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblStatus = itemView.findViewById(R.id.lblStatus);
            lblAsunto = itemView.findViewById(R.id.lblAsunto);
            imgViewStatus = itemView.findViewById(R.id.imgStatus);
            cv = itemView.findViewById(R.id.cardViewSoporte);
        }

    }

}
