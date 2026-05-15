package ort.da.hipodromo.modelo;

import java.util.List;
import java.util.Date;

public class Jornada {
    private List<Carrera> carreras;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Jornada(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    public void setCarreras(List<Carrera> carreras) {
        this.carreras = carreras;
    }

    @Override
    public String toString() {
        return "Jornada [carreras=" + carreras + ", fecha=" + fecha + "]";
    }

    
}