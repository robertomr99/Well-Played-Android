package com.example.wellplayed.model;

public class Producto {

    private Integer iIdProducto;
    private String sNombre;
    private int iPrecio, iFoto;
    private Categoria oCategoria;

    public Producto(Integer iIdProducto, String sNombre, int iPrecio, int iFoto, Categoria oCategoria) {
        this.iIdProducto = iIdProducto;
        this.sNombre = sNombre;
        this.iPrecio = iPrecio;
        this.iFoto = iFoto;
        this.oCategoria = oCategoria;
    }

    public Producto(Integer iIdProducto) {
        this.iIdProducto = iIdProducto;
    }

    public Producto() {
    }

    public Integer getiIdProducto() {
        return iIdProducto;
    }

    public void setiIdProducto(Integer iIdProducto) {
        this.iIdProducto = iIdProducto;
    }

    public String getsNombre() {
        return sNombre;
    }

    public void setsNombre(String sNombre) {
        this.sNombre = sNombre;
    }

    public int getiPrecio() {
        return iPrecio;
    }

    public void setiPrecio(int iPrecio) {
        this.iPrecio = iPrecio;
    }

    public int getiFoto() {
        return iFoto;
    }

    public void setiFoto(int iFoto) {
        this.iFoto = iFoto;
    }

    public Categoria getoCategoria() {
        return oCategoria;
    }

    public void setoCategoria(Categoria oCategoria) {
        this.oCategoria = oCategoria;
    }
}
