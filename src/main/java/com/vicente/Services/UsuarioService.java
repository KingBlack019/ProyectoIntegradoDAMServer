package com.vicente.Services;

import com.vicente.Database;
import com.vicente.Model.Usuario;

public class UsuarioService {
    private final Database database;

    public UsuarioService(Database database) {
        this.database = database;
    }

    public void crearUsuario(Usuario usuario){
        database.crearUsuario(usuario);
    }

    public void buscarUsuarioPorId(String id){
        database.buscarUsuario(id);
    }

    public void eliminarUsuario(String id){
        database.eliminarUsuario(id);
    }

}
