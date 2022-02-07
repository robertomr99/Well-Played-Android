package com.example.wellplayed.model;

public class Partido_Equipo {

    private Integer iIdPartidoEquipo,iIdJuego;
    private String sNombreEquipo1, sNombreEquipo2, sFotoEquipo1, sFotoEquipo2;
    private boolean boVictoria;

    public Partido_Equipo() {
    }

    public Partido_Equipo(Integer iIdPartidoEquipo, Integer iIdJuego, String sNombreEquipo1, String sNombreEquipo2, String sFotoEquipo1, String sFotoEquipo2, boolean boVictoria) {
        this.iIdPartidoEquipo = iIdPartidoEquipo;
        this.iIdJuego = iIdJuego;
        this.sNombreEquipo1 = sNombreEquipo1;
        this.sNombreEquipo2 = sNombreEquipo2;
        this.sFotoEquipo1 = sFotoEquipo1;
        this.sFotoEquipo2 = sFotoEquipo2;
        this.boVictoria = boVictoria;
    }

    public Integer getiIdPartidoEquipo() {
        return iIdPartidoEquipo;
    }

    public void setiIdPartidoEquipo(Integer iIdPartidoEquipo) {
        this.iIdPartidoEquipo = iIdPartidoEquipo;
    }

    public Integer getiIdJuego() {
        return iIdJuego;
    }

    public void setiIdJuego(Integer iIdJuego) {
        this.iIdJuego = iIdJuego;
    }

    public String getsNombreEquipo1() {
        return sNombreEquipo1;
    }

    public void setsNombreEquipo1(String sNombreEquipo1) {
        this.sNombreEquipo1 = sNombreEquipo1;
    }

    public String getsNombreEquipo2() {
        return sNombreEquipo2;
    }

    public void setsNombreEquipo2(String sNombreEquipo2) {
        this.sNombreEquipo2 = sNombreEquipo2;
    }

    public String getsFotoEquipo1() {
        return sFotoEquipo1;
    }

    public void setsFotoEquipo1(String sFotoEquipo1) {
        this.sFotoEquipo1 = sFotoEquipo1;
    }

    public String getsFotoEquipo2() {
        return sFotoEquipo2;
    }

    public void setsFotoEquipo2(String sFotoEquipo2) {
        this.sFotoEquipo2 = sFotoEquipo2;
    }

    public boolean isBoVictoria() {
        return boVictoria;
    }

    public void setBoVictoria(boolean boVictoria) {
        this.boVictoria = boVictoria;
    }
}
