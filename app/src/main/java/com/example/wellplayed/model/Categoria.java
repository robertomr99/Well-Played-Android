package com.example.wellplayed.model;

public class Categoria {

    private Integer iIdCategoria;
    private String sNombre;


    public Categoria(Integer iIdCategoria, String sNombre) {
        this.iIdCategoria = iIdCategoria;
        this.sNombre = sNombre;
    }

    public Categoria(Integer iIdCategoria) {
    }

    public Integer getiIdCategoria() {
        return iIdCategoria;
    }

    public void setiIdCategoria(Integer iIdCategoria) {
        this.iIdCategoria = iIdCategoria;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }
}
