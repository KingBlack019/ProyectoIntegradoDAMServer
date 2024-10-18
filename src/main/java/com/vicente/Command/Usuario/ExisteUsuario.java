package com.vicente.Command.Usuario;

import com.vicente.Controller.Comando;
import com.vicente.Services.UsuarioService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ExisteUsuario implements Comando {
    private final UsuarioService usuarioService;

    public ExisteUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void ejecutar(BufferedReader entrada, PrintWriter salida) {
        try {
            String texto = entrada.readLine();

            // TODO hay que comprobar si existe o no a la base de datos
            if (texto != null) {
                salida.println(true);
                System.out.println("EXISTE USUARIO EJECUTADO");
            } else {
                System.out.println("No se recibio el nombre de usuario");
            }
            salida.println(false);
            System.out.println(texto); // todo va a leer primer el nombre de usuario para comprobar si existe, enn nuestro caso saltamos esto

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
