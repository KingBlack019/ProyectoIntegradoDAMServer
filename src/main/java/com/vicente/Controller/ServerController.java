package com.vicente.Controller;

import com.vicente.Command.CrearUsuario;
import com.vicente.Command.ExisteUsuario;
import com.vicente.Ordenes;
import com.vicente.Services.UsuarioService;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ServerController {
    private final ServerSocket serverSocket;
    private final Map<String, Comando> comandos;
    private final UsuarioService usuarioService;

    // SE CREAN TODOS LOS COMANDOS Y SE GESTIONA
    public ServerController(ServerSocket serverSocket, UsuarioService usuarioService) {
        this.serverSocket = serverSocket;
        this.usuarioService = usuarioService;
        comandos = new HashMap<>();

        // COMANDOS RELACIONADOS CON EL USUARIO
        comandos.put(Ordenes.CREAR_USUARIO.toString(), new CrearUsuario(usuarioService, serverSocket));
        comandos.put(Ordenes.LOGUEAR_USUARIO.toString(), new IniciarUsuario(usuarioService));
        comandos.put(Ordenes.EXISTEUSER.toString(), new ExisteUsuario(usuarioService));
    }

    public void gestionarOrden(String orden, Object... parametros) {
        Comando comando = comandos.get(orden);
        if (comando != null)
            comando.ejecutar(parametros);
        else
            System.out.println("Orden no reconocida.");
    }


    // Metodo para generar un ID aleatorio de 10 caracteres alfanuméricos
    public static String generarIdAleatorio() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder idBuilder = new StringBuilder(10);

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(caracteres.length());
            idBuilder.append(caracteres.charAt(index));
        }
        return idBuilder.toString();
    }

    public boolean mismoTexto(String txt1, String txt2){
        return txt1.compareTo(txt2) == 0;
    }

}
