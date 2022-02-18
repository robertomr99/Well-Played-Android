package com.example.wellplayed.model;

public class Soporte {
    private Integer iIdTicket,iIdUsuario,iStatus;
    private String sMensaje,sFoto,sAsunto;

    public Soporte() {
    }

    public Soporte(Integer iIdTicket, Integer iIdUsuario, Integer iStatus, String sMensaje, String sFoto, String sAsunto) {
        this.iIdTicket = iIdTicket;
        this.sFoto = sFoto;
        this.iIdUsuario = iIdUsuario;
        this.iStatus = iStatus;
        this.sMensaje = sMensaje;
        this.sAsunto = sAsunto;
    }

    public String getsAsunto() {
        return sAsunto;
    }

    public void setsAsunto(String sAsunto) {
        this.sAsunto = sAsunto;
    }

    public String getsFoto() {
        return sFoto;
    }

    public void setsFoto(String sFoto) {
        this.sFoto = sFoto;
    }

    public Integer getiIdTicket() {
        return iIdTicket;
    }

    public void setiIdTicket(Integer iIdTicket) {
        this.iIdTicket = iIdTicket;
    }

    public Integer getiIdUsuario() {
        return iIdUsuario;
    }

    public void setiIdUsuario(Integer iIdUsuario) {
        this.iIdUsuario = iIdUsuario;
    }

    public Integer getiStatus() {
        return iStatus;
    }

    public void setiStatus(Integer iStatus) {
        this.iStatus = iStatus;
    }

    public String getsMensaje() {
        return sMensaje;
    }

    public void setsMensaje(String sMensaje) {
        this.sMensaje = sMensaje;
    }

    @Override
    public String toString() {
        return "Soporte{" +
                "iIdTicket=" + iIdTicket +
                ", iIdUsuario=" + iIdUsuario +
                ", iStatus=" + iStatus +
                ", sMensaje='" + sMensaje + '\'' +
                ", sFoto='" + sFoto + '\'' +
                ", sAsunto='" + sAsunto + '\'' +
                '}';
    }
}
