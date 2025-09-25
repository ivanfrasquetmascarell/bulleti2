package com.ivanfrasquet.tema01;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println();
        System.out.println("Hashmap");
        System.out.println();

        Hashmap hm = new Hashmap("llave", 43);
        System.out.println(hm.getvalor("llave"));

    }
}
