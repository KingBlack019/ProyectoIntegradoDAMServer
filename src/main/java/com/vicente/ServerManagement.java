package com.vicente;

import java.util.Random;

public class ServerManagement {

    private Database baseDeDatos = new Database();

    public ServerManagement() {
    }

    public boolean mismoTexto(String txt1, String txt2){
        return txt1.compareTo(txt2) == 0;
    }

    //  retorna la comprobacion entre si existe el usuario, si no es así lo crea
    public boolean crearUsuario(String usuario, String primerApellido, String segundoApellido, String contrasena, String correoElectronico, String rol, String fechaCreacion) {
        if (!baseDeDatos.usuarioExiste(usuario)) {
            return baseDeDatos.crearUsuario(usuario, primerApellido, segundoApellido, contrasena, correoElectronico, fechaCreacion);
        }
        return false; // Usuario ya existe

    }

    public boolean conectarBaseDeDatos() {
        try {
            return baseDeDatos.conectarBaseDeDatos();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean loguear(String username, String password) {
        return baseDeDatos.loguear(username, password);
    }

    // Metodo para generar un ID aleatorio de 10 caracteres alfanuméricos
    protected static String generarIdAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(caracteres.length());
            idBuilder.append(caracteres.charAt(index));
        }
        return idBuilder.toString();
    }

    public boolean existeUsuario(String s) {
        System.out.println(s);
        return !baseDeDatos.usuarioExiste(s);
    }
}
