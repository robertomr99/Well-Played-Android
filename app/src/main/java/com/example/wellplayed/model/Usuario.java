package com.example.wellplayed.model;
import android.widget.Toast;

import com.example.wellplayed.Registro;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario implements Serializable {

    private Integer iIdUsuario;
    private String sEmail , sUser, sPassword, sFechaNacimiento, sFoto, sCodigo, sBanner;
    private int iPais, iMonedas;
    private Integer iAdmin;

    public Usuario() {
    }

    public Usuario(String User, String sPassword){
        this.sUser = sUser;
        this.sPassword = sPassword;
    }

    public Usuario(Integer iIdUsuario) {
        this.iIdUsuario = iIdUsuario;
    }

    public Usuario(Integer iIdUsuario, String sEmail, String sUser, String sPassword, String sFechaNacimiento, int iPais, int iMonedas, Integer iAdmin, String sFoto,String sCodigo, String sBanner) {
        this.iIdUsuario = iIdUsuario;
        this.sEmail = sEmail;
        this.sUser = sUser;
        this.sPassword = sPassword;
        this.sFechaNacimiento = sFechaNacimiento;
        this.iPais = iPais;
        this.iMonedas = iMonedas;
        this.iAdmin = iAdmin;
        this.sFoto = sFoto;
        this.sCodigo = sCodigo;
        this.sBanner = sBanner;
    }

    public String getsBanner() {
        return sBanner;
    }

    public void setsBanner(String sBanner) {
        this.sBanner = sBanner;
    }

    public String getsCodigo() {
        return sCodigo;
    }

    public void setsCodigo(String sCodigo) {
        this.sCodigo = sCodigo;
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

    public Integer getiAdmin() {
        return iAdmin;
    }

    public void setiAdmin(Integer iAdmin) {
        this.iAdmin = iAdmin;
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
                ", sFoto='" + sFoto + '\'' +
                ", sCodigo='" + sCodigo + '\'' +
                ", sBanner='" + sBanner + '\'' +
                ", iPais=" + iPais +
                ", iMonedas=" + iMonedas +
                ", iAdmin=" + iAdmin +
                '}';
    }
}
