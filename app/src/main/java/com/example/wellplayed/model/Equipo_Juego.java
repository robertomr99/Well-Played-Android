package com.example.wellplayed.model;

public class Equipo_Juego {

   private Equipo oEquipo;
   private Juego oJuego;

    public Equipo_Juego(Equipo oEquipo, Juego oJuego) {
        this.oEquipo = oEquipo;
        this.oJuego = oJuego;
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
}
