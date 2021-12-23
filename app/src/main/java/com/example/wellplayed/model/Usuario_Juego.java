package com.example.wellplayed.model;

public class Usuario_Juego {

    private Usuario oUsuario;
    private Juego oJuego;

    public Usuario_Juego(Usuario oUsuario, Juego oJuego) {
        this.oUsuario = oUsuario;
        this.oJuego = oJuego;
    }

    public Usuario_Juego() {
    }

    public Usuario getoUsuario() {
        return oUsuario;
    }

    public void setoUsuario(Usuario oUsuario) {
        this.oUsuario = oUsuario;
    }

    public Juego getoJuego() {
        return oJuego;
    }

    public void setoJuego(Juego oJuego) {
        this.oJuego = oJuego;
    }

}
