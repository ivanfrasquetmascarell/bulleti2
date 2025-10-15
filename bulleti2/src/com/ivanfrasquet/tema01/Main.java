package com.ivanfrasquet.tema01;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
        System.out.println();
        System.out.println("Hashmap");
        System.out.println();

        MyHashMap hashMap = new MyHashMap(10);
        hashMap.put("ivan", 16);
        System.out.println(hashMap.get("ivan"));


        //System.out.println();
        //System.out.println("Act3");
        //System.out.println();

        //Act3 a3 = new Act3("bulleti2/ficheros");


        System.out.println();
        System.out.println("Act4");
        System.out.println();

        Act4 a4 = new Act4("bulleti2/ficheros");

        System.out.println();
        System.out.println("Act5");
        System.out.println();

        Act5 a5 = new Act5();
        a5.setPuntuacionJugador1(100);
        Act5 a5_1 = new Act5();
        GameStorage g1 = new GameStorage();
        g1.guardar(a5, "ficheros/estado.txt");
        a5_1 = g1.cargar("ficheros/estado.txt");
        System.out.println(a5_1.getPuntuacionJugador1());

        System.out.println();
        System.out.println("Act6");
        System.out.println();

        Act6 a6 = new Act6("ficheros");
        a6.calcularYGuardarPrimos(10);
        a6.calcularYGuardarPrimos(10);

    }
}



