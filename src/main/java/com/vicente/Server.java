package com.vicente;

import com.vicente.Controller.ServerController;
import com.vicente.Services.UsuarioService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Server implements Runnable
{
    private final Socket socket;
    private final ServerController serverController;

    public Server(Socket socket, ServerController serverController) {
        this.socket = socket;
        this.serverController = serverController;
    }
    static int puerto = 1234;

    public static void main(String[] args) {

        try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Crear servicios para el servidor
            Database database = new Database();

            UsuarioService usuarioService = new UsuarioService(database);

            ServerController serverController = new ServerController(servidorSocket, usuarioService);

            // Aceptar múltiples conexiones en un bucle infinito
            while (true) {
                Socket socketCliente = servidorSocket.accept();
                System.out.println("Cliente conectado: " + socketCliente.getInetAddress());

                // Crear un nuevo hilo para manejar al cliente
                new Thread(new Server(socketCliente, serverController)).start();
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
                serverController.gestionarOrden(entrada, salida, mensaje);
            }

            }catch (IOException ex) {
            throw new RuntimeException(ex);
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