package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo;
import com.example.wellplayed.model.Equipo_Usuario;
import com.example.wellplayed.model.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UnirseEquipoAdapter extends RecyclerView.Adapter<UnirseEquipoAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
    UnirseEquipoAdapterInterface ueInterface;
    private View.OnClickListener listener;


    public interface UnirseEquipoAdapterInterface {
        void addEquipoUsuario(Equipo oEquipo);
    }

    public UnirseEquipoAdapter(Context context, UnirseEquipoAdapterInterface ueInterface) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.ueInterface = ueInterface;
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

    public UnirseEquipoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_unirse_equipo_adapter, parent, false);
        view.setOnClickListener(this);
        return new UnirseEquipoAdapter.ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull UnirseEquipoAdapter.ViewHolder holder, int position) {
        Equipo oEquipo = ListadoEquipos.lstEquipos.get(position); // Instanciamos el objeto de la lista con la posicion

        Glide.with(context).load(oEquipo.getsFoto()).circleCrop().into(holder.imageViewEquipo);
        String sNombre = oEquipo.getsNombre();
        String sMiembros = String.valueOf(oEquipo.getiMiembros());

        holder.lblNombre.setText(sNombre);

        holder.imgBtnUnirseEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.clickanimation));
                ueInterface.addEquipoUsuario(oEquipo);
            }
        }
        );

        holder.lblMiembros.setText(sMiembros);
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));

    }


    public int getItemCount() {
        return ListadoEquipos.lstEquipos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblNombre;
        TextView lblMiembros;
        ImageView imageViewEquipo;
        ImageButton imgBtnUnirseEquipo;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblMiembros = itemView.findViewById(R.id.lblUMiembros);
            lblNombre = itemView.findViewById(R.id.lblUNombreEquipo);
            imageViewEquipo = itemView.findViewById(R.id.imgViewUEquipo);
            imgBtnUnirseEquipo = itemView.findViewById(R.id.imgBtnUnirseEquipo);
            cv = itemView.findViewById(R.id.cardViewUEquipos);
        }

    }

}