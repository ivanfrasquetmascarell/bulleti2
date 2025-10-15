package com.ivanfrasquet.tema01;

import java.io.*;

public class Act6 {

    private static final String archivo_primos = "primos.txt";
    private String directorio = "";

    public Act6(String directorio) {
        this.directorio = directorio;
    }

    /**
     * lee el ultimo numero primo del archivo
     * @return
     * @throws IOException
     * @throws NumberFormatException
     */
    private long obtenerUltimoPrimo() throws IOException, NumberFormatException {
        File archivo = new File(directorio + "/" + archivo_primos);
        if (!archivo.exists()) {
            return 1;
        }

        BufferedReader br = null;
        String ultimo = null;

        try {
            br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                ultimo = linea;
            }
            if (ultimo != null) {
                return Long.parseLong(ultimo.trim());
            }
        } catch (FileNotFoundException | EOFException | NumberFormatException e) {
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }

        return 1;
    }

    /**
     *  escribe los numeros primos indicados en el archivo
     * @param cantidad
     * @throws IOException
     */
    public void calcularYGuardarPrimos(int cantidad) throws IOException {
        BufferedWriter bw = null;
        long ultimoPrimo = obtenerUltimoPrimo();
        long num = ultimoPrimo + 1;
        int encontrados = 0;

        try {
            bw = new BufferedWriter(new FileWriter(directorio + "/" + archivo_primos, true));

            while (encontrados < cantidad) {
                if (esPrimo(num)) {
                    bw.write(Long.toString(num));
                    bw.newLine();
                    encontrados++;
                }
                num++;
            }
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }

    /**
     * verifica que el numero sea primo
     * @param n
     * @return
     */
    private boolean esPrimo(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}