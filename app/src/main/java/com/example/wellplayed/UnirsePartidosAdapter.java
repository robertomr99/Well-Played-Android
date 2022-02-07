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

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Partido_Equipo;
import com.example.wellplayed.model.Partido_Usuario;


public class UnirsePartidosAdapter extends RecyclerView.Adapter<UnirsePartidosAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
    UnirsePartidosAdapterInterface upInterface;
    private View.OnClickListener listener;
    int iTipoGlobal;


    public interface UnirsePartidosAdapterInterface {
        void prueba();
    }

    public UnirsePartidosAdapter(Context context, UnirsePartidosAdapterInterface upInterface, int iTipo) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.upInterface = upInterface;
        this.iTipoGlobal = iTipo;
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

    public UnirsePartidosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_unirse_partido_adapter, parent, false);
        view.setOnClickListener(this);
        return new UnirsePartidosAdapter.ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull UnirsePartidosAdapter.ViewHolder holder, int position) {

        if(iTipoGlobal == 1){

            // EQUIPO

            Partido_Equipo oPartidoEquipo = ListadoPartidosEquipo.lstPartidoEquipo.get(position); // Instanciamos el objeto de la lista con

            holder.lblNombreC1.setText(oPartidoEquipo.getsNombreEquipo1());
            holder.lblNombreC2.setText(oPartidoEquipo.getsNombreEquipo2());
            Glide.with(context).load(oPartidoEquipo.getsFotoEquipo1()).error(R.drawable.profile).into(holder.imageViewC1);
            Glide.with(context).load(oPartidoEquipo.getsFotoEquipo2()).error(R.drawable.profile).into(holder.imageViewC2);

            if(oPartidoEquipo.getiIdJuego() == 1){
                Glide.with(context).load(R.drawable.cvbannerlol).into(holder.imageViewFondo);
            }else if(oPartidoEquipo.getiIdJuego() == 2){
                Glide.with(context).load(R.drawable.cvbannevalorant).into(holder.imageViewFondo);
            }else if(oPartidoEquipo.getiIdJuego() == 3){
                Glide.with(context).load(R.drawable.cvbannercsgo).into(holder.imageViewFondo);
            }else{
                Glide.with(context).load(R.drawable.cvbannefifa).into(holder.imageViewFondo);
            }

        }else{

            // USUARIO

            Partido_Usuario oPartidoUsuario = ListadoPartidosUsuario.lstPartidoUsuario.get(position);

            holder.lblNombreC1.setText(oPartidoUsuario.getsNombreJugador1());
            holder.lblNombreC2.setText(oPartidoUsuario.getsNombreJugador2());
            Glide.with(context).load(oPartidoUsuario.getsFotoJugador1()).error(R.drawable.profile).into(holder.imageViewC1);
            Glide.with(context).load(oPartidoUsuario.getsFotoJugador2()).error(R.drawable.profile).into(holder.imageViewC2);

            if(oPartidoUsuario.getiIdJuego() == 1){
                Glide.with(context).load(R.drawable.cvbannerlol).into(holder.imageViewFondo);
            }else if(oPartidoUsuario.getiIdJuego() == 2){
                Glide.with(context).load(R.drawable.cvbannevalorant).into(holder.imageViewFondo);
            }else if(oPartidoUsuario.getiIdJuego() == 3){
                Glide.with(context).load(R.drawable.cvbannercsgo).into(holder.imageViewFondo);
            }else{
                Glide.with(context).load(R.drawable.cvbannefifa).into(holder.imageViewFondo);
            }

        }



        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));

    }


    public int getItemCount() {
        int iSize;
        if(iTipoGlobal == 1){
            iSize = ListadoPartidosEquipo.lstPartidoEquipo.size();
        }else{
            iSize = ListadoPartidosUsuario.lstPartidoUsuario.size();
        }
        return iSize;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblNombreC1,lblNombreC2;
        ImageView imageViewC1,imageViewC2, imageViewFondo ;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblNombreC1 = itemView.findViewById(R.id.lblUNombreC1);
            lblNombreC2 = itemView.findViewById(R.id.lblUNombreC2);
            imageViewC1 = itemView.findViewById(R.id.imgViewUC1);
            imageViewC2 = itemView.findViewById(R.id.imgViewUC2);
            imageViewFondo = itemView.findViewById(R.id.imgViewFondo);
            cv = itemView.findViewById(R.id.cardViewUPartidos);
        }

    }

}