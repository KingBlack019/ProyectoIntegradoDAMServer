package com.vicente.Command;

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

            Usuario nuevoUsuario = new Usuario().convertirAUsuario(cadenaADescomprimir);
            System.out.println("nuevoUsuario = " + nuevoUsuario);
            // todo descomentar y agregar db usuarioService.crearUsuario(new Usuario().convertirAUsuario(cadenaADescomprimir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

/*
    // TODO LOS DATOS DE ENTRADA Y DE SALIDA SE DEBEN DE GESTIONAR DESDE FUERA DE OTRA FORMA NO METIENDO EL SOCKET DEL TIRON
    @Override
    public void ejecutar(Socket sk) {

        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(sk.getInputStream()));
            PrintWriter salida = new PrintWriter(sk.getOutputStream(), true);

            System.out.println(entrada.readLine()); // todo va a leer primer el nombre de usuario para comprobar si existe, enn nuestro caso saltamos esto

            salida.println(true);

            String cadenaADescomprimir = entrada.readLine();
            System.out.println(cadenaADescomprimir); // cadena a descomprimir

            Usuario nuevoUsuario = new Usuario().convertirAUsuario(cadenaADescomprimir);
            System.out.println("nuevoUsuario = " + nuevoUsuario);
            // todo descomentar y agregar db usuarioService.crearUsuario(new Usuario().convertirAUsuario(cadenaADescomprimir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
*/
}
