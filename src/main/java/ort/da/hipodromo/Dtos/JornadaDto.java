package ort.da.hipodromo.Dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Carrera.Jornada;

public class JornadaDto {
    private LocalDate fecha;

    public JornadaDto(Jornada jornada) {
        this.fecha = jornada.getFecha();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public static List<JornadaDto> fromList(List<Jornada> jornadas){
        List<JornadaDto> result = new ArrayList<>();

        for(Jornada jornada : jornadas){
            result.add(new JornadaDto(jornada));
        }

        return result;
    }
}
