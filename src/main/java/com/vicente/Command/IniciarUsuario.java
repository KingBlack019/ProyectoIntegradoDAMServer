package com.vicente.Command;

import com.vicente.Controller.Comando;
import com.vicente.Services.UsuarioService;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class IniciarUsuario implements Comando {
    private final UsuarioService usuarioService;
    public IniciarUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void ejecutar(BufferedReader entrada, PrintWriter salida) {

    }
/*
    @Override
    public void ejecutar(Socket sk) {

    }*/
}
