package com.vicente.Controller;

import com.vicente.Services.UsuarioService;

public class IniciarUsuario implements Comando {
    private final UsuarioService usuarioService;
    public IniciarUsuario(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public void ejecutar(Object[] parametro) {

    }
}
