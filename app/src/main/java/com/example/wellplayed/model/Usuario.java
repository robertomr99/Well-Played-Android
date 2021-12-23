package com.example.wellplayed.model;

public class Usuario {

    private Integer iIdUsuario;
    private String sEmail , sUser, sPassword, sFechaNacimiento;
    private int iPais, iMonedas, iVictorias, iDerrotas;
    private float fWinRate;
    private boolean boAdmin;

    public Usuario(Integer iIdUsuario, String sEmail, String sUser, String sPassword, String sFechaNacimiento, int iPais, int iMonedas, int iVictorias, int iDerrotas, float fWinRate, boolean boAdmin) {
        this.iIdUsuario = iIdUsuario;
        this.sEmail = sEmail;
        this.sUser = sUser;
        this.sPassword = sPassword;
        this.sFechaNacimiento = sFechaNacimiento;
        this.iPais = iPais;
        this.iMonedas = iMonedas;
        this.iVictorias = iVictorias;
        this.iDerrotas = iDerrotas;
        this.fWinRate = fWinRate;
        this.boAdmin = boAdmin;
    }

    public Usuario(Integer iIdUsuario) {
        this.iIdUsuario = iIdUsuario;
    }

    public Usuario() {
    }

    public Integer getiIdUsuario() {
        return iIdUsuario;
    }

    public void setiIdUsuario(Integer iIdUsuario) {
        this.iIdUsuario = iIdUsuario;
    }

    public String getsEmail() {
        return sEmail;
    }

    public void setsEmail(String sEmail) {
        this.sEmail = sEmail;
    }

    public String getsUser() {
        return sUser;
    }

    public void setsUser(String sUser) {
        this.sUser = sUser;
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword;
    }

    public String getsFechaNacimiento() {
        return sFechaNacimiento;
    }

    public void setsFechaNacimiento(String sFechaNacimiento) {
        this.sFechaNacimiento = sFechaNacimiento;
    }

    public int getiPais() {
        return iPais;
    }

    public void setiPais(int iPais) {
        this.iPais = iPais;
    }

    public int getiMonedas() {
        return iMonedas;
    }

    public void setiMonedas(int iMonedas) {
        this.iMonedas = iMonedas;
    }

    public int getiVictorias() {
        return iVictorias;
    }

    public void setiVictorias(int iVictorias) {
        this.iVictorias = iVictorias;
    }

    public int getiDerrotas() {
        return iDerrotas;
    }

    public void setiDerrotas(int iDerrotas) {
        this.iDerrotas = iDerrotas;
    }

    public float getfWinRate() {
        return fWinRate;
    }

    public void setfWinRate(float fWinRate) {
        this.fWinRate = fWinRate;
    }

    public boolean isBoAdmin() {
        return boAdmin;
    }

    public void setBoAdmin(boolean boAdmin) {
        this.boAdmin = boAdmin;
    }
}
