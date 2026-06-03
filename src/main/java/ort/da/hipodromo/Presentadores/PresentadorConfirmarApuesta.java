package ort.da.hipodromo.Presentadores;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;
import ort.da.hipodromo.Dtos.ApuestaEnCursoDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Apuesta.ApuestaEnCurso;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.RegistroIngreso;

@RestController
@RequestMapping("/hipodromo")
public class PresentadorConfirmarApuesta {
    private Sistema sistema;

    public PresentadorConfirmarApuesta(Sistema sistema){
        this.sistema = sistema;
    }

    @PostMapping("/inicioCasoUsoConfirmarApuesta")
    public Commands inicioCasoUsoConfirmarApuesta(@RequestParam LocalDate fechaJornada,@RequestParam int numeroCarrera,@RequestParam int numeroCaballo,
        @RequestParam String modalidad, @RequestParam double monto, @SessionAttribute("registroIngreso") RegistroIngreso registro,HttpSession session){
            String nombreUsuario = registro.getJugadorLogueado().getNombreUsuario();

            ApuestaEnCurso apuesta = sistema.crearApuestaEnCurso(nombreUsuario, fechaJornada, numeroCarrera, numeroCaballo, modalidad, monto);
            session.setAttribute("apuestaEnCurso", apuesta);
            return Commands.create(datosApuestaEnCurso(apuesta));

    }

    @PostMapping("/confirmarApuesta")
    public Commands confirmarApuesta(@RequestParam String password, @SessionAttribute("apuestaEnCurso") ApuestaEnCurso apuestaEnCurso,HttpSession session) {
        sistema.confirmarApuesta(apuestaEnCurso, password);
        session.removeAttribute("apuestaEnCurso");
        return Commands.create(apuestaConfirmada());
    }
    
    @PostMapping("/cancelarApuesta")
    public Commands cancelarApuesta(@SessionAttribute("apuestaEnCurso") ApuestaEnCurso apuestaEnCurso,HttpSession session) {
        sistema.cancelarApuesta(apuestaEnCurso);
        session.removeAttribute("apuestaEnCurso");
        return Commands.create(apuestaCancelada());
    }
    

     public Command datosApuestaEnCurso(ApuestaEnCurso apuesta){
        return new Command("datosApuestaEnCurso", new ApuestaEnCursoDto(apuesta, sistema.getComision()));
    }

    public Command apuestaConfirmada(){
        return new Command("apuestaConfirmada","");
    }

    public Command apuestaCancelada(){
        return new Command("apuestaCancelada", "");
    }



}
