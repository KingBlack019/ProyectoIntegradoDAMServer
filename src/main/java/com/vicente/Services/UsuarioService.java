package com.vicente.Services;

import com.vicente.Database;
import com.vicente.Model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {
    private final Database database;

    public UsuarioService(Database database) {
        this.database = database;
    }

    public void crearUsuario(Usuario usuario){
        database.crearUsuario(usuario);
    }

    public void buscarUsuarioPorId(String id){
        database.buscarUsuario(id);
    }

    public void eliminarUsuario(String id){
        database.eliminarUsuario(id);
    }

    public void iniciarSesionUsuario(String nombreUsuario, String contrasenaUsuario) {

        String contrasenaHasheada = database.obtenerContrasena(nombreUsuario);
        System.out.println("contrasenaHasheada = " + contrasenaHasheada);

        if(checkPassword(contrasenaUsuario, contrasenaHasheada))
            System.out.println("Se ha iniciado sesion correctamente");
        else
            System.out.println("Contrasena incorrecta");

    }



    public Usuario convertirAUsuario (String cadena){
        String[] partes = cadena.split(";");
        Usuario usuario = new Usuario();

        // Asumiendo que las partes están en el mismo orden que se agregaron
        usuario.setNombre(partes[0]);
        usuario.setPrimerApellido(partes[1]);
        usuario.setSegundoApellido(partes[2]);
        usuario.setContrasena(partes[3]);
        usuario.setEmail(partes[4]);
        usuario.setFechaRegistro(partes[5]);

        return usuario;
    }


    // Hashear una contraseña
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Verificar una contraseña
    public static boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}