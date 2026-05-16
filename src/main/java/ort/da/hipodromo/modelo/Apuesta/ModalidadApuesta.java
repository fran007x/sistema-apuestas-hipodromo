package ort.da.hipodromo.modelo.Apuesta;

public abstract class ModalidadApuesta {
    private String nombre;

    public ModalidadApuesta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return  nombre;
    }

    public abstract double calcularCosto(double monto);

    public abstract double calcularPago(double monto, double dividendoFinal, double totalApostadoRegistro);
}