package com.vicente.Controller;

import com.vicente.Model.Usuario;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public interface Comando {
    void ejecutar(BufferedReader entrada, PrintWriter salida);

}
