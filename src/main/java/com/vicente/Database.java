package com.vicente;

import com.vicente.Controller.ServerController;
import com.vicente.Model.Usuario;

import java.sql.*;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/vmotiondb"; // Cambia el nombre de tu base de datos
    private static final String USUARIO = "vmotion";
    private static final String CONTRASENA = "vmotion";

    public boolean usuarioExiste(String username){
        boolean existe = false;
        String query = "SELECT COUNT(*) FROM usuarios WHERE nombre = ?";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conexion.prepareStatement(query)) {

            // Establecer el parámetro en la consulta
            statement.setString(1, username);

            // Ejecutar la consulta
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    existe =  resultSet.getInt(1) > 0 ;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(existe);
        return existe;
    }

    public boolean conectarBaseDeDatos() {
        try(Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA)){
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean loguear(String username, String password) {
        boolean existe = false;

        return existe;
    }


    public boolean crearUsuario(Usuario usuario) {
        String query = "INSERT INTO usuarios (idUsuario, nombre, apellido1, apellido2, email, contrasena, fechaCuentaCreada) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conexion.prepareStatement(query)) {

            // Establecer los parámetros
            statement.setString(1, ServerController.generarIdAleatorio());
            statement.setString(2, usuario.getNombre()); // Utilizar el ID generado en el cliente
            statement.setString(3, usuario.getPrimerApellido());
            statement.setString(4, usuario.getSegundoApellido());
            statement.setString(5, usuario.getEmail());
            statement.setString(6, usuario.getContrasena());
            statement.setString(7, usuario.getFechaRegistro());


            // Ejecutar la inserción
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario creado exitosamente.");
                return true;
            } else {
                System.out.println("Error al crear el usuario.");
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void buscarUsuario(String id) {
    }

    public void eliminarUsuario(String id) {
    }

    public void iniciarSesionUsuario(String nombreUsuario, String contrasena) {

    }

    // TODO obtener la contraseña hasheada de la base de datos
    public String obtenerContrasena(String nombreUsuario){
        return ""
    }
}
