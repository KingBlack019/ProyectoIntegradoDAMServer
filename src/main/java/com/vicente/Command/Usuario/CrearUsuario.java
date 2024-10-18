package com.vicente.Command.Usuario;

import com.vicente.Controller.Comando;
import com.vicente.Model.Usuario;
import com.vicente.Services.UsuarioService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;

public class CrearUsuario implements Comando {
    private final UsuarioService usuarioService;
    private final ServerSocket serverSocket;

    public CrearUsuario(UsuarioService usuarioService, ServerSocket serverSocket) {
        this.usuarioService = usuarioService;
        this.serverSocket = serverSocket;
    }

    @Override
    public void ejecutar(BufferedReader entrada, PrintWriter salida) {
        try{
            String cadenaADescomprimir = entrada.readLine();
            System.out.println(cadenaADescomprimir); // cadena a descomprimir

            Usuario nuevoUsuario = usuarioService.convertirAUsuario(cadenaADescomprimir);
            System.out.println("nuevoUsuario = " + nuevoUsuario);
            usuarioService.crearUsuario(nuevoUsuario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}