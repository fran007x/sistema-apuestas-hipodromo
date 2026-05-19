package ort.da.hipodromo.modelo.Carrera;

import java.util.List;

import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;

import java.util.ArrayList;
import java.util.Date;

public class Jornada {
    private List<Carrera> carreras;
    private Date fecha;

    public Jornada(List<Carrera> carreras, Date fecha) {
        this.carreras = carreras;
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) throws DatosInvalidosException {
        this.fecha = fecha;
        validarFecha();
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }

    @Override
    public String toString() {
        return "Jornada [carreras=" + carreras + ", fecha=" + fecha + "]";
    }

    public void agregarCarrera(Carrera carrera){
        carrera.setNumero(carreras.size() + 1);
        carreras.add(carrera);
    }

    public Carrera buscar(int numero){
        for(Carrera carrera : carreras){
            if(carrera.getNumero() == numero){
                return carrera;
            }
        }
        return null;
    }

    private void validarFecha() throws DatosInvalidosException{
        if (fecha == null) {
            throw new DatosInvalidosException("La fecha de la jornada es obligatoria.");
        }
    }

    public boolean esDeFecha(Date fecha) {
        return fecha != null && this.fecha.equals(fecha);
    }

    public int cantidadCarreras(){
        return this.carreras.size();
    }

    public double totalApostado(){
        double total = 0;

        for(Carrera carrera : carreras){
            total += carrera.totalApostado();
        }
        return total;
    }

    public double totalPagado(){
        double total = 0;

        for(Carrera carrera : carreras){
            total += carrera.totalPagado();
        }
        return total;
    }

    public double totalComisiones(double comision){
        double total = 0;

        for(Carrera carrera : carreras){
            total += carrera.totalComision(comision);
        }
        return total;
    }

    public double balanceGeneral(){
        return this.totalApostado() - this.totalPagado();
    }

    public int cantidadCarrerasFinalizadas(){
        int contador = 0;

        for(Carrera carrera : carreras){
            if(carrera.estaFinalizada()){
                contador++;
            }
        }
        return contador;
    }

    public int cantidadCarrerasPorCorrer(){
        int contador = 0;

        for(Carrera carrera : carreras){
            if(!carrera.estaFinalizada()){
                contador++;
            }
        }
        return contador;
    }

    public List<Carrera> carrerasFinalizadasOrdenadasDesc(){
        List<Carrera> finalizadas = new ArrayList<>();

        for(Carrera carrera : carreras){
            if(carrera.estaFinalizada()){
                finalizadas.add(carrera);
            }
        }
        finalizadas.sort((c1, c2) -> c2.getNumero() - c1.getNumero());

        return finalizadas;
    }

    public List<Carrera> proximasCarreras(){
        List<Carrera> proximas = new ArrayList<>();
        
        for(Carrera carrera : carreras){
            if(!carrera.estaFinalizada()){
                proximas.add(carrera);
            }
        }
        return proximas;
    }

}