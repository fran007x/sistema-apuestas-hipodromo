package ort.da.hipodromo.Dtos;

import java.time.LocalDate;

import ort.da.hipodromo.modelo.Carrera.Jornada;

public class JornadaAdminDto {
    private LocalDate fecha;
    private double totalApostado;
    private double totalPagado;
    private double totalComisiones;
    private double balanceGeneral;
    private int cantidadCarreras;
    private int cantidadFinalizadas;
    private int cantidadPorCorrer;

    public JornadaAdminDto(Jornada jornada, double comision) {

        this.fecha = jornada.getFecha();
        this.totalApostado = jornada.totalApostado();
        this.totalPagado = jornada.totalPagado();
        this.totalComisiones = jornada.totalComisiones(comision);
        this.balanceGeneral = jornada.balanceGeneral();

        this.cantidadCarreras = jornada.cantidadCarreras();
        this.cantidadFinalizadas = jornada.cantidadCarrerasFinalizadas();
        this.cantidadPorCorrer = jornada.cantidadCarrerasPorCorrer();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTotalApostado() {
        return totalApostado;
    }

    public double getTotalPagado() {
        return totalPagado;
    }

    public double getTotalComisiones() {
        return totalComisiones;
    }

    public double getBalanceGeneral() {
        return balanceGeneral;
    }

    public int getCantidadCarreras() {
        return cantidadCarreras;
    }

    public int getCantidadFinalizadas() {
        return cantidadFinalizadas;
    }

    public int getCantidadPorCorrer() {
        return cantidadPorCorrer;
    }
}
