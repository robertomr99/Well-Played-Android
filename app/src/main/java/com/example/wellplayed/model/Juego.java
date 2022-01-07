package com.example.wellplayed.model;

public class Juego {

    private Integer iIdJuego;
    private String sNombre, sDescripcion, sFoto;



    public Juego() {
    }

    public Juego(Integer iIdJuego) {
        this.iIdJuego = iIdJuego;
    }

    public Juego(String sNombre, String sDescripcion, String sFoto) {
        this.sNombre = sNombre;
        this.sDescripcion = sDescripcion;
        this.sFoto = sFoto;
    }

    public Juego(Integer iIdJuego, String sNombre, String sDescripcion, String sFoto) {
        this.iIdJuego = iIdJuego;
        this.sNombre = sNombre;
        this.sDescripcion = sDescripcion;
        this.sFoto = sFoto;
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

    public String getsFoto() {
        return sFoto;
    }

    public void setsFoto(String sFoto) {
        this.sFoto = sFoto;
    }
}
