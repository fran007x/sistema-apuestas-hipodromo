package ort.da.hipodromo.Presentadores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import jakarta.servlet.http.HttpSession;
import ort.da.hipodromo.Dtos.CarreraFinalizadaDto;
import ort.da.hipodromo.Dtos.JornadaAdminDto;
import ort.da.hipodromo.Dtos.ProximaCarreraDto;
import ort.da.hipodromo.Presentadores.Comandos.Command;
import ort.da.hipodromo.Presentadores.Comandos.Commands;
import ort.da.hipodromo.Presentadores.ConexionNavegador.ConexionNavegador;
import ort.da.hipodromo.modelo.Carrera.Jornada;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Administrador;
import ort.da.hipodromo.observador.Observable;
import ort.da.hipodromo.observador.Observador;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/administracion")
@SessionScope
public class PresentadorTableroAdministrador implements Observador {
    private Sistema sistema;
    private LocalDate fechaJornadaSeleccionada;
    private ConexionNavegador conexionNavegador;

    public PresentadorTableroAdministrador(Sistema sistema, ConexionNavegador conexionNavegador){
        this.sistema = sistema;
        this.conexionNavegador = conexionNavegador;
    }

    @GetMapping(value = "/registrarSSE", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter registrarSSE(){
        conexionNavegador.conectarSSE();
        sistema.agregarObservador(this);
        return conexionNavegador.getConexionSSE();
    }

    @PostMapping("/inicioTableroAdmin")
    public Commands inicioCasoUsoTableroAdmin(@SessionAttribute("administrador") Administrador admin, HttpSession session){ 
        Jornada jornada = sistema.jornadaActual();

        this.fechaJornadaSeleccionada = jornada.getFecha();

        session.setAttribute("fechaJornadaSeleccionada", jornada.getFecha());

        return comandosTablero(jornada);
    }

    @PostMapping("/finalizarTableroAdmin")
    public Commands finalizarCasoUsoTableroAdmin(){
        sistema.quitarObservador(this);
        return null;
    }

    @PostMapping("/jornadaAnterior")
    public Commands jornadaAnterior(@SessionAttribute("fechaJornadaSeleccionada") LocalDate fechaSeleccionada, HttpSession session){
        Jornada jornada = sistema.jornadaAnterior(fechaSeleccionada);
        
        if(jornada == null){
            return Commands.create(mensajeError("No hay jornadas anteriores a la seleccionada"));
        }

        this.fechaJornadaSeleccionada = jornada.getFecha();

        session.setAttribute("fechaJornadaSeleccionada", jornada.getFecha());
        return comandosTablero(jornada);
    }

    @PostMapping("/jornadaSiguiente")
    public Commands jornadaSiguiente(@SessionAttribute("fechaJornadaSeleccionada") LocalDate fechaSeleccionada, HttpSession session){
        Jornada jornada = sistema.jornadaSiguiente(fechaSeleccionada);
        
        if(jornada == null){
            return Commands.create(mensajeError("No hay jornadas siguientes a la seleccionada"));
        }

        this.fechaJornadaSeleccionada = jornada.getFecha();

        session.setAttribute("fechaJornadaSeleccionada", jornada.getFecha());
        return comandosTablero(jornada);
    }
    

    private Commands comandosTablero(Jornada jornada){
        return Commands.create(datosJornadaActual(jornada), carrerasFinalizadas(jornada), proximasCarreras(jornada));
    }

    public Command datosJornadaActual(Jornada jornada){
        return new Command("datosJornadaActual", new JornadaAdminDto(jornada, sistema.getComision()));
    }

    public Command carrerasFinalizadas(Jornada jornada){
        return new Command("carrerasFinalizadas", CarreraFinalizadaDto.fromList(jornada.carrerasFinalizadasOrdenadasDesc()));
    }

    public Command proximasCarreras(Jornada jornada){
        return new Command("proximasCarreras", ProximaCarreraDto.fromList(jornada.getFecha(), jornada.proximasCarreras()));
    }

    public Command mensajeError(String texto){
        return new Command("mensajeError", texto);
    }

    @Override
    public void actualizar(Observable origen, Object evento) {
        if(evento.equals(Sistema.Eventos.cambioCarreras) || evento.equals(Sistema.Eventos.cambioApuestas)) {
        Jornada jornada = sistema.buscarJornada(fechaJornadaSeleccionada);

        conexionNavegador.enviarJSON(comandosTablero(jornada));
       }
    }

}
