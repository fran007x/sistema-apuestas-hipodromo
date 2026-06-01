package ort.da.hipodromo.Dtos;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Carrera;

public class ProximaCarreraDto {
    private int numero;
    private String estado;
    private int cantidadCaballos;
    private double totalApostado;
    private int cantidadApuestas;

    public ProximaCarreraDto(Carrera carrera) {
        this.numero = carrera.getNumero();
        this.estado = carrera.getEstado().toString();
        this.cantidadCaballos = carrera.cantidadRegistros();
        this.totalApostado = carrera.totalApostado();
        this.cantidadApuestas = carrera.cantidadApuestas();
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

    public static List<ProximaCarreraDto>fromList(List<Carrera> carreras){
        List<ProximaCarreraDto> result = new ArrayList<>();
        for(Carrera carrera : carreras){
            result.add( new ProximaCarreraDto(carrera));
        }
        return result;
    }
}
