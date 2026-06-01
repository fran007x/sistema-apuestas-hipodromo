package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import ort.da.hipodromo.Dtos.CarreraFinalizadaDto;
import ort.da.hipodromo.Dtos.JornadaAdminDto;
import ort.da.hipodromo.Dtos.ProximaCarreraDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Carrera.Jornada;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Administrador;

@RestController
@RequestMapping("/administracion")
public class PresentadorTableroAdministrador {
    private Sistema sistema;

    public PresentadorTableroAdministrador(Sistema sistema){
        this.sistema = sistema;
    }

    @RequestMapping("/inicioTableroAdmin")
    public Commands inicioCasoUsoTableroAdmin(@SessionAttribute("administrador") Administrador admin) {
        Jornada jornadaActual = sistema.jornadaActual();

        return Commands.create(datosJornadaActual(jornadaActual),carrerasFinalizadas(jornadaActual),
                                proximasCarreras(jornadaActual));

    }





    public Command datosJornadaActual(Jornada jornada){
        return new Command("datosJornadaActual", new JornadaAdminDto(jornada, sistema.getComision()));
    }

    public Command carrerasFinalizadas(Jornada jornada){
        return new Command("carrerasFinalizadas", CarreraFinalizadaDto.fromList(jornada.carrerasFinalizadasOrdenadasDesc()));
    }

    public Command proximasCarreras(Jornada jornada){
        return new Command("proximasCarreras", ProximaCarreraDto.fromList(jornada.proximasCarreras()));
    }

}
