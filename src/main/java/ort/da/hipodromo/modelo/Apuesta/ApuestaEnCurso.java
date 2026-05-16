package ort.da.hipodromo.modelo.Apuesta;

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


    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "ApuestaEnCurso [jugador=" + jugador + ", carrera=" + carrera + ", registro=" + registro + ", modalidad="
                + modalidad + ", monto=" + monto + "]";
    }

}