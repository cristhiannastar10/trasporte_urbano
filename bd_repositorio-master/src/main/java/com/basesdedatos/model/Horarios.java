package com.basesdedatos.model;

import java.sql.Date;
import java.sql.Time;

public class Horarios {
    private Integer id_horario;
    private Date fecha;
    private Time hora_salida;
    private Time hora_llegada;

    public Horarios(Integer id_horario, Date fecha, Time hora_salida, Time hora_llegada) {
        this.id_horario = id_horario;
        this.fecha = fecha;
        this.hora_salida = hora_salida;
        this.hora_llegada = hora_llegada;
    }

    public Integer getId_horario() {
        return id_horario;
    }

    public void setId_horario(Integer id_horario) {
        this.id_horario = id_horario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public Time getHora_llegada() {
        return hora_llegada;
    }

    public void setHora_llegada(Time hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    
}