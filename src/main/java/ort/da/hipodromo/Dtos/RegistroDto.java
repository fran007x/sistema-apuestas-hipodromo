package ort.da.hipodromo.Dtos;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Registro;

public class RegistroDto {
    private int numeroCaballo;
    private String nombreCaballo;
    private double dividendoActual;

    public RegistroDto(Registro registro,double totalApostadoCarrera, double comision){

        this.numeroCaballo =registro.getNumeroCaballo();
        this.nombreCaballo = registro.getCaballo().getNombre();
        this.dividendoActual = registro.calcularDividendo(totalApostadoCarrera, comision);
    }

    public int getNumeroCaballo() {
        return numeroCaballo;
    }

    public String getNombreCaballo() {
        return nombreCaballo;
    }

    public double getDividendoActual() {
        return dividendoActual;
    }

    public static List<RegistroDto> fromList(List<Registro> registros, double totalApostadoCarrera, double comision){
    List<RegistroDto> result = new ArrayList<>();

    for(Registro registro : registros){
        result.add(new RegistroDto(registro, totalApostadoCarrera, comision));
    }

    return result;
}
}
