package com.example.wellplayed.model;

public class Producto {

    private Integer iIdProducto;
    private String sNombre, sFoto;
    private int iPrecio;
    private Categoria oCategoria;

    public Producto(Integer iIdProducto, String sNombre, int iPrecio, String sFoto, Categoria oCategoria) {
        this.iIdProducto = iIdProducto;
        this.sNombre = sNombre;
        this.iPrecio = iPrecio;
        this.sFoto = sFoto;
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

    public void setiIdProducto(Integer iIdProducto) { this.iIdProducto = iIdProducto;
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

    public String getsFoto() {
        return sFoto;
    }

    public void setsFoto(String sFoto) {
        this.sFoto = sFoto;
    }

    public Categoria getoCategoria() {
        return oCategoria;
    }

    public void setoCategoria(Categoria oCategoria) {
        this.oCategoria = oCategoria;
    }
}
