package com.basesdedatos.model;

public class Rutas {
    private Integer id_rutas;
    private String punto_partida;
    private String destino;
    private Integer horario;
    private String trayectoria;
    private Integer cantidad_pasajeros;

    public Rutas(Integer id_rutas, String punto_partida, String destino, Integer horario, String trayectoria, Integer cantidad_pasajeros) {
        this.id_rutas = id_rutas;
        this.punto_partida = punto_partida;
        this.destino = destino;
        this.horario = horario;
        this.trayectoria = trayectoria;
        this.cantidad_pasajeros = cantidad_pasajeros;
    }

    public Integer getId_rutas() {
        return id_rutas;
    }

    public void setId_rutas(Integer id_rutas) {
        this.id_rutas = id_rutas;
    }

    public String getPunto_partida() {
        return punto_partida;
    }

    public void setPunto_partida(String punto_partida) {
        this.punto_partida = punto_partida;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    public String getTrayectoria() {
        return trayectoria;
    }

    public void setTrayectoria(String trayectoria) {
        this.trayectoria = trayectoria;
    }

    public Integer getCantidad_pasajeros() {
        return cantidad_pasajeros;
    }

    public void setCantidad_pasajeros(Integer cantidad_pasajeros) {
        this.cantidad_pasajeros = cantidad_pasajeros;
    }

    
}