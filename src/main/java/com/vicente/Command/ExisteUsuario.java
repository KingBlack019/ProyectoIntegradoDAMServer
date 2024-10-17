package com.vicente.Command;

import com.vicente.Controller.Comando;
import com.vicente.Services.UsuarioService;

public class ExisteUsuario implements Comando {
    private final UsuarioService usuarioService;

    public ExisteUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void ejecutar(Object[] parametro) {

    }
}
