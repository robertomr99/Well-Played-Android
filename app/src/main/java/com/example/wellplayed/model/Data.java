package com.example.wellplayed.model;

public class Data {
   private Integer iContador;

    public Data() {
    }

    public Data(Integer iContador) {
        this.iContador = iContador;
    }

    public Integer getiContador() {
        return iContador;
    }

    public void setiContador(Integer iContador) {
        this.iContador = iContador;
    }

    @Override
    public String toString() {
        return "Data{" +
                "iContador=" + iContador +
                '}';
    }
}
