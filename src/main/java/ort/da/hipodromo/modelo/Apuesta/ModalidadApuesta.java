package ort.da.hipodromo.modelo;

public abstract class ModalidadApuesta {
    private String nombre;

    public ModalidadApuesta(String nombre) {
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
        return "ModalidadApuesta [nombre=" + nombre + "]";
    }

}