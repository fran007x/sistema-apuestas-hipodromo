package ort.da.hipodromo.modelo;

public class Jugador extends Usuario {
    private double saldo;

    public Jugador(String nombreUsuario, String password, String nombreCompleto, double saldo) {
        super(nombreUsuario, password, nombreCompleto);
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Jugador [saldo=" + saldo + "]";
    }
    
    
}