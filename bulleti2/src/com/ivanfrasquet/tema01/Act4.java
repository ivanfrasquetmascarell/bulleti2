package com.ivanfrasquet.tema01;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Act4 {

    private final File file;
    private final String directorio;
    private final Gson gson = new Gson();
    private final MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");

    private Map<String, String> usuarios; // usuario -> contraseña encriptada

    public Act4(String directorio) throws IOException, NoSuchAlgorithmException {
        this.directorio = directorio;
        this.file = new File(directorio + "/usuarios.json");

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();

            usuarios = new HashMap<>();
            agregarUsuario("Default", "S3cret@");
        } else {
            cargarUsuarios();
        }

        menuPrincipal();
    }

    /**
     * carga lista de usuarios desde el .json
     * @throws IOException
     */
    private void cargarUsuarios() throws IOException {
        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            usuarios = gson.fromJson(reader, type);
            if (usuarios == null) usuarios = new HashMap<>();
        }
    }

    /**
     * guarda usuarios en el archivo .json
     * @throws IOException
     */
    private void guardarUsuarios() throws IOException {
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(usuarios, writer);
        }
    }

    /**
     * encrpta el texto recivido (contraseña)
     * @param texto
     * @return
     */
    private String encriptar(String texto) {
        messageDigest.update(texto.getBytes());
        byte[] encriptado = messageDigest.digest();
        return Base64.getEncoder().encodeToString(encriptado);
    }

    /**
     *  Valida usuarios con su contraseña
     * @param usuario
     * @param password
     * @return
     */
    private boolean validar(String usuario, String password) {
        if (!usuarios.containsKey(usuario)) return false;
        String hash = encriptar(password);
        return hash.equals(usuarios.get(usuario));
    }

    /**
     * agrega usuario a la lista
     * @param usuario
     * @param password
     * @throws IOException
     */
    private void agregarUsuario(String usuario, String password) throws IOException {
        usuarios.put(usuario, encriptar(password));
        guardarUsuarios();
    }

    /**
     * guarda el usuario con la nueva contraseña
     * @param usuario
     * @return
     * @throws IOException
     */
    private boolean cambiarPassword(String usuario) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Escribe la nueva contraseña:");
        String nuevaPass = br.readLine();
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$";

        if (nuevaPass.matches(regex)) {
            usuarios.put(usuario, encriptar(nuevaPass));
            guardarUsuarios();
            return(true);
        } else {
            return (false);
        }
    }

    /**
     * imprime el menu
     * @throws IOException
     */
    private void menuPrincipal() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion;

        do {
            System.out.println("*****************");
            System.out.println("1. Validar Acceso");
            System.out.println("2. Crear Usuario");
            System.out.println("3. Salir");
            System.out.println("*****************");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1 -> {
                    System.out.println("Usuario:");
                    String usuario = br.readLine();
                    System.out.println("Contrasena:");
                    String password = br.readLine();

                    if (validar(usuario, password)) {
                        System.out.println("Acceso concedido.");
                        menuUsuario(usuario);
                    } else {
                        System.out.println("Usuario o contraseña incorrecta.");
                    }
                }
                case 2 -> {
                    System.out.println("Nuevo usuario:");
                    String nuevoUsuario = br.readLine();
                    System.out.println("Contraseña:");
                    String nuevaPassword = br.readLine();
                    agregarUsuario(nuevoUsuario, nuevaPassword);
                    System.out.println("Usuario creado con éxito.");
                }
                case 3 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 3);
    }

    /**
     * imprime el menu de usuario
     * @param usuario
     * @throws IOException
     */
    private void menuUsuario(String usuario) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        do {
            System.out.println("*****************");
            System.out.println("1. Cambiar contraseña");
            System.out.println("2. Salir al menú principal");
            System.out.println("*****************");
            opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1 -> System.out.println(cambiarPassword(usuario));
                case 2 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 2);
    }
}
