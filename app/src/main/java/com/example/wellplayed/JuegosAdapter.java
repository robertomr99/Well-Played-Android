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

public class JuegosAdapter extends RecyclerView.Adapter<JuegosAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
    private View.OnClickListener listener;

    public JuegosAdapter(Context context){
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

    public JuegosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_juegos_adapter, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull JuegosAdapter.ViewHolder holder, int position) {
        String sNombre = ListadoJuegos.lstJuegos.get(position).getsNombre();
        int iFoto = ListadoJuegos.lstJuegos.get(position).getiFoto();

        holder.lblNombreJuego.setText(sNombre);
        holder.imgViewJuego.setImageResource(iFoto);
        //holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));

    }


    public int getItemCount() {
        return ListadoJuegos.lstJuegos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblNombreJuego;
        ImageView imgViewJuego;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombreJuego = itemView.findViewById(R.id.lblNombreJuego);
            imgViewJuego = itemView.findViewById(R.id.imgViewJuego);
        }
    }
}