package ort.da.hipodromo.Dtos;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Carrera;

public class CarreraDto {

    private int numero;
    private String nombre;
    private String estado;

    public CarreraDto(Carrera carrera){

        this.numero = carrera.getNumero();

        this.nombre = carrera.getNombre();

        this.estado = carrera.getEstado().toString();
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public static List<CarreraDto> fromList(
            List<Carrera> carreras){

        List<CarreraDto> result = new ArrayList<>();

        for(Carrera carrera : carreras){
            result.add(new CarreraDto(carrera));
        }

        return result;
    }
}
