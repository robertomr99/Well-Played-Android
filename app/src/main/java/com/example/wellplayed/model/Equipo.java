package com.example.wellplayed.model;

import java.io.Serializable;

public class Equipo implements Serializable {

    private Integer iIdEquipo, iMiembros;
    private String sNombre, sFoto;


    public Equipo() {
    }

    public Equipo(Integer iIdEquipo) {
        this.iIdEquipo = iIdEquipo;
    }

    public Equipo(Integer iIdEquipo, Integer iMiembros, String sNombre, String sFoto) {
        this.iIdEquipo = iIdEquipo;
        this.iMiembros = iMiembros;
        this.sNombre = sNombre;
        this.sFoto = sFoto;
    }

    public Integer getiIdEquipo() {
        return iIdEquipo;
    }

    public void setiIdEquipo(Integer iIdEquipo) {
        this.iIdEquipo = iIdEquipo;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public String getsFoto() {
        return sFoto;
    }

    public void setsFoto(String sFoto) {
        this.sFoto = sFoto;
    }

    public Integer getiMiembros() {
        return iMiembros;
    }

    public void setiMiembros(Integer iMiembros) {
        this.iMiembros = iMiembros;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "iIdEquipo=" + iIdEquipo +
                ", iMiembros=" + iMiembros +
                ", sNombre='" + sNombre + '\'' +
                ", sFoto='" + sFoto + '\'' +
                '}';
    }
}
