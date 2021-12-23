package com.example.wellplayed.model;

public class Equipo_Usuario {

   private Equipo oEquipo;
   private Usuario oUsuario;
   private boolean boCreador;

    public Equipo_Usuario(Equipo oEquipo, Usuario oUsuario, boolean boCreador) {
        this.oEquipo = oEquipo;
        this.oUsuario = oUsuario;
        this.boCreador = boCreador;
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

    public boolean isBoCreador() {
        return boCreador;
    }

    public void setBoCreador(boolean boCreador) {
        this.boCreador = boCreador;
    }
}
