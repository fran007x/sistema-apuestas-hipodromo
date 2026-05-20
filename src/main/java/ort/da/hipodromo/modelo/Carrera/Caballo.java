package ort.da.hipodromo.modelo.Carrera;

import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;

public class Caballo {
    private String nombre;

    public Caballo(String nombre){
        this.nombre = nombre;
        validarNombre();
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Caballo [nombre=" + nombre + "]";
    }

    private void validarNombre(){
        if(nombre == null || nombre.isBlank()){
            throw new DatosInvalidosException("El nombre del caballo es obligatorio.");
        }
    }

}