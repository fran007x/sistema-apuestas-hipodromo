package ort.da.hipodromo.Dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Carrera;

public class ProximaCarreraDto {
    private LocalDate fechaJornada;
    private int numero;
    private String estado;
    private int cantidadCaballos;
    private double totalApostado;
    private int cantidadApuestas;

    public ProximaCarreraDto(LocalDate fechaJornada, Carrera carrera) {
        this.fechaJornada = fechaJornada;
        this.numero = carrera.getNumero();
        this.estado = carrera.getEstado().toString();
        this.cantidadCaballos = carrera.cantidadRegistros();
        this.totalApostado = carrera.totalApostado();
        this.cantidadApuestas = carrera.cantidadApuestas();
    }

    public LocalDate getFechaJornada() {
        return fechaJornada;
    }

    public int getNumero() {
        return numero;
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

    public static List<ProximaCarreraDto>fromList(LocalDate fechaJornada, List<Carrera> carreras){
        List<ProximaCarreraDto> result = new ArrayList<>();
        for(Carrera carrera : carreras){
            result.add( new ProximaCarreraDto(fechaJornada, carrera));
        }
        return result;
    }
}
