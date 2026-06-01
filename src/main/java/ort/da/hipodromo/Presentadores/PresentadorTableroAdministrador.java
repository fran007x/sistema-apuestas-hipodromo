package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import ort.da.hipodromo.Dtos.JornadaDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Administrador;
import ort.da.hipodromo.modelo.Usuario.Jugador;
import ort.da.hipodromo.modelo.Usuario.RegistroIngreso;

@RestController
@RequestMapping("/administracion")
public class PresentadorTableroAdministrador {
    private Sistema sistema;

    public PresentadorTableroAdministrador(Sistema sistema){
        this.sistema = sistema;
    }

    @RequestMapping("/inicioTableroAdmin")
    public Commands inicioCasoUsoTableroAdmin(@SessionAttribute("administrador") Administrador admin) {
        
        return Commands.create();

    }





    public Command fechaJornadaActual(){
        return new Command("fechaJornadaActual", JornadaDto.fromList(sistema.jornadaActual()));
    }
    public Command totalApostadoJornadaActual(){
        return new Command("totalApostadoJornadaActual", JornadaDto.fromList(sistema);
    }

}
