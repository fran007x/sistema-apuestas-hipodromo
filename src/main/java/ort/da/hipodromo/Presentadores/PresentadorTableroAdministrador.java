package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import ort.da.hipodromo.Dtos.CarreraFinalizadaDto;
import ort.da.hipodromo.Dtos.JornadaAdminDto;
import ort.da.hipodromo.Dtos.ProximaCarreraDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Carrera.Jornada;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Administrador;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/administracion")
public class PresentadorTableroAdministrador {
    private Sistema sistema;

    public PresentadorTableroAdministrador(Sistema sistema){
        this.sistema = sistema;
    }

    @RequestMapping("/inicioTableroAdmin")
    public Commands inicioCasoUsoTableroAdmin(@SessionAttribute("administrador") Administrador admin, HttpSession session){ 
        Jornada jornada = sistema.jornadaActual();

        session.setAttribute("fechaJornadaSeleccionada", jornada.getFecha());

        return comandosTablero(jornada);
    }

    @PostMapping("/jornadaAnterior")
    public Commands jornadaAnterior(@SessionAttribute("fechaJornadaSeleccionada") LocalDate fechaSeleccionada, HttpSession session){
        Jornada jornada = sistema.jornadaAnterior(fechaSeleccionada);
        
        if(jornada == null){
            return Commands.create(mensajeError("No hay jornadas anteriores a la seleccionada"));
        }

        session.setAttribute("fechaJornadaSeleccionada", jornada.getFecha());
        return comandosTablero(jornada);
    }

    @PostMapping("/jornadaSiguiente")
    public Commands jornadaSiguiente(@SessionAttribute("fechaJornadaSeleccionada") LocalDate fechaSeleccionada, HttpSession session){
        Jornada jornada = sistema.jornadaSiguiente(fechaSeleccionada);
        
        if(jornada == null){
            return Commands.create(mensajeError("No hay jornadas siguientes a la seleccionada"));
        }

        session.setAttribute("fechaJornadaSeleccionada", jornada.getFecha());
        return comandosTablero(jornada);
    }
    

    private Commands comandosTablero(Jornada jornada){
        return Commands.create(datosJornadaActual(jornada), carrerasFinalizadas(jornada), proximasCarreras(jornada));
    }

    public Command datosJornadaActual(Jornada jornada){
        return new Command("datosJornadaActual", new JornadaAdminDto(jornada, sistema.getComision()));
    }

    public Command carrerasFinalizadas(Jornada jornada){
        return new Command("carrerasFinalizadas", CarreraFinalizadaDto.fromList(jornada.carrerasFinalizadasOrdenadasDesc()));
    }

    public Command proximasCarreras(Jornada jornada){
        return new Command("proximasCarreras", ProximaCarreraDto.fromList(jornada.getFecha(), jornada.proximasCarreras()));
    }

    public Command mensajeError(String texto){
        return new Command("mensajeError", texto);
    }

}
