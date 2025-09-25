package com.ivanfrasquet.tema01;

import java.util.ArrayList;

public class Hashmap {
    private ArrayList<String> keys = new ArrayList<String>();
    private ArrayList<Object> values = new ArrayList<Object>();
    private int pos = 0;


    public Hashmap(String Key, Object Value) {
        keys.add(0, Key);
        values.add(0, Value);
        pos++;
    }

    /**
     * añade un valor enlazado a una key string a las listas
     * @param Key
     * @param Value
     */
    public void add(String Key, Object Value) {
        keys.add(pos, Key);
        values.add(pos, Value);
        pos++;
    }

    /**
     * devuelbe el valor enlazado a la key que se le pasa
     * @param key
     * @return valor2 o null
     */
    public Object getvalue(String key) {
        int cont = 0;
        for (String i : keys) {
            if (key.equals(i)) {
                Object valor2 = values.get(cont);
                return valor2;
            }
            cont++;
        }
        return null;
    }
}
