package com.ivanfrasquet.tema01;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.println();
        System.out.println("Hashmap");
        System.out.println();

        Hashmap hm = new Hashmap("llave", 43);
        System.out.println(hm.getvalue("llave"));

        System.out.println();
        System.out.println("Act3");
        System.out.println();

        Act2 a2 = new Act2("bulleti2/ficheros");

    }
}
