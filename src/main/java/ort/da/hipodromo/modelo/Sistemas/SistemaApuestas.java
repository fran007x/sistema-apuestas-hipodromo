package ort.da.hipodromo.modelo.Sistemas;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.ApuestaEnCurso;
import ort.da.hipodromo.modelo.Apuesta.ModalidadApuesta;

public class SistemaApuestas {
    private List<ApuestaEnCurso> apuestasEnCurso;
    private List<ModalidadApuesta> modalidades;
    private double comision;
    
    public SistemaApuestas() {
        this.apuestasEnCurso = new ArrayList<>();
        this.modalidades = new ArrayList<>();
        this.comision = 0.10;
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