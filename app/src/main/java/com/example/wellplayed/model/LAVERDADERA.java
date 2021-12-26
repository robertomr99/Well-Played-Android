package com.example.wellplayed.model;

public class LAVERDADERA {

    Integer IDVERDAD;
    String PORFAVOR;

    public LAVERDADERA() {
    }

    public LAVERDADERA(Integer IDVERDAD) {
        this.IDVERDAD = IDVERDAD;
    }

    public LAVERDADERA(Integer IDVERDAD, String PORFAVOR) {
        this.IDVERDAD = IDVERDAD;
        this.PORFAVOR = PORFAVOR;
    }

    public Integer getIDVERDAD() {
        return IDVERDAD;
    }

    public void setIDVERDAD(Integer IDVERDAD) {
        this.IDVERDAD = IDVERDAD;
    }

    public String getPORFAVOR() {
        return PORFAVOR;
    }

    public void setPORFAVOR(String PORFAVOR) {
        this.PORFAVOR = PORFAVOR;
    }
}
