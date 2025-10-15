package com.ivanfrasquet.tema01;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

public class Act3 {
    private final File file;
    private final Properties properties;
    private final String directorio;
    private byte[] password;
    private byte[] encriptado;
    private final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
    public Act3(String directorio) throws IOException, NoSuchAlgorithmException {
        this.directorio = directorio;
        properties = new Properties();
        file = new File(directorio + "/config.properties");
        if (!file.exists()) {
            file.createNewFile();
            password = "S3cret@".getBytes();
            messageDigest.update(password);
            encriptado = messageDigest.digest();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            properties.setProperty("passwd", Base64.getEncoder().encodeToString(encriptado));
            try {
                properties.store(bw, "");
            } finally {
                bw.close();
                fw.close();
            }
        } else {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            try {
                properties.load(br);
            } finally {
                br.close();
                fr.close();
            }
        }
        int opcion;
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("*****************");
            System.out.println("1. Validar Acceso");
            System.out.println("*****************");
            opcion = Integer.parseInt(br.readLine());
            if (opcion == 1) {
                System.out.println("Escribe la contraseña");
                byte[] password2 = br.readLine().getBytes();
                if(!validar(password2)) {
                    System.out.println("Contraseña incorrecta");
                    opcion = 0;
                } else menu();
            }
        } while (opcion != 1);
    }
    public File getFile(){
        return file;
    }

    /**
     * Valida que la contraseña escrita sea la misma que la del fichero
     * @param password2
     * @return
     * @throws IOException
     */
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

    /**
     * Lanza el menu
     * @throws IOException
     */
    public void menu() throws IOException {
        int opcion;
        do {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("*****************");
            System.out.println("1. Modificar contraseña");
            System.out.println("2. Salir");
            System.out.println("*****************");
            opcion = Integer.parseInt(br.readLine());
            if (opcion == 1) {
                System.out.println("Escribe la contraseña");
                byte[] password2 = br.readLine().getBytes();
                if(!validar(password2)) {
                    System.out.println("Contraseña incorrecta");
                    opcion = 0;
                } else cambiarPassword();
            }
        } while (opcion != 2);
    }

    /**
     * Cambia la contraseña del fichero
     * @throws IOException
     */
    public void cambiarPassword() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Escribe la nueva contraseña");
        String contrasenya = br.readLine();
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";
        if (contrasenya.matches(regex)) {
            file.delete();
            file.createNewFile();
            password = contrasenya.getBytes();
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
            System.out.println("La contraseña no cumple los requisitos");
            System.out.println();
            cambiarPassword();
        }

    }
}

