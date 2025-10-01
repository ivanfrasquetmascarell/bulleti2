package com.ivanfrasquet.tema01;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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

        Act3 a3 = new Act3("bulleti2/ficheros");

    }
}
