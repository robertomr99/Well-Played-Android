package com.example.wellplayed.model;

public class Partido_Equipo {

    private Integer iIdPartidoEquipo;
    private Equipo_Juego oEquipo1, oEquipo2;
   private boolean boVictoria;

    public Partido_Equipo(Integer iIdPartidoEquipo, Equipo_Juego oEquipo1, Equipo_Juego oEquipo2, boolean boVictoria) {
        this.iIdPartidoEquipo = iIdPartidoEquipo;
        this.oEquipo1 = oEquipo1;
        this.oEquipo2 = oEquipo2;
        this.boVictoria = boVictoria;
    }

    public Integer getiIdPartidoEquipo() {
        return iIdPartidoEquipo;
    }

    public void setiIdPartidoEquipo(Integer iIdPartidoEquipo) {
        this.iIdPartidoEquipo = iIdPartidoEquipo;
    }

    public Equipo_Juego getoEquipo1() {
        return oEquipo1;
    }

    public void setoEquipo1(Equipo_Juego oEquipo1) {
        this.oEquipo1 = oEquipo1;
    }

    public Equipo_Juego getoEquipo2() {
        return oEquipo2;
    }

    public void setoEquipo2(Equipo_Juego oEquipo2) {
        this.oEquipo2 = oEquipo2;
    }

    public boolean isBoVictoria() {
        return boVictoria;
    }

    public void setBoVictoria(boolean boVictoria) {
        this.boVictoria = boVictoria;
    }
}
