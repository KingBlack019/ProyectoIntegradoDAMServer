package com.vicente.Model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String contrasena;
    private String email;
    private String fechaRegistro;

    public Usuario() {}

    public Usuario(String nombre, String primerApellido, String segundoApellido, String contrasena, String email, String fechaRegistro) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.contrasena = contrasena;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario convertirAUsuario (String cadena){
        String[] partes = cadena.split(";");
        Usuario usuario = new Usuario();

        // Asumiendo que las partes están en el mismo orden que se agregaron
        usuario.setNombre(partes[0]);
        usuario.setPrimerApellido(partes[1]);
        usuario.setSegundoApellido(partes[2]);
        usuario.setContrasena(partes[3]);
        usuario.setEmail(partes[4]);
        usuario.setFechaRegistro(partes[5]);

        return usuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro='" + fechaRegistro + '\'' +
                '}';
    }
}
