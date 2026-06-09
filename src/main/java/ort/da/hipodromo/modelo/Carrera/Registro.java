package ort.da.hipodromo.modelo.Carrera;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.Apuesta;

public class Registro {
    private Carrera carrera;
    private int numeroCaballo;
    private Caballo caballo;
    private List<Apuesta> apuestas;
    private double dividendoFinal;

    public Registro(int numeroCaballo, Caballo caballo) {
        this.numeroCaballo = numeroCaballo;
        this.caballo = caballo;
        this.apuestas = new ArrayList<>();
        this.dividendoFinal = 0;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public int getNumeroCaballo() {
        return numeroCaballo;
    }
    
    public Caballo getCaballo() {
        return caballo;
    }

    public List<Apuesta> getApuestas() {
        return apuestas;
    }

    public double getDividendoFinal() {
        return dividendoFinal;
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

    public void guardarDividendoFinal(double dividendoFinal){
        this.dividendoFinal = dividendoFinal;
        
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
    public String toString() {
        return "Registro [numeroCaballo=" + numeroCaballo + ", caballo=" + caballo + ", apuestas=" + apuestas + "]";
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