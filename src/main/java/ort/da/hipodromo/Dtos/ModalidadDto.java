package ort.da.hipodromo.Dtos;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.ModalidadApuesta;

public class ModalidadDto {
    private String nombre;

    public ModalidadDto(ModalidadApuesta ma) {
        this.nombre = ma.getNombre();
    }

    public String getNombre() {
        return nombre;
    }

    public static List<ModalidadDto> fromList(List<ModalidadApuesta> modalidades){
        List<ModalidadDto> result = new ArrayList<>();

        for(ModalidadApuesta ma : modalidades){
            result.add(new ModalidadDto(ma));
        }

        return result;
    }
}
