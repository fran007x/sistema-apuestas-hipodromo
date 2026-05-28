package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Administrador;

@RestController
@RequestMapping("/administracion")
public class PresentadorLoginAdministrador {
    private Sistema sistema;

    public PresentadorLoginAdministrador(Sistema sistema){
        this.sistema = sistema;
    }

    @PostMapping("/login")
    public Commands login(@RequestParam String nombreUsuario, @RequestParam String password, HttpSession session){
        Administrador admin = sistema.loginAdministrador(nombreUsuario, password);

        if(admin == null){
            return Commands.create(mensajeError("Acceso denegado"));
        }
        session.setAttribute("administrador", admin);
        return Commands.create(accesoPermitido());
    }

    @PostMapping("/logout")
    public Commands logout(HttpSession session){
        session.invalidate();
        return Commands.create(sesionFinalizada());
    }


    private Command sesionFinalizada() {
      return new Command("sesionFinalizada", "");
    }

    public Command accesoPermitido(){
        return new Command("accesoPermitido","");
    }
    public Command mensajeError(String texto){
        return new Command("mensajeError",texto);
    }
}
