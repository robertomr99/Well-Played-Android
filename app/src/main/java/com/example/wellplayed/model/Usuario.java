package com.example.wellplayed.model;
import android.widget.Toast;

import com.example.wellplayed.Registro;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Serializable {

    private Integer iIdUsuario;
    private String sEmail , sUser, sPassword, sFechaNacimiento, sFoto;
    private int iPais, iMonedas;
    private boolean boAdmin;

    public Usuario() {
    }

    public Usuario(String User, String sPassword){
        this.sUser = sUser;
        this.sPassword = sPassword;
    }

    public Usuario(Integer iIdUsuario) {
        this.iIdUsuario = iIdUsuario;
    }

    public Usuario(Integer iIdUsuario, String sEmail, String sUser, String sPassword, String sFechaNacimiento, int iPais, int iMonedas, boolean boAdmin, String sFoto) {
        this.iIdUsuario = iIdUsuario;
        this.sEmail = sEmail;
        this.sUser = sUser;
        this.sPassword = sPassword;
        this.sFechaNacimiento = sFechaNacimiento;
        this.iPais = iPais;
        this.iMonedas = iMonedas;
        this.boAdmin = boAdmin;
        this.sFoto = sFoto;
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

        // El email a validar

       if(Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
               + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",sEmail)){
           this.sEmail = sEmail;
       }else{

       }

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

    public boolean isBoAdmin() {
        return boAdmin;
    }

    public void setBoAdmin(boolean boAdmin) {
        this.boAdmin = boAdmin;
    }

    public String getsFoto() {
        return sFoto;
    }

    public void setsFoto(String sFoto) {
        this.sFoto = sFoto;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "iIdUsuario=" + iIdUsuario +
                ", sEmail='" + sEmail + '\'' +
                ", sUser='" + sUser + '\'' +
                ", sPassword='" + sPassword + '\'' +
                ", sFechaNacimiento='" + sFechaNacimiento + '\'' +
                ", iPais=" + iPais +
                ", iMonedas=" + iMonedas +
                ", boAdmin=" + boAdmin +
                '}';
    }
}
