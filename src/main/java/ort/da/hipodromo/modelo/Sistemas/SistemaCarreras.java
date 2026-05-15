package ort.da.hipodromo.modelo;

import java.util.ArrayList;
import java.util.List;

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