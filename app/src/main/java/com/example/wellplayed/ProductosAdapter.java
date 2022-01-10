package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Producto;

public class ProductosAdapter extends AppCompatActivity {

    LayoutInflater inflater;
    Context context;
    private View.OnClickListener listener;

    public ProductosAdapter(Context context){
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
    public ProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_productos_adapter, parent, false);
        view.setOnClickListener((View.OnClickListener) this);
        return new ProductosAdapter.ViewHolder(view);
    }

    public void onBindViewHolder(@NonNull ProductosAdapter.ViewHolder holder, int position) {

        Producto oProducto = ListadoProductos.lstProductos.get(position);

        holder.lblNombreBanner.setText(oProducto.getsNombre());
        Glide.with(context).load(oProducto.getiFoto()).into(holder.imgViewBannerAdapter);
    }

    public int getItemCount() {
        return ListadoProductos.lstProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblNombreBanner;
        ImageView imgViewBannerAdapter;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombreBanner = itemView.findViewById(R.id.lblNombreJuego);
            imgViewBannerAdapter = itemView.findViewById(R.id.imgViewBannerAdapter);
        }
    }
}