package ort.da.hipodromo.Presentadores;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import ort.da.hipodromo.Dtos.ApuestaDto;
import ort.da.hipodromo.Dtos.ModalidadDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.Presentadores.ConexionNavegador.ConexionNavegador;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Jugador;
import ort.da.hipodromo.modelo.Usuario.RegistroIngreso;
import ort.da.hipodromo.observador.Observable;
import ort.da.hipodromo.observador.Observador;


@RestController
@RequestMapping("/hipodromo")
@SessionScope
public class PresentadorTableroJugador implements Observador {
    private Sistema sistema;
    private ConexionNavegador conexionNavegador;
    private Jugador jugadorLogueado;

    public PresentadorTableroJugador(Sistema sistema, ConexionNavegador conexionNavegador){
        this.sistema = sistema;
        this.conexionNavegador = conexionNavegador;
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE(){
        conexionNavegador.conectarSSE();
        sistema.agregarObservador(this);
        return conexionNavegador.getConexionSSE();
    }

    @PostMapping("/inicioTableroJugador")
    public Commands inicioCasoUsoTableroJugador(@SessionAttribute("registroIngreso") RegistroIngreso registro) {
        Jugador jugador = registro.getJugadorLogueado();
        this.jugadorLogueado = jugador;
        
        return Commands.create(nombreJugador(jugador), saldoActual(jugador), montoTotalApostado(jugador),
                            montoTotalGanado(jugador), carrerasDisponiblesParaApuestas(), modalidadesApuestas(), 
                            apuestasRealizadasOrdenDesc(jugador));

    }
    
    @PostMapping("/finalizarTableroJugador")
    public Commands finalizarCasoUsoTableroJugador(){
        sistema.quitarObservador(this);
        return null;
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

    @Override
    public void actualizar(Observable origen, Object evento) {
        if(evento.equals(Sistema.Eventos.cambioApuestas) || evento.equals(Sistema.Eventos.cambioCarreras)) {
            if(jugadorLogueado == null){
                return;
            }

            conexionNavegador.enviarJSON(Commands.create(saldoActual(jugadorLogueado), montoTotalApostado(jugadorLogueado),
                            montoTotalGanado(jugadorLogueado),carrerasDisponiblesParaApuestas(),apuestasRealizadasOrdenDesc(jugadorLogueado)
                    )
            );
        }
    }

}
