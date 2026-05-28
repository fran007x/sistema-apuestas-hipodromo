package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.RegistroIngreso;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/hipodromo")
public class PresentadorLoginJugador {
    private Sistema sistema;

    public PresentadorLoginJugador(Sistema sistema){
        this.sistema = sistema;
    }

    @PostMapping("/inicioLogin")
    public Commands inicioLogin(HttpSession session){
        RegistroIngreso registroActual = (RegistroIngreso)session.getAttribute("registroIngreso");

        if(registroActual != null){
            return Commands.create(accesoPermitido());
        }
        return null;
    }

    @PostMapping("/login")
    public Commands loginJugador(@RequestParam String nombreUsuario, @RequestParam String password, HttpSession session){
        RegistroIngreso registro = sistema.loginJugador(nombreUsuario, password);

        if(registro == null){
            return Commands.create(mensajeError("Acceso denegado"));
        }

        session.setAttribute("registroIngreso", registro);
        return Commands.create(accesoPermitido());
    }

    @PostMapping("/logout")
    public Commands logout(HttpSession session){
        RegistroIngreso registro = (RegistroIngreso)session.getAttribute("registroIngreso");
        session.invalidate();

        if(registro != null){
            sistema.logoutJugador(registro);
        }
        return Commands.create(sesionFinalizada());
    }

    

    private Command sesionFinalizada(){
        return new Command("sesionFinalizada", "");
    }

    public Command accesoPermitido(){
        return new Command("accesoPermitido","");
    }

    public Command mensajeError(String texto){
        return new Command("mensajeError", texto);
    }
}
