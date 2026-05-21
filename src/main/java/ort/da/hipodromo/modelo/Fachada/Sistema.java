package ort.da.hipodromo.modelo.Fachada;

import ort.da.hipodromo.modelo.Sistemas.SistemaApuestas;
import ort.da.hipodromo.modelo.Sistemas.SistemaCarreras;
import ort.da.hipodromo.modelo.Sistemas.SistemaUsuarios;

public class Sistema {
    private static Sistema instancia;

    private SistemaUsuarios sistemaUsuarios;
    private SistemaApuestas sistemaApuestas;
    private SistemaCarreras sistemaCarreras;

    private Sistema() {
        this.sistemaUsuarios = new SistemaUsuarios();
        this.sistemaApuestas = new SistemaApuestas();
        this.sistemaCarreras = new SistemaCarreras();
    }

    public static Sistema getInstance(){
        if(instancia == null){
            instancia = new Sistema();
        }
        return instancia;
    }

    
}