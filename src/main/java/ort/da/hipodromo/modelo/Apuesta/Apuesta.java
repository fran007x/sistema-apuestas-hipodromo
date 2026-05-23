package ort.da.hipodromo.modelo.Apuesta;

import java.time.LocalDateTime;

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
    private LocalDateTime fecha;


    public Apuesta(Jugador jugador, Registro registro, ModalidadApuesta modalidad, double monto,
            LocalDateTime fecha) {
        this.jugador = jugador;
        this.registro = registro;
        this.modalidad = modalidad;
        this.estado = EstadoApuesta.Por_Iniciar;
        this.monto = monto;
        this.costo = modalidad.calcularCosto(monto);
        this.montoCobrado = 0;
        this.dividendoFinal = 0;
        this.fecha = fecha;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Registro getRegistro() {
        return registro;
    }

    public ModalidadApuesta getModalidad() {
        return modalidad;
    }

    public double getMonto() {
        return monto;
    }


    public double getCosto() {
        return costo;
    }

    public double getMontoCobrado() {
        return montoCobrado;
    }

    public double getDividendoFinal() {
        return dividendoFinal;
    }

    public LocalDateTime getFecha() {
        return fecha;
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

    public void liquidar(Registro ganador) {
        if (esGanadora(ganador)) {
            double totalApostadoRegistro = registro.totalApostado();

            this.montoCobrado = modalidad.calcularPago(monto,dividendoFinal,totalApostadoRegistro);

            jugador.acreditarSaldo(montoCobrado);
        }

        estado = EstadoApuesta.Finalizada;
    }

    public void guardarDividendoFinal(double dividendoFinal){
        this.dividendoFinal = dividendoFinal;
    }
}