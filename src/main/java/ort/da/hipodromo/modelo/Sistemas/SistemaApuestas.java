package ort.da.hipodromo.modelo.Sistemas;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.Apuesta;
import ort.da.hipodromo.modelo.Apuesta.ApuestaEnCurso;
import ort.da.hipodromo.modelo.Apuesta.ModalidadApuesta;
import ort.da.hipodromo.modelo.Carrera.Carrera;
import ort.da.hipodromo.modelo.Carrera.Registro;
import ort.da.hipodromo.modelo.Exceptions.CredencialesInvalidasException;
import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;
import ort.da.hipodromo.modelo.Exceptions.EstadoCarreraInvalidoException;
import ort.da.hipodromo.modelo.Exceptions.MontoInvalidoException;
import ort.da.hipodromo.modelo.Exceptions.SaldoInsuficienteException;
import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Usuario.Jugador;

public class SistemaApuestas {
    private List<ApuestaEnCurso> apuestasEnCurso;
    private List<ModalidadApuesta> modalidades;
    private double comision;
    
    public SistemaApuestas() {
        this.apuestasEnCurso = new ArrayList<>();
        this.modalidades = new ArrayList<>();
        this.comision = 0.10;
    }

    public List<ApuestaEnCurso> getApuestasEnCurso() {
        return apuestasEnCurso;
    }

    public List<ModalidadApuesta> getModalidades() {
        return modalidades;
    }

    public double getComision() {
        return comision;
    }

    public void agregarModalidad(ModalidadApuesta modalidad){
        modalidades.add(modalidad);
    }

    public ModalidadApuesta buscarModalidad(String nombre){
        for(ModalidadApuesta modalidad : modalidades){
            if(modalidad.getNombre().equals(nombre)){
                return modalidad;
            }
        }
        return null;
    }

    public ApuestaEnCurso crearApuestaEnCurso(Jugador jugador, Carrera carrera, Registro registro, ModalidadApuesta modalidad, double monto){
        if(jugador == null){
            throw new DatosInvalidosException("El jugador no existe.");
        }
        if(carrera == null){
            throw new DatosInvalidosException("La carrera no existe.");
        }

        if(registro == null){
            throw new DatosInvalidosException("El caballo indicado no existe en la carrera.");
        }

        if(modalidad == null){
            throw new DatosInvalidosException("La modalidad no existe.");
        }

        if(monto < 1){
            throw new MontoInvalidoException("Monto invalido.");
        }

        if(!carrera.permiteApuestas()){
            throw new EstadoCarreraInvalidoException("Esta carrera ya no recibe apuestas.");
        }

        ApuestaEnCurso apuestaEnCurso = new ApuestaEnCurso(jugador,carrera,registro,modalidad,monto);

        apuestasEnCurso.add(apuestaEnCurso);

        return apuestaEnCurso;
    }

    public Apuesta confirmarApuesta(ApuestaEnCurso apuestaEnCurso, String password){
        Jugador jugador = apuestaEnCurso.getJugador();

        if(!jugador.getPassword().equals(password)){
            throw new CredencialesInvalidasException("Contraseña incorrecta.");
        }

        if(!apuestaEnCurso.getCarrera().permiteApuestas()){
            throw new EstadoCarreraInvalidoException("Esta carrera ya no permite apuestas.");
        }

        if(!jugador.tieneSaldoSuficiente(apuestaEnCurso.costo())){
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }

        jugador.descontarSaldo(apuestaEnCurso.costo());

        Apuesta apuesta = apuestaEnCurso.confirmarApuesta();

        jugador.agregarApuesta(apuesta);

        apuesta.getRegistro().agregarApuesta(apuesta);

        apuestaEnCurso.getCarrera().actualizarEstadoPorDividendos(comision);

        apuestasEnCurso.remove(apuestaEnCurso);

        Sistema.getInstance().avisar(Sistema.Eventos.cambioApuestas);

        return apuesta;
    }

    public void cancelarApuesta(ApuestaEnCurso apuestaEnCurso){
        apuestasEnCurso.remove(apuestaEnCurso);
    }
    
}