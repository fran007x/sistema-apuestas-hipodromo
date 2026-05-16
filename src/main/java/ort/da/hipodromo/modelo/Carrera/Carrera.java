package ort.da.hipodromo.modelo.Carrera;

import java.time.LocalDateTime;
import java.util.List;

public class Carrera {
    private int numero;
    private EstadoCarrera estado;
    private List<Registro> registros;
    private Registro caballoGanador;
    private LocalDateTime horaFinalizacion;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public EstadoCarrera getEstado() {
        return estado;
    }

    public void setEstado(EstadoCarrera estado) {
        this.estado = estado;
    }

    public LocalDateTime getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(LocalDateTime horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

    public Carrera(List<Registro> registros) {
        this.registros = registros;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public void setRegistros(List<Registro> registros) {
        this.registros = registros;
    }

    public Registro getCaballoGanador() {
        return caballoGanador;
    }

    public void setCaballoGanador(Registro caballoGanador) {
        this.caballoGanador = caballoGanador;
    }

    @Override
    public String toString() {
        return "Carrera [numero=" + numero + ", estado=" + estado + ", registros=" + registros + ", caballoGanador="
                + caballoGanador + ", horaFinalizacion=" + horaFinalizacion + "]";
    }

    
}