package com.vicente.Command;

import com.vicente.Controller.Comando;
import com.vicente.Controller.ServerController;
import com.vicente.Model.Usuario;
import com.vicente.Services.UsuarioService;

import java.net.ServerSocket;
import java.net.Socket;

public class CrearUsuario implements Comando {
    private final UsuarioService usuarioService;
    private final ServerSocket serverSocket;

    public CrearUsuario(UsuarioService usuarioService, ServerSocket serverSocket) {
        this.usuarioService = usuarioService;
        this.serverSocket = serverSocket;
    }


    @Override
    public void ejecutar(Object[] parametro) {
        usuarioService.crearUsuario(new Usuario().convertirAUsuario((String) parametro[1]));
    }

}
