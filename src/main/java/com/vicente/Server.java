package com.vicente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class Server implements Runnable
{
    private final Socket socket;
    private static ServerManagement serverManagement = new ServerManagement();

    public Server(Socket socket, ServerManagement serverManagement) {
        this.socket = socket;
        Server.serverManagement = serverManagement;
    }
    static int puerto = 1234;

    public static void main(String[] args) {

        try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Aceptar múltiples conexiones en un bucle infinito
            while (true) {
                Socket socketCliente = servidorSocket.accept();
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress());

                // Crear un nuevo hilo para manejar al cliente
                new Thread(new Server(socketCliente, new ServerManagement())).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void run() {
        try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido del cliente: " + mensaje);

                if (serverManagement.mismoTexto(mensaje, Ordenes.REGISTRAR.toString())){
                    System.out.println("PROCESO REGISTRO");
                    salida.println(serverManagement.existeUsuario(entrada.readLine()));

                    // RECIBIR 7 STRINGS

                    String usuario = entrada.readLine();
                    String contrasena = entrada.readLine();
                    String primerApellido = entrada.readLine();
                    String segundoApellido = entrada.readLine();
                    String correoElectronico = entrada.readLine();
                    String fechaCreacion = entrada.readLine();
                    String rol = entrada.readLine();

                    System.out.println("usuario = " + usuario);
                    System.out.println("primerApellido = " + primerApellido);
                    System.out.println("segundoApellido = " + segundoApellido);
                    System.out.println("correoElectronico = " + correoElectronico);
                    System.out.println("contrasena = " + contrasena);
                    System.out.println("fechaCreacion = " + fechaCreacion);

                    System.out.println(serverManagement.crearUsuario(usuario, primerApellido, segundoApellido, contrasena, correoElectronico, rol, fechaCreacion));

                }

                if(serverManagement.mismoTexto(mensaje, Ordenes.LOGUEAR.toString())){
                    boolean resultado = serverManagement.loguear(entrada.readLine(), entrada.readLine());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Conexión cerrada con el cliente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
}

