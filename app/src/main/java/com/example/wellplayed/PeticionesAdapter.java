package com.example.wellplayed;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Peticiones;
import com.example.wellplayed.model.Usuario;

import de.hdodenhof.circleimageview.CircleImageView;

public class PeticionesAdapter extends RecyclerView.Adapter<PeticionesAdapter.ViewHolder> implements View.OnClickListener {

    PeticionesInterface petInterface;
    LayoutInflater inflater;
    Context context;
    private View.OnClickListener listener;

    public interface PeticionesInterface {
        void insertPeticion(Usuario oUsuario);
        void borrarPeticion(Usuario oUsuario);
    }

    public PeticionesAdapter(Context context, PeticionesInterface petInterface) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.petInterface = petInterface;
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
    public PeticionesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.activity_peticiones_equipo_adapter, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PeticionesAdapter.ViewHolder holder, int position) {

        Usuario oUsuario = ListadoPeticionesUsuarios.lstPeticionesUsuarios.get(position); // Instanciamos el objeto de la lista con la posicion

        Log.d("UsuarioBasura", oUsuario.toString());

        Glide.with(context).load(oUsuario.getsFoto()).circleCrop().into(holder.imgViewUsuario);

        holder.lblUsuario.setText(oUsuario.getsUser());

        holder.imgBtnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.clickanimation));
                petInterface.insertPeticion(oUsuario);
            }
        });

        holder.imgBtnRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.clickanimation));
                petInterface.borrarPeticion(oUsuario);
            }
        });

        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
    }


    public int getItemCount() {
        return ListadoPeticionesUsuarios.lstPeticionesUsuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblUsuario;
        ImageView imgViewUsuario;
        ImageButton imgBtnAceptar, imgBtnRechazar;
        CardView cv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblUsuario = itemView.findViewById(R.id.lblNombreUsuario);
            imgViewUsuario = itemView.findViewById(R.id.imgViewPeticiones);
            imgBtnAceptar = itemView.findViewById(R.id.imgBtnAceptar);
            imgBtnRechazar = itemView.findViewById(R.id.imgBtnRechazar);
            cv = itemView.findViewById(R.id.cardViewPeticiones);
        }
    }
}