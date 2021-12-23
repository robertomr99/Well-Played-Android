package com.example.wellplayed.model;

public class Juego {

    private Integer iIdJuego;
    private String sNombre, sDescripcion;
    private int iFoto;

    public Juego(Integer iIdJuego, String sNombre, String sDescripcion, int iFoto) {
        this.iIdJuego = iIdJuego;
        this.sNombre = sNombre;
        this.sDescripcion = sDescripcion;
        this.iFoto = iFoto;
    }

    public Juego(Integer iIdJuego) {
        this.iIdJuego = iIdJuego;
    }

    public Juego() {
    }

    public Integer getiIdJuego() {
        return iIdJuego;
    }

    public void setiIdJuego(Integer iIdJuego) {
        this.iIdJuego = iIdJuego;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getsDescripcion() {
        return sDescripcion;
    }

    public void setsDescripcion(String sDescripcion) {
        this.sDescripcion = sDescripcion;
    }

    public int getiFoto() {
        return iFoto;
    }

    public void setiFoto(int iFoto) {
        this.iFoto = iFoto;
    }
}
