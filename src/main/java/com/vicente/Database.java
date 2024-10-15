package com.vicente;

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


    public boolean crearUsuario(String usuario, String primerApellido, String segundoApellido, String contrasena, String correoElectronico, String fechaCreacion) {
        String query = "INSERT INTO usuarios (idUsuario, nombre, apellido1, apellido2, email, contrasena, fechaCuentaCreada) VALUES (?, ?, ?, ?, ?, ?, ?)";

        System.out.println("usuario = " + usuario);
        System.out.println("primerApellido = " + primerApellido);
        System.out.println("segundoApellido = " + segundoApellido);
        System.out.println("correoElectronico = " + correoElectronico);
        System.out.println("contrasena = " + contrasena);
        System.out.println("fechaCreacion = " + fechaCreacion);

        try (Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement statement = conexion.prepareStatement(query)) {

            // Establecer los parámetros
            statement.setString(1, ServerManagement.generarIdAleatorio());
            statement.setString(2, usuario); // Utilizar el ID generado en el cliente
            statement.setString(3, primerApellido);
            statement.setString(4, segundoApellido);
            statement.setString(5, correoElectronico);
            statement.setString(6, contrasena);
            statement.setString(7, fechaCreacion);


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
}
