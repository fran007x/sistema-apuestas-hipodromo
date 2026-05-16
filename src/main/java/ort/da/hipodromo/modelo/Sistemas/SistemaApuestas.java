package ort.da.hipodromo.modelo.Sistemas;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.ApuestaEnCurso;
import ort.da.hipodromo.modelo.Apuesta.ModalidadApuesta;

public class SistemaApuestas {
    private List<ApuestaEnCurso> apuestasEnCurso;
    private List<ModalidadApuesta> modalidades;
    private double comision = 0.10;
    
    public SistemaApuestas(double comision) {
        this.apuestasEnCurso = new ArrayList<>();
        this.modalidades = new ArrayList<>();
        this.comision = comision;
    }

    public List<ApuestaEnCurso> getApuestasEnCurso() {
        return apuestasEnCurso;
    }

    public List<ModalidadApuesta> getModalidades() {
        return modalidades;
    }

    public double getComision() {
        return comision;
    }

    
}