package ort.da.hipodromo.Dtos;

import java.time.LocalDate;

import ort.da.hipodromo.modelo.Carrera.Carrera;
import ort.da.hipodromo.modelo.Carrera.Jornada;

public class CarreraDisponibleDto {
    private LocalDate fechaJornada;
    private int numeroCarrera;
    private String nombreCarrera;
    private String estado;
    private int cantidadCaballos;
    private double totalApostado;
    private int cantidadApuestas;

    public CarreraDisponibleDto(Jornada jornada, Carrera carrera) {
        this.fechaJornada = jornada.getFecha();
        this.numeroCarrera = carrera.getNumero();
        this.nombreCarrera = carrera.getNombre();
        this.estado = carrera.getEstado().toString();
        this.cantidadCaballos = carrera.cantidadRegistros();
        this.totalApostado = carrera.totalApostado();
        this.cantidadApuestas = carrera.cantidadApuestas();
    }

    public LocalDate getFechaJornada() {
        return fechaJornada;
    }

    public int getNumeroCarrera() {
        return numeroCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public String getEstado() {
        return estado;
    }

    public int getCantidadCaballos() {
        return cantidadCaballos;
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public int getCantidadApuestas() {
        return cantidadApuestas;
    }

    
}
