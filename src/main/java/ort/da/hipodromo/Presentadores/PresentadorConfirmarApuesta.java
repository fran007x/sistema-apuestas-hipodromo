package ort.da.hipodromo.Presentadores;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

import ort.da.hipodromo.Dtos.ApuestaEnCursoDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Apuesta.ApuestaEnCurso;
import ort.da.hipodromo.modelo.Carrera.Jornada;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.RegistroIngreso;

@RestController
@RequestMapping("/hipodromo")
@SessionScope
public class PresentadorConfirmarApuesta {
    private Sistema sistema;


    public PresentadorConfirmarApuesta(Sistema sistema){
        this.sistema = sistema;
    }

    @PostMapping("/inicioCasoUsoConfirmarApuesta")
    public Commands inicioCasoUsoConfirmarApuesta(@RequestParam LocalDate fechaJornada,@RequestParam int numeroCarrera,@RequestParam int numeroCaballo,
        @RequestParam String modalidad, @RequestParam double monto, @SessionAttribute("registroIngreso") RegistroIngreso registro){
            String nombreUsuario = registro.getUsuarioLogueado().getNombreUsuario();

            ApuestaEnCurso apuesta = sistema.crearApuestaEnCurso(nombreUsuario, fechaJornada, numeroCarrera, numeroCaballo, modalidad, monto);
            

            return Commands.create(datosApuestaEnCurso(apuesta));

    }

     public Command datosApuestaEnCurso(ApuestaEnCurso apuesta){
        return new Command("datosJornadaActual", new ApuestaEnCursoDto(apuesta, sistema.getComision()));
    }



}
