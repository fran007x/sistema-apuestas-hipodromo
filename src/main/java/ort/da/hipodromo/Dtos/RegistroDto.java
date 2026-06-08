package ort.da.hipodromo.Dtos;

import java.util.ArrayList;
import java.util.List;

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

    public static List<RegistroDto> fromList(List<Registro> registros){
    List<RegistroDto> result = new ArrayList<>();

    for(Registro registro : registros){
        result.add(new RegistroDto(registro));
    }

    return result;
}
}
