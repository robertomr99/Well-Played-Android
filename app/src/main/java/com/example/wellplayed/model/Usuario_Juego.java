package com.example.wellplayed.model;

import java.io.Serializable;

public class Usuario_Juego implements Serializable {

    private Usuario oUsuario;
    private Juego oJuego;
    private Integer iVictorias, iDerrotas;
    private Float fWinRate;

    public Usuario_Juego(Usuario oUsuario, Juego oJuego, Integer iVictorias, Integer iDerrotas, Integer iWinRate ) {
        this.oUsuario = oUsuario;
        this.oJuego = oJuego;
        this.iVictorias = iVictorias;
        this.iDerrotas = iDerrotas;
        this.fWinRate = fWinRate;
    }

    public Usuario_Juego(Integer iVictorias, Integer iDerrotas, Integer iWinRate ) {
        this.iVictorias = iVictorias;
        this.iDerrotas = iDerrotas;
        this.fWinRate = fWinRate;
    }


    public Usuario_Juego() {
    }

    public Usuario getoUsuario() {
        return oUsuario;
    }

    public void setoUsuario(Usuario oUsuario) {
        this.oUsuario = oUsuario;
    }

    public Juego getoJuego() {
        return oJuego;
    }

    public void setoJuego(Juego oJuego) {
        this.oJuego = oJuego;
    }

    public Integer getiVictorias() {
        return iVictorias;
    }

    public void setiVictorias(Integer iVictorias) {
        this.iVictorias = iVictorias;
    }

    public Integer getiDerrotas() {
        return iDerrotas;
    }

    public void setiDerrotas(Integer iDerrotas) {
        this.iDerrotas = iDerrotas;
    }

    public Float getfWinRate() {
        return fWinRate;
    }

    public void setfWinRate(Float fWinRate) {
        this.fWinRate = fWinRate;
    }

    @Override
    public String toString() {
        return "Usuario_Juego{" +
                "oUsuario=" + oUsuario +
                ", oJuego=" + oJuego +
                ", iVictorias=" + iVictorias +
                ", iDerrotas=" + iDerrotas +
                ", fWinRate=" + fWinRate +
                '}';
    }
}
