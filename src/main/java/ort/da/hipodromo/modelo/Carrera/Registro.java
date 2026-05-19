package ort.da.hipodromo.modelo.Carrera;

import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.Apuesta;

public class Registro {
    private int numeroCaballo;
    private Carrera carrera;
    private List<Apuesta> apuestas;

    public Registro(int numeroCaballo, Carrera carrera, List<Apuesta> apuestas) {
        this.numeroCaballo = numeroCaballo;
        this.carrera = carrera;
        this.apuestas = apuestas;
    }
    
    public List<Apuesta> getApuestas() {
        return apuestas;
    }

    public int getNumeroCaballo() {
        return numeroCaballo;
    }

    public void setNumeroCaballo(int numeroCaballo) {
        this.numeroCaballo = numeroCaballo;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }


    public double totalApostado(){
        double total = 0;
        
        for(Apuesta apuesta : apuestas){
            total += apuesta.getMonto();
        }
        return total;
    }

    public int cantidadApuestas() {
        return apuestas.size();
    }

    public double calcularDividendo(double totalApostadoCarrera, double comision){
        double totalApostadoRegistro = totalApostado();

        if(totalApostadoRegistro == 0){
            return 0;
        }

        double pozoARepartir = totalApostadoCarrera *(1 - comision);

        return pozoARepartir / totalApostadoRegistro;
    }

    public boolean dividendoValido(double totalApostadoCarrera, double comision) {
    return cantidadApuestas() > 0 && calcularDividendo(totalApostadoCarrera, comision) > 1;
    }

    public double totalPagado(){
        double total = 0;

        for(Apuesta apuesta : apuestas){
            total+= apuesta.getMontoCobrado();
        }
        return total;
    }

    public void agregarApuesta(Apuesta apuesta){
        apuestas.add(apuesta);
    }

    public void guardarDividendoFinalEnApuestas(double dividendoFinal){
        for(Apuesta apuesta : apuestas){
            apuesta.guardarDividendoFinal(dividendoFinal);
        }
    }

    public void liquidarApuestas(Registro ganador){
        for(Apuesta apuesta : apuestas){
            apuesta.liquidar(ganador);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Registro otro = (Registro) obj;

        return this.numeroCaballo == otro.numeroCaballo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numeroCaballo);
    }
}