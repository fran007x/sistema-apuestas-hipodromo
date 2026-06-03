package ort.da.hipodromo.Dtos;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Registro;

public class RegistroGestionDto {
    private int numeroCaballo;
    private String nombreCaballo;
    private double dividendoActual;
    private double totalApostado;
    private int cantidadApuestas;

    public RegistroGestionDto(Registro registro, double totalApostadoCarrera, double comision) {
        this.numeroCaballo = registro.getNumeroCaballo();
        this.nombreCaballo = registro.getCaballo().getNombre();
        this.dividendoActual = registro.calcularDividendo(totalApostadoCarrera, comision);
        this.totalApostado = registro.totalApostado();
        this.cantidadApuestas = registro.cantidadApuestas();
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

    public double getTotalApostado() {
        return totalApostado;
    }

    public int getCantidadApuestas() {
        return cantidadApuestas;
    }

    public static List<RegistroGestionDto> fromList(List<Registro> registros,double totalApostadoCarrera,double comision) {
        List<RegistroGestionDto> result = new ArrayList<>();

        for (Registro registro : registros) {
            result.add(new RegistroGestionDto(registro, totalApostadoCarrera, comision));
        }

        return result;
    }
}
