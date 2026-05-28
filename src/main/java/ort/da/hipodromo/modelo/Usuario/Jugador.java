package ort.da.hipodromo.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.Apuesta;
import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;
import ort.da.hipodromo.modelo.Exceptions.SaldoInsuficienteException;

public class Jugador extends Usuario {
    private double saldo;
    private List<Apuesta> apuestas;

    public Jugador(String nombreUsuario, String password, String nombreCompleto, double saldo) {
        super(nombreUsuario, password, nombreCompleto);
        this.saldo = saldo;
        this.apuestas = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

     public List<Apuesta> getApuestas() {
        return apuestas;
    }


    @Override
    public String toString() {
        return "Jugador [saldo=" + saldo + "]";
    }
    
    private void validarSaldo(){
        if(saldo < 0){
            throw new DatosInvalidosException("El saldo no puede ser negativo.");
        }
    }

    @Override
    public void validar(){
        super.validar();
        validarSaldo();
    }


    public boolean tieneSaldoSuficiente(double monto){
        return saldo >= monto;
    }

    public void descontarSaldo(double monto){
        if(!tieneSaldoSuficiente(monto)){
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
        saldo -= monto;
    }

    public void acreditarSaldo(double monto){
        saldo += monto;
    }

    public void agregarApuesta(Apuesta apuesta){
        apuestas.add(apuesta);
    }

    public double totalApostado(){
        double total = 0;

        for(Apuesta apuesta : apuestas){
            total += apuesta.getCosto();
        }
        return total;
    }

    public double totalGanado(){
        double total = 0;

        for(Apuesta apuesta : apuestas){
            total += apuesta.getMontoCobrado();
        }
        return total;
    }

    public List<Apuesta> apuestasOrdenadasDesc(){
        List<Apuesta> ordenadasDesc = new ArrayList<>(apuestas);

        ordenadasDesc.sort((a1,a2) -> a2.getFecha().compareTo(a1.getFecha()));
        return ordenadasDesc;
    }
   
}