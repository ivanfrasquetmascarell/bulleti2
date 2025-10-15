package com.ivanfrasquet.tema01;

import java.io.*;
import java.util.Arrays;

class Act5 implements Serializable {
    private char[][] tablero;
    private char turno;
    private int puntuacionJugador1;
    private int puntuacionJugador2;

    public Act5() {
        tablero = new char[3][3];
        for (char[] fila : tablero) Arrays.fill(fila, ' ');
        turno = 'X';
        puntuacionJugador1 = 0;
        puntuacionJugador2 = 0;
    }


    public char[][] getTablero() { return tablero; }
    public char getTurno() { return turno; }
    public int getPuntuacionJugador1() { return puntuacionJugador1; }
    public int getPuntuacionJugador2() { return puntuacionJugador2; }

    public void setCasilla(int fila, int columna, char valor) { tablero[fila][columna] = valor; }
    public void setTurno(char turno) { this.turno = turno; }
    public void setPuntuacionJugador1(int p) { this.puntuacionJugador1 = p; }
    public void setPuntuacionJugador2(int p) { this.puntuacionJugador2 = p; }
}

class GameStorage {
    /**
     * guarda el estado de la partida
     * @param estado
     * @param ruta
     * @throws IOException
     */
    public static void guardar(Act5 estado, String ruta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(estado);
        }
    }

    /**
     * carga el estado de la partida
     * @param ruta
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Act5 cargar(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return (Act5) ois.readObject();
        }
    }
}