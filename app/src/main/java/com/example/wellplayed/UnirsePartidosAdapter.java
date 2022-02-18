package com.example.wellplayed;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wellplayed.model.Equipo_Usuario;
import com.example.wellplayed.model.Partido_Equipo;
import com.example.wellplayed.model.Partido_Usuario;


public class UnirsePartidosAdapter extends RecyclerView.Adapter<UnirsePartidosAdapter.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    Context context;
    UnirsePartidosAdapterInterface upInterface;
    private View.OnClickListener listener;
    int iTipoGlobal;
    String sVentanaGlobal;


    public interface UnirsePartidosAdapterInterface {
        void unirseAlPartidoEquipo(Partido_Equipo oPartidoEquipo, Context context);

        void unirseAlPartidoUsuario(Partido_Usuario oPartidoUsuario, Context context);
    }


    public UnirsePartidosAdapter(Context context, UnirsePartidosAdapterInterface upInterface, int iTipo, String sVentana) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.upInterface = upInterface;
        this.iTipoGlobal = iTipo;
        this.sVentanaGlobal = sVentana;
    }

    public UnirsePartidosAdapter(Context context, int iTipo, String sVentana) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.iTipoGlobal = iTipo;
        this.sVentanaGlobal = sVentana;
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

        if (sVentanaGlobal.equals("MisPartidos")) {

            if (iTipoGlobal == 1) {
                // EQUIPO
                Partido_Equipo oPartidoEquipo = ListadoPartidosEquipo.lstPartidoEquipo.get(position); // Instanciamos el objeto de la lista con

                if (oPartidoEquipo.getsNombreEquipo1() == null) {
                    holder.lblNombreC1.setText("");
                } else {
                    holder.lblNombreC1.setText(oPartidoEquipo.getsNombreEquipo1().toLowerCase());
                }

                if (holder.lblNombreC1.getText() != null && !holder.lblNombreC1.getText().equals("")) {
                    holder.lblNombreC1.setBackgroundResource(R.drawable.borderecyclerpartidos);
                } else if (holder.lblNombreC1.getText().equals("")) {
                    holder.lblNombreC1.setVisibility(View.GONE);
                }


                if (oPartidoEquipo.getsNombreEquipo2() == null) {
                    holder.lblNombreC2.setText("");
                } else {
                    holder.lblNombreC2.setText(oPartidoEquipo.getsNombreEquipo2().toLowerCase());
                }

                if (holder.lblNombreC2.getText() != null && !holder.lblNombreC2.getText().equals("")) {
                    holder.lblNombreC2.setBackgroundResource(R.drawable.borderecyclerpartidos);
                } else if (holder.lblNombreC2.getText().equals("")) {
                    holder.lblNombreC2.setVisibility(View.GONE);
                }

                Glide.with(context).load(oPartidoEquipo.getsFotoEquipo1()).into(holder.imageViewC1);
                Glide.with(context).load(oPartidoEquipo.getsFotoEquipo2()).into(holder.imageViewC2);


                if (oPartidoEquipo.getiIdJuego() == 1) {
                    Glide.with(context).load(R.drawable.cvbannerlol).into(holder.imageViewFondo);
                } else if (oPartidoEquipo.getiIdJuego() == 2) {
                    Glide.with(context).load(R.drawable.cvbannevalorant).into(holder.imageViewFondo);
                } else if (oPartidoEquipo.getiIdJuego() == 3) {
                    Glide.with(context).load(R.drawable.cvbannercsgo).into(holder.imageViewFondo);
                } else {
                    Glide.with(context).load(R.drawable.cvbannefifa).into(holder.imageViewFondo);
                }

            } else {
                // USUARIO
                Partido_Usuario oPartidoUsuario = ListadoPartidosUsuario.lstPartidoUsuario.get(position);

                if (oPartidoUsuario.getsNombreJugador1() == null) {
                    holder.lblNombreC1.setText("");
                } else {
                    holder.lblNombreC1.setText(oPartidoUsuario.getsNombreJugador1().toLowerCase());
                }


                if (holder.lblNombreC1.getText() != null && !holder.lblNombreC1.getText().equals("")) {
                    holder.lblNombreC1.setBackgroundResource(R.drawable.borderecyclerpartidos);
                } else if (holder.lblNombreC1.getText().equals("")) {
                    holder.lblNombreC1.setVisibility(View.GONE);
                }


                if (oPartidoUsuario.getsNombreJugador2() == null) {
                    holder.lblNombreC2.setText("");
                } else {
                    holder.lblNombreC2.setText(oPartidoUsuario.getsNombreJugador2().toLowerCase());
                }

                if (holder.lblNombreC2.getText() != null && !holder.lblNombreC2.getText().equals("")) {
                    holder.lblNombreC2.setBackgroundResource(R.drawable.borderecyclerpartidos);
                } else if (holder.lblNombreC2.getText().equals("")) {
                    holder.lblNombreC2.setVisibility(View.GONE);
                }


                Glide.with(context).load(oPartidoUsuario.getsFotoJugador1()).into(holder.imageViewC1);
                Glide.with(context).load(oPartidoUsuario.getsFotoJugador2()).into(holder.imageViewC2);


                if (oPartidoUsuario.getiIdJuego() == 1) {
                    Glide.with(context).load(R.drawable.cvbannerlol).into(holder.imageViewFondo);
                } else if (oPartidoUsuario.getiIdJuego() == 2) {
                    Glide.with(context).load(R.drawable.cvbannevalorant).into(holder.imageViewFondo);
                } else if (oPartidoUsuario.getiIdJuego() == 3) {
                    Glide.with(context).load(R.drawable.cvbannercsgo).into(holder.imageViewFondo);
                } else {
                    Glide.with(context).load(R.drawable.cvbannefifa).into(holder.imageViewFondo);
                }
            }

        } else {

            if (iTipoGlobal == 1) {
                // EQUIPO
                Partido_Equipo oPartidoEquipo = ListadoPartidosEquipo.lstPartidoEquipo.get(position); // Instanciamos el objeto de la lista con


                if (oPartidoEquipo.getsNombreEquipo1() == null) {
                    holder.lblNombreC1.setText("");
                } else {
                    holder.lblNombreC1.setText(oPartidoEquipo.getsNombreEquipo1().toLowerCase());
                }

                if (holder.lblNombreC1.getText() != null && !holder.lblNombreC1.getText().equals("")) {
                    holder.lblNombreC1.setBackgroundResource(R.drawable.borderecyclerpartidos);
                } else if (holder.lblNombreC1.getText().equals("")) {
                    holder.lblNombreC1.setVisibility(View.GONE);
                }


                if (oPartidoEquipo.getsNombreEquipo2() == null) {
                    holder.lblNombreC2.setText("");
                } else {
                    holder.lblNombreC2.setText(oPartidoEquipo.getsNombreEquipo2().toLowerCase());
                }

                if (holder.lblNombreC2.getText() != null && !holder.lblNombreC2.getText().equals("")) {
                    holder.lblNombreC2.setBackgroundResource(R.drawable.borderecyclerpartidos);
                } else if (holder.lblNombreC2.getText().equals("")) {
                    holder.lblNombreC2.setVisibility(View.GONE);
                }

                Glide.with(context).load(oPartidoEquipo.getsFotoEquipo1()).into(holder.imageViewC1);
                Glide.with(context).load(oPartidoEquipo.getsFotoEquipo2()).into(holder.imageViewC2);

                holder.imageViewC1.setOnClickListener(view -> {
                    if (oPartidoEquipo.getsFotoEquipo1().isEmpty()) {
                        upInterface.unirseAlPartidoEquipo(oPartidoEquipo, context);
                    } else {
                        Toast.makeText(context, "Ya hay un equipo apuntado a este partido", Toast.LENGTH_SHORT).show();
                    }
                });

                holder.imageViewC2.setOnClickListener(view -> {
                    if (oPartidoEquipo.getsFotoEquipo1().isEmpty()) {
                        Toast.makeText(context, "Únete como primer equipo", Toast.LENGTH_SHORT).show();
                    } else {
                        upInterface.unirseAlPartidoEquipo(oPartidoEquipo, context);
                    }
                });

                if (oPartidoEquipo.getiIdJuego() == 1) {
                    Glide.with(context).load(R.drawable.cvbannerlol).into(holder.imageViewFondo);
                } else if (oPartidoEquipo.getiIdJuego() == 2) {
                    Glide.with(context).load(R.drawable.cvbannevalorant).into(holder.imageViewFondo);
                } else if (oPartidoEquipo.getiIdJuego() == 3) {
                    Glide.with(context).load(R.drawable.cvbannercsgo).into(holder.imageViewFondo);
                } else {
                    Glide.with(context).load(R.drawable.cvbannefifa).into(holder.imageViewFondo);
                }

            } else {
                // USUARIO
                Partido_Usuario oPartidoUsuario = ListadoPartidosUsuario.lstPartidoUsuario.get(position);

                if (oPartidoUsuario.getsNombreJugador1() == null) {
                    holder.lblNombreC1.setText("");
                } else {
                    holder.lblNombreC1.setText(oPartidoUsuario.getsNombreJugador1().toLowerCase());
                }


                if (holder.lblNombreC1.getText() != null && !holder.lblNombreC1.getText().equals("")) {
                    holder.lblNombreC1.setBackgroundResource(R.drawable.borderecyclerpartidos);
                } else if (holder.lblNombreC1.getText().equals("")) {
                    holder.lblNombreC1.setVisibility(View.GONE);
                }


                if (oPartidoUsuario.getsNombreJugador2() == null) {
                    holder.lblNombreC2.setText("");
                } else {
                    holder.lblNombreC2.setText(oPartidoUsuario.getsNombreJugador2().toLowerCase());
                }

                if (holder.lblNombreC2.getText() != null && !holder.lblNombreC2.getText().equals("")) {
                    holder.lblNombreC2.setBackgroundResource(R.drawable.borderecyclerpartidos);
                } else if (holder.lblNombreC2.getText().equals("")) {
                    holder.lblNombreC2.setVisibility(View.GONE);
                }

                Glide.with(context).load(oPartidoUsuario.getsFotoJugador1()).into(holder.imageViewC1);
                Glide.with(context).load(oPartidoUsuario.getsFotoJugador2()).into(holder.imageViewC2);

                holder.imageViewC1.setOnClickListener(view -> {
                    if (oPartidoUsuario.getsFotoJugador1().isEmpty()) {
                        upInterface.unirseAlPartidoUsuario(oPartidoUsuario, context);
                    } else {
                        Toast.makeText(context, "Ya hay un usuario apuntado como primer jugador", Toast.LENGTH_SHORT).show();
                    }
                });

                holder.imageViewC2.setOnClickListener(view -> {
                    if (oPartidoUsuario.getsFotoJugador1().isEmpty()) {
                        Toast.makeText(context, "Únete como primer jugador", Toast.LENGTH_SHORT).show();
                    } else {
                        upInterface.unirseAlPartidoUsuario(oPartidoUsuario, context);
                    }
                });

                if (oPartidoUsuario.getiIdJuego() == 1) {
                    Glide.with(context).load(R.drawable.cvbannerlol).into(holder.imageViewFondo);
                } else if (oPartidoUsuario.getiIdJuego() == 2) {
                    Glide.with(context).load(R.drawable.cvbannevalorant).into(holder.imageViewFondo);
                } else if (oPartidoUsuario.getiIdJuego() == 3) {
                    Glide.with(context).load(R.drawable.cvbannercsgo).into(holder.imageViewFondo);
                } else {
                    Glide.with(context).load(R.drawable.cvbannefifa).into(holder.imageViewFondo);
                }
            }

        }

        holder.cv.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition));
    }


    public int getItemCount() {
        int iSize;
        if (iTipoGlobal == 1) {
            iSize = ListadoPartidosEquipo.lstPartidoEquipo.size();
        } else {
            iSize = ListadoPartidosUsuario.lstPartidoUsuario.size();
        }
        return iSize;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView lblNombreC1, lblNombreC2;
        ImageView imageViewC1, imageViewC2, imageViewFondo;
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