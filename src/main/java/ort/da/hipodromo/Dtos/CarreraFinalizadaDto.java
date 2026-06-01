package ort.da.hipodromo.Dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Carrera;

public class CarreraFinalizadaDto {
      private int numero;
    private LocalDateTime horaFinalizacion;
    private int cantidadCaballos;

    private double totalApostado;
    private double totalPagado;

    private String caballoGanador;
    private double dividendoFinalGanador;

    public CarreraFinalizadaDto(Carrera carrera) {
        this.numero = carrera.getNumero();
        this.horaFinalizacion = carrera.getHoraFinalizacion();
        this.cantidadCaballos = carrera.cantidadRegistros();
        this.totalApostado = carrera.totalApostado();
        this.totalPagado = carrera.totalPagado();
        this.caballoGanador = carrera.getCaballoGanador().getCaballo().getNombre();
        this.dividendoFinalGanador = carrera.dividendoFinalGanador();
    }

    

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public int getCantidadCaballos() {
        return cantidadCaballos;
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public String getCaballoGanador() {
        return caballoGanador;
    }

    public double getDividendoFinalGanador() {
        return dividendoFinalGanador;
    }

    public static List<CarreraFinalizadaDto>fromList(List<Carrera> carreras){
     List<CarreraFinalizadaDto> result = new ArrayList<>();
        for(Carrera carrera : carreras){
            result.add(new CarreraFinalizadaDto(carrera));
        }
        return result;
    }
}
