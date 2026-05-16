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