package com.example.wellplayed.model;

public class Partido_Usuario {

    private Integer iIdPartidoUsuario;
    private Usuario_Juego oJugador1, oJugador2;
    private int iVictoria;

    public Partido_Usuario(Integer iIdPartidoUsuario, Usuario_Juego oJugador1, Usuario_Juego oJugador2, int iVictoria) {
        this.iIdPartidoUsuario = iIdPartidoUsuario;
        this.oJugador1 = oJugador1;
        this.oJugador2 = oJugador2;
        this.iVictoria = iVictoria;
    }

    public Integer getiIdPartidoUsuario() {
        return iIdPartidoUsuario;
    }

    public void setiIdPartidoUsuario(Integer iIdPartidoUsuario) {
        this.iIdPartidoUsuario = iIdPartidoUsuario;
    }

    public Usuario_Juego getJugador1() {
        return oJugador1;
    }

    public void setJugador1(Usuario_Juego jugador1) {
        oJugador1 = oJugador1;
    }

    public Usuario_Juego getJugador2() {
        return oJugador2;
    }

    public void setJugador2(Usuario_Juego jugador2) {
        oJugador2 = oJugador2;
    }

    public int getiVictoria() {
        return iVictoria;
    }

    public void setiVictoria(int iVictoria) {
        this.iVictoria = iVictoria;
    }
}
