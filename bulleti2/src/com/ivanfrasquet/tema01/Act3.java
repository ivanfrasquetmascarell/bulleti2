package com.ivanfrasquet.tema01;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Act3 {
    private final File file;
    private String Directorio;
    private byte[] password;
    private byte[] encriptado;
    private final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

    public Act3(String Directorio) throws IOException, NoSuchAlgorithmException {
        this.Directorio = Directorio;
        file = new File(Directorio + "/" + "properties.txt");

        if (!file.exists()) {
            file.createNewFile();
            password = "S3cret@".getBytes();
            messageDigest.update(password);
            encriptado = messageDigest.digest();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            try {
                bw.write(Base64.getEncoder().encodeToString(encriptado));
            } finally {
                bw.close();
                fw.close();
            }
        } else {
            file.delete();
            file.createNewFile();
            password = "S3cret@".getBytes();
            messageDigest.update(password);
            encriptado = messageDigest.digest();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            try {
                bw.write(Base64.getEncoder().encodeToString(encriptado));
            } finally {
                bw.close();
                fw.close();
            }
        }
        int opcion;
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("*************************");
                System.out.println("1- Validar Acceso");
                System.out.println("*************************");
                opcion = Integer.parseInt(br.readLine());
                if (opcion == 1) {
                    System.out.println("Escribe tu contraseña");
                    byte[] password2 = br.readLine().getBytes();
                    if (!validar(password2)) {
                        System.out.println("Contraseña incorrecta");
                        opcion = 0;
                    } else menu();
                }

        } while (opcion != 1);
    }


    public File getFile() {
        return file;
    }

    public boolean validar(byte[] password2) throws IOException {
        messageDigest.update(password2);
        byte[] encriptado2 = messageDigest.digest();
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        try {
            encriptado = Base64.getDecoder().decode(br.readLine());
        } finally {
            br.close();
            fr.close();
        }
        if (Arrays.equals(encriptado2, encriptado)) {
            return true;
        }
        return false;
    }

    public void menu() throws IOException {
        int opcion;
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("*************************");
                System.out.println("1- Modificar Contraseña");
                System.out.println("2- Salir");
                System.out.println("*************************");
                opcion = Integer.parseInt(br.readLine());
                if (opcion == 1) {
                    System.out.println("Escribe tu contraseña");
                    byte[] password2 = br.readLine().getBytes();
                    if (!validar(password2)) {
                        System.out.println("Contraseña incorrecta");
                        opcion = 0;
                    } else cambiarPassword();
                }
        } while (opcion != 2);
    }

        public void cambiarPassword() throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe tu nueva contraseña");
            String Contrasenya = br.readLine();
            String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";
            if(Contrasenya.matches(regex)) {
                file.delete();
                file.createNewFile();
                password = Contrasenya.getBytes();
                messageDigest.update(password);
                encriptado = messageDigest.digest();
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                try {
                    bw.write(Base64.getEncoder().encodeToString(encriptado));
                } finally{
                    bw.close();
                    fw.close();
                }
            } else {
                System.out.println("La contraseña no cumple los requisitos");
                System.out.println();
                cambiarPassword();
            }
        }

}
