package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import ort.da.hipodromo.Dtos.ApuestaDto;
import ort.da.hipodromo.Dtos.ModalidadDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Jugador;
import ort.da.hipodromo.modelo.Usuario.RegistroIngreso;


@RestController
@RequestMapping("/hipodromo")
public class PresentadorTableroJugador {
    private Sistema sistema;

    public PresentadorTableroJugador(Sistema sistema){
        this.sistema = sistema;
    }

    @RequestMapping("/inicioTableroJugador")
    public Commands inicioCasoUsoTableroJugador(@SessionAttribute("registroIngreso") RegistroIngreso registro) {
        Jugador jugador = registro.getJugadorLogueado();
        
        return Commands.create(nombreJugador(jugador), saldoActual(jugador), montoTotalApostado(jugador),
                            montoTotalGanado(jugador), carrerasDisponiblesParaApuestas(), modalidadesApuestas(), 
                            apuestasRealizadasOrdenDesc(jugador));

    }
    
    

    public Command nombreJugador(Jugador jugador){
        return new Command("nombreCompleto", jugador.getNombreCompleto());
    }

    public Command saldoActual(Jugador jugador){
        return new Command("saldoActual", jugador.getSaldo());
    }

    public Command montoTotalApostado(Jugador jugador){
        return new Command("montoTotalApostado", jugador.totalApostado());
    }

    public Command montoTotalGanado(Jugador jugador){
        return new Command("montoTotalGanado", jugador.totalGanado());
    }

    public Command carrerasDisponiblesParaApuestas(){
        return new Command("carrerasDisponiblesParaApuestas",sistema.carrerasDisponiblesParaApostar());
    }

    public Command modalidadesApuestas(){
        return new Command("modalidadesApuestas", ModalidadDto.fromList(sistema.getModalidades()));
    }

    public Command apuestasRealizadasOrdenDesc(Jugador jugador){
        return new Command("apuestasRealizadasOrdenDesc", ApuestaDto.fromList(jugador.apuestasOrdenadasDesc()));
    }

}
