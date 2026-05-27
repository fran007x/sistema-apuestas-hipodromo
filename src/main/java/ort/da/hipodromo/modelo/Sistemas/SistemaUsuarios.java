package ort.da.hipodromo.modelo.Sistemas;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Usuario.Usuario;
import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;
import ort.da.hipodromo.modelo.Usuario.Administrador;
import ort.da.hipodromo.modelo.Usuario.Jugador;
import ort.da.hipodromo.modelo.Usuario.RegistroIngreso;

public class SistemaUsuarios {
    private List<Jugador> jugadores;
    private List<Administrador> administradores;
    private List<RegistroIngreso> jugadoresActivos;

    public SistemaUsuarios() {
        this.jugadores = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.jugadoresActivos = new ArrayList<>();
    }

     public RegistroIngreso loginJugador(String nombreUsuario, String password) {
        Jugador jugador = (Jugador) buscarCredenciales(nombreUsuario, password, jugadores);

        if (jugador == null) {
            return null;
        }

        RegistroIngreso registro = new RegistroIngreso(jugador);
        jugadoresActivos.add(registro);

        return registro;
    }

    public Administrador loginAdministrador(String nombreUsuario, String password) {
        return (Administrador) buscarCredenciales(nombreUsuario, password, administradores);
    }

    private Usuario buscarCredenciales(String nombreUsuario, String password, List<? extends Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.coincidenCredenciales(nombreUsuario, password)) {
                return usuario;
            }
        }

        return null;
    }

    public void agregarJugador(String nombreUsuario, String password, String nombreCompleto, double saldo) {
        if (buscarJugador(nombreUsuario) != null) {
            throw new DatosInvalidosException("El jugador ya existe.");
        }

        jugadores.add(new Jugador(nombreUsuario, password, nombreCompleto, saldo));
    }

    public void agregarAdministrador(String nombreUsuario, String password, String nombreCompleto) {
        if (buscarAdministrador(nombreUsuario) != null) {
            throw new DatosInvalidosException("El administrador ya existe.");
        }

        administradores.add(new Administrador(nombreUsuario, password, nombreCompleto));
    }

    public Jugador buscarJugador(String nombreUsuario) {
        for (Jugador jugador : jugadores) {
            if (jugador.getNombreUsuario().equals(nombreUsuario)) {
                return jugador;
            }
        }

        return null;
    }

    public Administrador buscarAdministrador(String nombreUsuario) {
        for (Administrador administrador : administradores) {
            if (administrador.getNombreUsuario().equals(nombreUsuario)) {
                return administrador;
            }
        }

        return null;
    }

    public void logoutJugador(RegistroIngreso registro) {
        jugadoresActivos.remove(registro);
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public List<RegistroIngreso> getJugadoresActivos() {
        return jugadoresActivos;
    }
}