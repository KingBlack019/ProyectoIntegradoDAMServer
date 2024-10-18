package com.vicente.Command.Usuario;

import com.vicente.Controller.Comando;
import com.vicente.Services.UsuarioService;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class EliminarUsuario implements Comando {
    private final UsuarioService usuarioService;

    public EliminarUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /*
    @Override
    public void ejecutar(Socket sk) {

    }*/

    @Override
    public void ejecutar(BufferedReader entrada, PrintWriter salida) {

    }
}
