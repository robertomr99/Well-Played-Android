package com.example.wellplayed.model;

public class Equipo {

    private Integer iIdEquipo, iVictorias, iDerrotas, iFoto;
    private String sNombre;
    private Float fWinRate;

    public Equipo(Integer iIdEquipo, String sNombre, Integer iVictorias, Integer iDerrotas,  Float fWinRate, Integer iFoto) {
        this.iIdEquipo = iIdEquipo;
        this.sNombre = sNombre;
        this.iVictorias = iVictorias;
        this.iDerrotas = iDerrotas;
        this.fWinRate = fWinRate;
        this.iFoto = iFoto;
    }

    public Equipo() {

    }

    public Integer getiIdEquipo() {
        return iIdEquipo;
    }

    public void setiIdEquipo(Integer iIdEquipo) {
        this.iIdEquipo = iIdEquipo;
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

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public Float getfWinRate() {
        return fWinRate;
    }

    public void setfWinRate(Float fWinRate) {
        this.fWinRate = fWinRate;
    }

    public Integer getiFoto() {
        return iFoto;
    }

    public void setiFoto(Integer iFoto) {
        this.iFoto = iFoto;
    }
}
