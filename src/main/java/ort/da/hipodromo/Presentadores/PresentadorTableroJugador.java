package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.RestController;

import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.modelo.Usuario.Jugador;

@RestController
@RequestMapping("/hipodromo")
public class PresentadorTableroJugador {
    

    public Command nombreJugador(Jugador jugador){
        return new Command("nombreCompleto", jugador.getNombreCompleto());
    }

    
}
