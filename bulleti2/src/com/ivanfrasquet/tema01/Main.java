package com.ivanfrasquet.tema01;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.println();
        System.out.println("Hashmap");
        System.out.println();

        MyHashMap hashMap = new MyHashMap(10);
        hashMap.put("ivan", 16);
        System.out.println(hashMap.get("ivan"));


        System.out.println();
        System.out.println("Act3");
        System.out.println();

        Act3 a3 = new Act3("bulleti2/ficheros");

    }
}
