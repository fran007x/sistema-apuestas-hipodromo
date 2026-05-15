package ort.da.hipodromo.modelo;

public class Caballo {
    private String nombre;

    public Caballo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Caballo [nombre=" + nombre + "]";
    }

}