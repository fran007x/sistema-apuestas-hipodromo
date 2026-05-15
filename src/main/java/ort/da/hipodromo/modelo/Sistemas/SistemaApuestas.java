package ort.da.hipodromo.modelo;

import java.util.ArrayList;
import java.util.List;

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