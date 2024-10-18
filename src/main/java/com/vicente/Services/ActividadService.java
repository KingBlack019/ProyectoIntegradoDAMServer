package com.vicente.Services;

import com.vicente.Database;
import com.vicente.Model.Actividad;
import com.vicente.Model.Usuario;

public class ActividadService {
    private final Database database;

    public ActividadService(Database database) {
        this.database = database;
    }

    public Actividad convertirActividad (String cadena){
        String[] partes = cadena.split(";");
        Actividad actividad = new Actividad();

        // Asumiendo que las partes están en el mismo orden que se agregaron
        actividad.setIdActividad(partes[0]);
        actividad.setIdEncargado(partes[1]);
        actividad.setTitulo(partes[2]);
        actividad.setDescripcion(partes[3]);
        actividad.setFechaCreacion(partes[4]);

        return actividad;
    }
}
