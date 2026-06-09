package ort.da.hipodromo.Dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.Apuesta;

public class ApuestaDto {
    private LocalDateTime fecha;
    private int numeroCarrera;
    private String nombreCarrera;
    private int numeroCaballo;
    private String nombreCaballo;
    private double monto;
    private String modalidad;
    private double dividendoFinal;
    private double montoCobrado;
    private String estado;

    public ApuestaDto(Apuesta apuesta){
        this.fecha = apuesta.getFecha();
        this.numeroCarrera = apuesta.getRegistro().getCarrera().getNumero();
        this.nombreCarrera = apuesta.getRegistro().getCarrera().getNombre();
        this.numeroCaballo = apuesta.getRegistro().getNumeroCaballo();
        this.nombreCaballo = apuesta.getRegistro().getCaballo().getNombre();
        this.monto = apuesta.getMonto();
        this.modalidad = apuesta.getModalidad().getNombre();
        this.dividendoFinal = apuesta.getDividendoFinal();
        this.montoCobrado = apuesta.getMontoCobrado();
        this.estado = apuesta.getEstado().toString().replace("_", " ");
    }


    public double getMonto() {
        return monto;
    }

    public String getModalidad() {
        return modalidad;
    }

    public String getEstado() {
        return estado;
    }


    public LocalDateTime getFecha() {
        return fecha;
    }


    public int getNumeroCarrera() {
        return numeroCarrera;
    }


    public String getNombreCarrera() {
        return nombreCarrera;
    }


    public int getNumeroCaballo() {
        return numeroCaballo;
    }


    public String getNombreCaballo() {
        return nombreCaballo;
    }


    public double getDividendoFinal() {
        return dividendoFinal;
    }


    public double getMontoCobrado() {
        return montoCobrado;
    }


    public static List<ApuestaDto> fromList(List<Apuesta> apuestas){
        List<ApuestaDto> result = new ArrayList<>();

        for(Apuesta apuesta : apuestas){
            result.add(new ApuestaDto(apuesta));
        }

        return result;
    }
}
