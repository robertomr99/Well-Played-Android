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
import com.example.wellplayed.model.Producto;

public class LogosAdapter extends RecyclerView.Adapter<LogosAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
    private View.OnClickListener listener;

    public LogosAdapter(Context context){
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
    public LogosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_logos_adapter, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull LogosAdapter.ViewHolder holder, int position) {

        Producto oProducto = ListadoProductos.lstProductos.get(position); // Instanciamos el objeto de la lista con la posicion

        Glide.with(context).load(oProducto.getsFoto()).circleCrop().into(holder.imgViewLogo);

        //.override es para poner tamaño personalizado.
    }


    public int getItemCount() {
        return ListadoProductos.lstProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgViewLogo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgViewLogo = itemView.findViewById(R.id.imgViewLogo);
        }
    }
}