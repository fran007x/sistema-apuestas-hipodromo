package ort.da.hipodromo.Dtos;

import ort.da.hipodromo.modelo.Apuesta.ApuestaEnCurso;

public class ApuestaEnCursoDto {
    private int numeroCarrera;
    private String nombreCarrera;
    private int numeroCaballo;
    private String nombreCaballo;
    private double dividendoActual;
    private double montoApostado;
    private String modalidad;
    private double montoAPagar;
    private double montoACobrar;

    public ApuestaEnCursoDto(ApuestaEnCurso apuesta, double comision) {
        this.numeroCarrera = apuesta.getCarrera().getNumero();
        this.nombreCarrera = apuesta.getCarrera().getNombre();
        this.numeroCaballo = apuesta.getRegistro().getNumeroCaballo();
        this.nombreCaballo = apuesta.getRegistro().getCaballo().getNombre();
        this.dividendoActual = apuesta.dividendoActual(comision);
        this.montoApostado = apuesta.getMonto();
        this.modalidad = apuesta.getModalidad().getNombre();
        this.montoAPagar = apuesta.costo();
        this.montoACobrar = apuesta.posiblePago(comision);
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

    public double getDividendoActual() {
        return dividendoActual;
    }

    public double getMontoApostado() {
        return montoApostado;
    }

    public String getModalidad() {
        return modalidad;
    }

    public double getMontoAPagar() {
        return montoAPagar;
    }

    public double getMontoACobrar() {
        return montoACobrar;
    }

    
    
}
