package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Producto;

public class ProductosAdapter extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> implements View.OnClickListener {

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
        holder.lblNombreProducto.setText(oProducto.getsNombre());
        holder.lblPrecioProducto.setText(String.valueOf(oProducto.getiPrecio()));

    if(tiendaFragment.spinnerCategoriaProducto.getSelectedItemPosition()==0) {
        Glide.with(context).load(oProducto.getsFoto()).override(350).circleCrop().into(holder.imgViewProductoAdapter);
    }else{
        Glide.with(context).load(oProducto.getsFoto()).into(holder.imgViewProductoAdapter);
    }
    }

    public int getItemCount() {
        return ListadoProductos.lstProductos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView lblNombreProducto;
        TextView lblPrecioProducto;
        ImageView imgViewProductoAdapter;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombreProducto = itemView.findViewById(R.id.lblNombreProducto);
            imgViewProductoAdapter = itemView.findViewById(R.id.imgViewProductoAdapter);
            lblPrecioProducto = itemView.findViewById(R.id.lblPrecioProducto);
        }
    }
}