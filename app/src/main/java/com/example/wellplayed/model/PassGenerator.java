package com.example.wellplayed.model;

import android.util.Log;

public class PassGenerator {

    public static String NUMEROS = "0123456789";

    public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    public static String ESPECIALES = "ñÑ";

    //
    public static String getPinNumber() {
        return getPassword(NUMEROS, 4);
    }

    public static String getPassword() {
        return getPassword(8);
    }

    public static String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    public static String getPassword(String key, int length) {
        StringBuilder pswd = new StringBuilder();

        for (int i = 0; i < length; i++) {
            pswd.append(key.charAt((int) (Math.random() * key.length())));
        }

        Log.d("prueba",pswd.toString());
        return pswd.toString();
    }
}
