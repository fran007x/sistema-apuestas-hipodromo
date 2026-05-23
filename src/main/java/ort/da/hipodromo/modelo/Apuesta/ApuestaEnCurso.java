package ort.da.hipodromo.modelo.Apuesta;

import java.time.LocalDateTime;

import ort.da.hipodromo.modelo.Carrera.Carrera;
import ort.da.hipodromo.modelo.Carrera.Registro;
import ort.da.hipodromo.modelo.Usuario.Jugador;

public class ApuestaEnCurso {
    private Jugador jugador;
    private Carrera carrera;
    private Registro registro;
    private ModalidadApuesta modalidad;
    private double monto;

    

    public ApuestaEnCurso(Jugador jugador, Carrera carrera, Registro registro, ModalidadApuesta modalidad,
            double monto) {
        this.jugador = jugador;
        this.carrera = carrera;
        this.registro = registro;
        this.modalidad = modalidad;
        this.monto = monto;
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

    
    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    @Override
    public String toString() {
        return "ApuestaEnCurso [jugador=" + jugador + ", carrera=" + carrera + ", registro=" + registro + ", modalidad="
                + modalidad + ", monto=" + monto + "]";
    }

    public double costo(){
        return modalidad.calcularCosto(monto);
    }

    public double posiblePago(double dividendoActual){
        return modalidad.calcularPago(monto, dividendoActual, registro.totalApostado());
    }

    public boolean puedeConfirmarse(){
        return carrera.permiteApuestas() && jugador.tieneSaldoSuficiente(costo());
    }

    public Apuesta confirmarApuesta(){
        return new Apuesta(jugador, registro, modalidad, monto, LocalDateTime.now());
    }

}