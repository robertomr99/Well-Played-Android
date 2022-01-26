package com.example.wellplayed.model;

import java.io.Serializable;

public class Equipo_Usuario implements Serializable {

    private Equipo oEquipo;
    private Usuario oUsuario;
    private Integer iCreador;

    public Equipo_Usuario(Equipo oEquipo, Usuario oUsuario, Integer iCreador) {
        this.oEquipo = oEquipo;
        this.oUsuario = oUsuario;
        this.iCreador = iCreador;
    }

    public Equipo getoEquipo() {
        return oEquipo;
    }

    public void setoEquipo(Equipo oEquipo) {
        this.oEquipo = oEquipo;
    }

    public Usuario getoUsuario() {
        return oUsuario;
    }

    public void setoUsuario(Usuario oUsuario) {
        this.oUsuario = oUsuario;
    }

    public Integer getiCreador() {
        return iCreador;
    }

    public void setiCreador(Integer iCreador) {
        this.iCreador = iCreador;
    }

    @Override
    public String toString() {
        return "Equipo_Usuario{" +
                "oEquipo=" + oEquipo +
                ", oUsuario=" + oUsuario +
                ", iCreador=" + iCreador +
                '}';
    }
}
