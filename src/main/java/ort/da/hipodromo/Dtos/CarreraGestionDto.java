package ort.da.hipodromo.Dtos;

import java.time.LocalDate;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Carrera;

public class CarreraGestionDto {
    private LocalDate fechaJornada;
    private int numero;
    private String nombre;
    private String estado;
    private double totalApostado;
    private List<RegistroGestionDto> caballosParticipantes;

    public CarreraGestionDto(LocalDate fechaJornada, Carrera carrera, double comision) {
        this.fechaJornada = fechaJornada;
        this.numero = carrera.getNumero();
        this.nombre = carrera.getNombre();
        this.estado = carrera.getEstado().toString();
        this.totalApostado = carrera.totalApostado();
        this.caballosParticipantes = RegistroGestionDto.fromList(carrera.getRegistros(),carrera.totalApostado(),comision);
    }

    public LocalDate getFechaJornada() {
        return fechaJornada;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public List<RegistroGestionDto> getCaballosParticipantes() {
        return caballosParticipantes;
    }
}
