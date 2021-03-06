package com.example.wellplayed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Juego;

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

        Juego oJuego = ListadoJuegos.lstJuegos.get(position); // Instanciamos el objeto de la lista con la posicion

        holder.lblNombreJuego.setText(oJuego.getsNombre());
        Glide.with(context).load(oJuego.getsFoto()).circleCrop().into(holder.imgViewJuego);

        //.override es para poner tamaño personalizado.
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