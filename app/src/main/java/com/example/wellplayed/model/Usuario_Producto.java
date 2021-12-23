package com.example.wellplayed.model;

public class Usuario_Producto {

    private Integer iIdUsuarioProducto;
    private Usuario oUsuario;
    private Producto oProducto;

    public Usuario_Producto(Integer iIdUsuarioProducto, Usuario oUsuario, Producto oProducto) {
        this.iIdUsuarioProducto = iIdUsuarioProducto;
        this.oUsuario = oUsuario;
        this.oProducto = oProducto;
    }

    public Usuario_Producto() {
    }

    public Usuario_Producto(Integer iIdUsuarioProducto) {
        this.iIdUsuarioProducto = iIdUsuarioProducto;
    }

    public Integer getiIdUsuarioProducto() {
        return iIdUsuarioProducto;
    }

    public void setiIdUsuarioProducto(Integer iIdUsuarioProducto) {
        this.iIdUsuarioProducto = iIdUsuarioProducto;
    }

    public Usuario getoUsuario() {
        return oUsuario;
    }

    public void setoUsuario(Usuario oUsuario) {
        this.oUsuario = oUsuario;
    }

    public Producto getoProducto() {
        return oProducto;
    }

    public void setoProducto(Producto oProducto) {
        this.oProducto = oProducto;
    }
}
