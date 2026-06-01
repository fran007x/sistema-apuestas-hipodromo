package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;

import ort.da.hipodromo.Presentadores.Comandos.Commands;
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
    public Commands inicioCasoUsoConfirmarApuesta(@RequestParam int numeroCarrera, @RequestParam String nombreCarrera, @RequestParam int numeroCaballo,
        @RequestParam int nombreCaballo, @RequestParam double dividendoActual, @RequestParam String modalidad, @RequestParam double monto,
        @SessionAttribute("registroIngreso") RegistroIngreso registro)){


    }


}
