package ort.da.hipodromo.modelo.Carrera;

public class Registro {
    private Caballo caballo;
    private Carrera carrera;

    public Registro(Caballo caballo, Carrera carrera) {
        this.caballo = caballo;
        this.carrera = carrera;
    }
    
    public Caballo getCaballo() {
        return caballo;
    }

    public void setCaballo(Caballo caballo) {
        this.caballo = caballo;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}