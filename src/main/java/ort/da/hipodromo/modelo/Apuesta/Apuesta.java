package ort.da.hipodromo.modelo.Apuesta;

import java.util.Date;

import ort.da.hipodromo.modelo.Carrera.Registro;
import ort.da.hipodromo.modelo.Usuario.Jugador;

public class Apuesta {
    private Jugador jugador;
    private Registro registro;
    private ModalidadApuesta modalidad;
    private EstadoApuesta estado;
    private double monto;
    private double costo;
    private double montoCobrado;
    private double dividendoFinal;
    private Date fecha;


    public Apuesta(Jugador jugador, Registro registro, ModalidadApuesta modalidad, double monto,
            double costo, double montoCobrado, double dividendoFinal, Date fecha) {
        this.jugador = jugador;
        this.registro = registro;
        this.modalidad = modalidad;
        this.estado = EstadoApuesta.Por_Iniciar;
        this.monto = monto;
        this.costo = costo;
        this.montoCobrado = montoCobrado;
        this.dividendoFinal = dividendoFinal;
        this.fecha = fecha;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Registro getRegistro() {
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public ModalidadApuesta getModalidad() {
        return modalidad;
    }

    public void setModalidad(ModalidadApuesta modalidad) {
        this.modalidad = modalidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getMontoCobrado() {
        return montoCobrado;
    }

    public void setMontoCobrado(double montoCobrado) {
        this.montoCobrado = montoCobrado;
    }

    public double getDividendoFinal() {
        return dividendoFinal;
    }

    public void setDividendoFinal(double dividendoFinal) {
        this.dividendoFinal = dividendoFinal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoApuesta getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Apuesta [jugador=" + jugador + ", registro=" + registro + ", modalidad=" + modalidad + ", estado="
                + estado + ", monto=" + monto + ", costo=" + costo + ", montoCobrado=" + montoCobrado
                + ", dividendoFinal=" + dividendoFinal + ", fecha=" + fecha + "]";
    }

    public boolean esGanadora(Registro ganador){
        return registro.equals(ganador);
    }

    public void liquidar(Registro ganador, double dividendoFinal) {
        this.dividendoFinal = dividendoFinal;

        if (esGanadora(ganador)) {
            double totalApostadoRegistro = registro.totalApostado();

            this.montoCobrado = modalidad.calcularPago(monto,dividendoFinal,totalApostadoRegistro);

            jugador.acreditarSaldo(montoCobrado);
        }

        estado = EstadoApuesta.Finalizada;
    }
}