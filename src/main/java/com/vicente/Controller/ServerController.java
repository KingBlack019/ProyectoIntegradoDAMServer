package com.vicente.Controller;

import com.vicente.Command.Usuario.CrearUsuario;
import com.vicente.Command.Usuario.ExisteUsuario;
import com.vicente.Command.Usuario.IniciarUsuario;
import com.vicente.Ordenes;
import com.vicente.Services.UsuarioService;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ServerController {
    private final Map<String, Comando> comandos;
    private final UsuarioService usuarioService;

    // SE CREAN TODOS LOS COMANDOS Y SE GESTIONA
    public ServerController(ServerSocket serverSocket, UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        comandos = new HashMap<>();

        // COMANDOS RELACIONADOS CON EL USUARIO
        comandos.put(Ordenes.CREAR_USUARIO.toString(), new CrearUsuario(usuarioService, serverSocket));
        comandos.put(Ordenes.LOGUEAR_USUARIO.toString(), new IniciarUsuario(usuarioService));
        comandos.put(Ordenes.EXISTE_USUARIO.toString(), new ExisteUsuario(usuarioService));
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

    public void gestionarOrden(BufferedReader entrada, PrintWriter salida, String orden) {
        try{
            Comando comando = comandos.get(orden);
            if (comando != null) {
                comando.ejecutar(entrada, salida);
            } else
                System.out.println("Orden no reconocida.");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
