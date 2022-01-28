package com.example.wellplayed.model;

public class Peticiones {

    private Integer iIdPeticion, iEquipo, iIdUsuario;

    public Peticiones() {
    }

    public Peticiones(Integer iIdPeticion) {
        this.iIdPeticion = iIdPeticion;
    }

    public Peticiones(Integer iIdPeticion, Integer iEquipo, Integer iIdUsuario) {
        this.iIdPeticion = iIdPeticion;
        this.iEquipo = iEquipo;
        this.iIdUsuario = iIdUsuario;
    }


    public Integer getiIdPeticion() {
        return iIdPeticion;
    }

    public void setiIdPeticion(Integer iIdPeticion) {
        this.iIdPeticion = iIdPeticion;
    }

    public Integer getiEquipo() {
        return iEquipo;
    }

    public void setiEquipo(Integer iEquipo) {
        this.iEquipo = iEquipo;
    }

    public Integer getiIdUsuario() {
        return iIdUsuario;
    }

    public void setiIdUsuario(Integer iIdUsuario) {
        this.iIdUsuario = iIdUsuario;
    }

    @Override
    public String toString() {
        return "Peticiones{" +
                "iIdPeticion=" + iIdPeticion +
                ", iEquipo=" + iEquipo +
                ", iIdUsuario=" + iIdUsuario +
                '}';
    }
}
