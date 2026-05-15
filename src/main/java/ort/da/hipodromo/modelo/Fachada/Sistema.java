package ort.da.hipodromo.modelo;

public class Sistema {
    private static Sistema instancia;

    private SistemaUsuarios sistemaUsuarios;
    private SistemaApuestas sistemaApuestas;
    private SistemaCarreras sistemaCarreras;

    private Sistema() {
        this.sistemaUsuarios = new SistemaUsuarios();
        this.sistemaApuestas = new SistemaApuestas(0);
        this.sistemaCarreras = new SistemaCarreras();
    }

    public static Sistema getInstance(){
        if(instancia == null){
            instancia = new Sistema();
        }
        return instancia;
    }

    
}