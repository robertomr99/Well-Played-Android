package com.example.wellplayed.model;

import java.io.Serializable;

public class Partido_Usuario implements Serializable {

    private Integer iIdPartido, iIdJuego;
    private String sNombreJugador1, sNombreJugador2, sFotoJugador1, sFotoJugador2;
    private int iVictoria;

    public Partido_Usuario() {
    }

    public Partido_Usuario(Integer iIdPartido, Integer iIdJuego, String sNombreJugador1, String sNombreJugador2, String sFotoJugador1, String sFotoJugador2, int iVictoria) {
        this.iIdPartido = iIdPartido;
        this.iIdJuego = iIdJuego;
        this.sNombreJugador1 = sNombreJugador1;
        this.sNombreJugador2 = sNombreJugador2;
        this.sFotoJugador1 = sFotoJugador1;
        this.sFotoJugador2 = sFotoJugador2;
        this.iVictoria = iVictoria;
    }

    public Integer getiIdPartido() {
        return iIdPartido;
    }

    public void setiIdPartido(Integer iIdPartido) {
        this.iIdPartido = iIdPartido;
    }

    public Integer getiIdJuego() {
        return iIdJuego;
    }

    public void setiIdJuego(Integer iIdJuego) {
        this.iIdJuego = iIdJuego;
    }

    public String getsNombreJugador1() {
        return sNombreJugador1;
    }

    public void setsNombreJugador1(String sNombreJugador1) {
        this.sNombreJugador1 = sNombreJugador1;
    }

    public String getsNombreJugador2() {
        return sNombreJugador2;
    }

    public void setsNombreJugador2(String sNombreJugador2) {
        this.sNombreJugador2 = sNombreJugador2;
    }

    public String getsFotoJugador1() {
        return sFotoJugador1;
    }

    public void setsFotoJugador1(String sFotoJugador1) {
        this.sFotoJugador1 = sFotoJugador1;
    }

    public String getsFotoJugador2() {
        return sFotoJugador2;
    }

    public void setsFotoJugador2(String sFotoJugador2) {
        this.sFotoJugador2 = sFotoJugador2;
    }

    public int getiVictoria() {
        return iVictoria;
    }

    public void setiVictoria(int iVictoria) {
        this.iVictoria = iVictoria;
    }

    @Override
    public String toString() {
        return "Partido_Usuario{" +
                "iIdPartido=" + iIdPartido +
                ", iIdJuego=" + iIdJuego +
                ", sNombreJugador1='" + sNombreJugador1 + '\'' +
                ", sNombreJugador2='" + sNombreJugador2 + '\'' +
                ", sFotoJugador1='" + sFotoJugador1 + '\'' +
                ", sFotoJugador2='" + sFotoJugador2 + '\'' +
                ", iVictoria=" + iVictoria +
                '}';
    }
}
