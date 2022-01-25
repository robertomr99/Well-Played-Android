package com.example.wellplayed.model;

public class Equipo_Juego {

   private Equipo oEquipo;
   private Juego oJuego;
   private Integer iVictorias, iDerrotas;
   private float fWinRate;

    public Equipo_Juego(Equipo oEquipo, Juego oJuego) {
        this.oEquipo = oEquipo;
        this.oJuego = oJuego;
    }

    public Equipo_Juego(Equipo oEquipo, Juego oJuego, Integer iVictorias, Integer iDerrotas, float fWinRate) {
        this.oEquipo = oEquipo;
        this.oJuego = oJuego;
        this.iVictorias = iVictorias;
        this.iDerrotas = iDerrotas;
        this.fWinRate = fWinRate;
    }

    public Equipo_Juego() {
    }

    public Equipo getoEquipo() {
        return oEquipo;
    }

    public void setoEquipo(Equipo oEquipo) {
        this.oEquipo = oEquipo;
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

    public float getfWinRate() {
        return fWinRate;
    }

    public void setfWinRate(float fWinRate) {
        this.fWinRate = fWinRate;
    }

    @Override
    public String toString() {
        return "Equipo_Juego{" +
                "oEquipo=" + oEquipo +
                ", oJuego=" + oJuego +
                ", iVictorias=" + iVictorias +
                ", iDerrotas=" + iDerrotas +
                ", fWinRate=" + fWinRate +
                '}';
    }
}
