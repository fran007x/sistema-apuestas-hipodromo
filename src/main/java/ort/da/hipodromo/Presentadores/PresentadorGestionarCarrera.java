package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import ort.da.hipodromo.Dtos.CarreraGestionDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Carrera.Carrera;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Administrador;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/administracion")
public class PresentadorGestionarCarrera {
    private Sistema sistema;

    public PresentadorGestionarCarrera(Sistema sistema) {
        this.sistema = sistema;
    }

    @PostMapping("/inicioCasoUsoGestionarCarrera")
    public Commands inicioCasoUsoGestionarCarrera( @RequestParam LocalDate fechaJornada,@RequestParam int numeroCarrera, @SessionAttribute("administrador") Administrador administrador, HttpSession session) {
        Carrera carrera = sistema.buscarCarrera(fechaJornada, numeroCarrera);

        if(carrera == null){
            return Commands.create(mensajeError("No hay carrera seleccionada"));
        }

        session.setAttribute("fechaJornadaGestion", fechaJornada);
        session.setAttribute("numeroCarreraGestion", numeroCarrera);
        
        return Commands.create(datosCarrera(carrera, fechaJornada));
    }

      @PostMapping("/abrirCarrera")
    public Commands abrirCarrera(@SessionAttribute("fechaJornadaGestion") LocalDate fechaJornada, @SessionAttribute("numeroCarreraGestion") Integer numeroCarrera) {
        sistema.abrirCarrera(fechaJornada, numeroCarrera);

        Carrera carrera = sistema.buscarCarrera(fechaJornada, numeroCarrera);

        return Commands.create(carreraAbierta(), datosCarrera(carrera, fechaJornada)
        );
    }

    @PostMapping("/cerrarCarrera")
    public Commands cerrarCarrera(@SessionAttribute("fechaJornadaGestion") LocalDate fechaJornada,@SessionAttribute("numeroCarreraGestion") Integer numeroCarrera) {
        sistema.cerrarCarrera(fechaJornada, numeroCarrera);

        Carrera carrera = sistema.buscarCarrera(fechaJornada, numeroCarrera);

        return Commands.create(carreraCerrada(),datosCarrera(carrera, fechaJornada));
    }

    @PostMapping("/finalizarCarrera")
    public Commands finalizarCarrera(@RequestParam Integer numeroCaballoGanador,@SessionAttribute("fechaJornadaGestion") LocalDate fechaJornada,@SessionAttribute("numeroCarreraGestion") Integer numeroCarrera) {
        if (numeroCaballoGanador == null) {
            return Commands.create(mensajeError("Debe indicar el caballo ganador de la carrera"));
        }

        sistema.finalizarCarrera(fechaJornada, numeroCarrera, numeroCaballoGanador);

        Carrera carrera = sistema.buscarCarrera(fechaJornada, numeroCarrera);

        return Commands.create(carreraFinalizada(),datosCarrera(carrera, fechaJornada),actualizarTableroAdministrador()
        );
    }

    @PostMapping("/finalizarGestionCarrera")
    public Commands finalizarGestionCarrera(HttpSession session) {
        session.removeAttribute("fechaJornadaGestion");
        session.removeAttribute("numeroCarreraGestion");

        return Commands.create(actualizarTableroAdministrador());
    }

    public Command datosCarrera(Carrera carrera, LocalDate fechaJornada) {
        return new Command("datosCarreraGestion", new CarreraGestionDto(fechaJornada,carrera,sistema.getComision()));
    }

    public Command carreraAbierta() {
        return new Command("carreraAbierta", "");
    }

    public Command carreraCerrada() {
        return new Command("carreraCerrada", "");
    }

    public Command carreraFinalizada() {
        return new Command("carreraFinalizada", "");
    }

    public Command actualizarTableroAdministrador() {
        return new Command("actualizarTableroAdministrador", "");
    }

    public Command mensajeError(String texto) {
        return new Command("mensajeError", texto);
    }
}
