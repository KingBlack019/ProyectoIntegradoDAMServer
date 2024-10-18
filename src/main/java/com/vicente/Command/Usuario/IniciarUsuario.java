package com.vicente.Command.Usuario;

import com.vicente.Controller.Comando;
import com.vicente.Services.UsuarioService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class IniciarUsuario implements Comando {
    private final UsuarioService usuarioService;

    public IniciarUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void ejecutar(BufferedReader entrada, PrintWriter salida) {
        try {

            String nombreUsuario = entrada.readLine();
            String contrasenaUsuario = entrada.readLine();

            usuarioService.iniciarSesionUsuario(nombreUsuario, contrasenaUsuario);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
