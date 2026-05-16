package ort.da.hipodromo.modelo.Sistemas;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Caballo;
import ort.da.hipodromo.modelo.Carrera.Jornada;

public class SistemaCarreras {
    private List<Jornada> jornadas;
    private List<Caballo> caballos;
    
    public SistemaCarreras() {
        this.jornadas = new ArrayList<>();
        this.caballos = new ArrayList<>();
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public List<Caballo> getCaballos() {
        return caballos;
    }

    
}