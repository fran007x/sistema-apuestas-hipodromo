package ort.da.hipodromo.Dtos;

import ort.da.hipodromo.modelo.Carrera.Registro;

public class RegistroDto {
    private int numeroCaballo;
    private String nombreCaballo;

    public RegistroDto(Registro registro){

        this.numeroCaballo =registro.getNumeroCaballo();

        this.nombreCaballo =registro.getCaballo().getNombre();
    }

    public int getNumeroCaballo() {
        return numeroCaballo;
    }

    public String getNombreCaballo() {
        return nombreCaballo;
    }
}
