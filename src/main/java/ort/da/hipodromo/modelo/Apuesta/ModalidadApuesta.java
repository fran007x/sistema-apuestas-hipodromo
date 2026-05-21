package ort.da.hipodromo.modelo.Apuesta;

import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;

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

    public void validarNombre(){
        if(nombre == null || nombre.isBlank()){
            throw new DatosInvalidosException("El nombre no puede ser nulo o vacio");
        }
    }

    public abstract double calcularCosto(double monto);

    public abstract double calcularPago(double monto, double dividendoFinal, double totalApostadoRegistro);
}