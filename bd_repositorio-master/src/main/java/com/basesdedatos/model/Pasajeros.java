package com.basesdedatos.model;

public class Pasajeros {
    private Integer pasajeros_id;
    private String nombre;
    private String apellidos;
    private String numeroTelefono;
    private String tipoSangre;

    public Pasajeros(Integer pasajeros_id, String nombre, String apellidos, String numeroTelefono, String tipoSangre) {
        this.pasajeros_id = pasajeros_id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.numeroTelefono = numeroTelefono;
        this.tipoSangre = tipoSangre;
    }

    public Integer getPasajeros_id() {
        return pasajeros_id;
    }

    public void setPasajeros_id(Integer pasajeros_id) {
        this.pasajeros_id = pasajeros_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    
}