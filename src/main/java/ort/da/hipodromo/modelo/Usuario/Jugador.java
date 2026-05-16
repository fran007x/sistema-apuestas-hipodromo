package ort.da.hipodromo.modelo.Usuario;

import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;
import ort.da.hipodromo.modelo.Exceptions.SaldoInsuficienteException;

public class Jugador extends Usuario {
    private double saldo;

    public Jugador(String nombreUsuario, String password, String nombreCompleto, double saldo) {
        super(nombreUsuario, password, nombreCompleto);
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }


    @Override
    public String toString() {
        return "Jugador [saldo=" + saldo + "]";
    }
    
    private void validarSaldo() throws DatosInvalidosException{
        if(saldo < 0){
            throw new DatosInvalidosException("El saldo no puede ser negativo.");
        }
    }

    @Override
    public void validar() throws DatosInvalidosException{
        super.validar();
        validarSaldo();
    }


    public boolean tieneSaldoSuficiente(double monto){
        return saldo >= monto;
    }

    public void descontarSaldo(double monto) throws SaldoInsuficienteException{
        if(!tieneSaldoSuficiente(monto)){
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
        saldo -= monto;
    }

    public void acreditarSaldo(double monto){
        saldo += monto;
    }

}