package com.example.wellplayed.model;

public class Partido_Equipo {

    private Integer iIdPartidoEquipo,iIdEquipo1,iIdEquipo2,iIdJuego;
    private boolean boVictoria;

    public Partido_Equipo() {
    }

    public Partido_Equipo(Integer iIdPartidoEquipo, Integer iIdEquipo1, Integer iIdEquipo2, Integer iIdJuego, boolean boVictoria) {
        this.iIdPartidoEquipo = iIdPartidoEquipo;
        this.iIdEquipo1 = iIdEquipo1;
        this.iIdEquipo2 = iIdEquipo2;
        this.iIdJuego = iIdJuego;
        this.boVictoria = boVictoria;
    }

    public Integer getiIdPartidoEquipo() {
        return iIdPartidoEquipo;
    }

    public void setiIdPartidoEquipo(Integer iIdPartidoEquipo) {
        this.iIdPartidoEquipo = iIdPartidoEquipo;
    }

    public Integer getiIdEquipo1() {
        return iIdEquipo1;
    }

    public void setiIdEquipo1(Integer iIdEquipo1) {
        this.iIdEquipo1 = iIdEquipo1;
    }

    public Integer getiIdEquipo2() {
        return iIdEquipo2;
    }

    public void setiIdEquipo2(Integer iIdEquipo2) {
        this.iIdEquipo2 = iIdEquipo2;
    }

    public Integer getiIdJuego() {
        return iIdJuego;
    }

    public void setiIdJuego(Integer iIdJuego) {
        this.iIdJuego = iIdJuego;
    }

    public boolean isBoVictoria() {
        return boVictoria;
    }

    public void setBoVictoria(boolean boVictoria) {
        this.boVictoria = boVictoria;
    }
}
