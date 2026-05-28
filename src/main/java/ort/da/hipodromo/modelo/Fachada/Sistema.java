package ort.da.hipodromo.modelo.Fachada;

import java.time.LocalDate;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.Apuesta;
import ort.da.hipodromo.modelo.Apuesta.ApuestaEnCurso;
import ort.da.hipodromo.modelo.Apuesta.ModalidadApuesta;
import ort.da.hipodromo.modelo.Carrera.Caballo;
import ort.da.hipodromo.modelo.Carrera.Carrera;
import ort.da.hipodromo.modelo.Carrera.Jornada;
import ort.da.hipodromo.modelo.Carrera.Registro;
import ort.da.hipodromo.modelo.Sistemas.SistemaApuestas;
import ort.da.hipodromo.modelo.Sistemas.SistemaCarreras;
import ort.da.hipodromo.modelo.Sistemas.SistemaUsuarios;
import ort.da.hipodromo.modelo.Usuario.Administrador;
import ort.da.hipodromo.modelo.Usuario.Jugador;
import ort.da.hipodromo.modelo.Usuario.RegistroIngreso;

public class Sistema {
    private static Sistema instancia;

    private SistemaUsuarios sistemaUsuarios;
    private SistemaApuestas sistemaApuestas;
    private SistemaCarreras sistemaCarreras;

    private Sistema() {
        this.sistemaUsuarios = new SistemaUsuarios();
        this.sistemaApuestas = new SistemaApuestas();
        this.sistemaCarreras = new SistemaCarreras();
    }

    public static Sistema getInstance(){
        if(instancia == null){
            instancia = new Sistema();
        }
        return instancia;
    }

    public RegistroIngreso loginJugador(String nombreUsuario,String password){
        return sistemaUsuarios.loginJugador(nombreUsuario, password);
    }

    public void agregarJugador(String nombreUsuario,String password,String nombreCompleto,double saldo){
        sistemaUsuarios.agregarJugador(nombreUsuario, password, nombreCompleto, saldo);
    }

    public Administrador loginAdministrador(String nombreUsuario,String password){
        return sistemaUsuarios.loginAdministrador(nombreUsuario, password);
    }


    public void agregarAdministrador(String nombreUsuario,String password,String nombreCompleto){
        sistemaUsuarios.agregarAdministrador(nombreUsuario, password, nombreCompleto);
    }

    public Jugador buscarJugador(String nombreUsuario){
        return sistemaUsuarios.buscarJugador(nombreUsuario);
    }

    public Administrador buscarAdministrador(String nombreUsuario){
        return sistemaUsuarios.buscarAdministrador(nombreUsuario);
    }

     public void logoutJugador(RegistroIngreso registro) {
        sistemaUsuarios.logoutJugador(registro);
    }

    public List<RegistroIngreso> getJugadoresActivos(){
        return sistemaUsuarios.getJugadoresActivos();
    }

    public void agregarJornada(LocalDate fecha){
        sistemaCarreras.agregarJornada(fecha);
    }

    public Jornada buscarJornada(LocalDate fecha){
        return sistemaCarreras.buscarJornada(fecha);
    }

    public void agregarCaballo(String nombre){
        sistemaCarreras.agregarCaballo(nombre);
    }

    public Caballo buscarCaballo(String nombre){
        return sistemaCarreras.buscarCaballo(nombre);
    }

    public void agregarCarrera(LocalDate fechaJornada, Carrera carrera){
        sistemaCarreras.agregarCarrera(fechaJornada, carrera);
    }

    public Carrera buscarCarrera(LocalDate fechaJornada, int numeroCarrera){
        return sistemaCarreras.buscarCarrera(fechaJornada, numeroCarrera);
    }

    public Registro buscarRegistro(LocalDate fechaJornada,int numeroCarrera,int numeroRegistro){
        return sistemaCarreras.buscarRegistro(fechaJornada, numeroCarrera, numeroRegistro);
    }

    public List<Carrera> carrerasDisponiblesParaApostar(){
        return sistemaCarreras.carrerasDisponiblesParaApostar();
    }

    public Jornada jornadaActual(){
        return sistemaCarreras.jornadaActual();
    }

    public Jornada jornadaSiguiente(LocalDate fechaActual){
        return sistemaCarreras.jornadaSiguiente(fechaActual);
    }

    public Jornada jornadaAnterior(LocalDate fechaActual){
        return sistemaCarreras.jornadaAnterior(fechaActual);
    }

    public void abrirCarrera(LocalDate fechaJornada, int numeroCarrera){
        sistemaCarreras.abrirCarrera(fechaJornada, numeroCarrera);
    }

    public void cerrarCarrera(LocalDate fechaJornada, int numeroCarrera){
        sistemaCarreras.cerrarCarrera(fechaJornada, numeroCarrera, sistemaApuestas.getComision());
    }

    public void finalizarCarrera(LocalDate fechaJornada, int numeroCarrera,int numeroRegistroGanador){
        sistemaCarreras.finalizarCarrera(fechaJornada, numeroCarrera, numeroRegistroGanador);
    }

    public void agregarModalidad(ModalidadApuesta modalidad){
        sistemaApuestas.agregarModalidad(modalidad);
    }

    public List<ModalidadApuesta> getModalidades(){  
        return sistemaApuestas.getModalidades();
    }

    public ModalidadApuesta buscarModalidad(String nombre){
        return sistemaApuestas.buscarModalidad(nombre);
    }

    public ApuestaEnCurso crearApuestaEnCurso(String nombreUsuario,LocalDate fechaJornada, int numeroCarrera, int numeroRegistro, String nombreModalidad, double monto){
        Jugador jugador = this.buscarJugador(nombreUsuario);
        Carrera carrera = this.buscarCarrera(fechaJornada, numeroCarrera);
        Registro registro = this.buscarRegistro(fechaJornada, numeroCarrera, numeroRegistro);
        ModalidadApuesta modalidad = this.buscarModalidad(nombreModalidad);

        return sistemaApuestas.crearApuestaEnCurso(jugador, carrera, registro, modalidad, monto);
    }

    public Apuesta confirmarApuesta(ApuestaEnCurso apuestaEnCurso, String password){
        return sistemaApuestas.confirmarApuesta(apuestaEnCurso, password);
    }

    public void cancelarApuesta(ApuestaEnCurso apuestaEnCurso){
        sistemaApuestas.cancelarApuesta(apuestaEnCurso);
    }
}