package ort.da.hipodromo.Dtos;

import ort.da.hipodromo.modelo.Apuesta.Apuesta;

public class ApuestaDto {
    private double monto;
    private String modalidad;
    private String estado;

    public ApuestaDto(Apuesta apuesta){

        this.monto = apuesta.getMonto();

        this.modalidad = apuesta.getModalidad()
                        .getNombre();

        this.estado = apuesta.getEstado()
                        .toString();
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
}
