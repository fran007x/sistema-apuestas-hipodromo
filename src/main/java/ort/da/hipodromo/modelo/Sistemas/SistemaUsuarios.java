package ort.da.hipodromo.modelo.Sistemas;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Usuario.Usuario;
import ort.da.hipodromo.modelo.Exceptions.CredencialesInvalidasException;
import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;
import ort.da.hipodromo.modelo.Usuario.Administrador;
import ort.da.hipodromo.modelo.Usuario.Jugador;

public class SistemaUsuarios {
    private List<Usuario> usuarios;

    public SistemaUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }


    public Usuario login(String nombreUsuario, String password) {
        Usuario usuario = buscarUsuario(nombreUsuario);
            if(usuario != null && usuario.coincidenCredenciales(nombreUsuario, password)){
                return usuario;
            }
        
        throw new CredencialesInvalidasException("Acceso denegado");
    }

    public void agregarJugador(String nombreUsuario,String password,String nombreCompleto,double saldo){
        if(buscarUsuario(nombreUsuario) != null){
            throw new DatosInvalidosException("El usuario ya existe");
        }
        usuarios.add(new Jugador(nombreUsuario,password,nombreCompleto,saldo));
    }

    public void agregarAdministrador(String nombreUsuario,String password,String nombreCompleto){
        if(buscarUsuario(nombreUsuario) != null){
            throw new DatosInvalidosException("El usuario ya existe");
        }
        usuarios.add(new Administrador(nombreUsuario,password,nombreCompleto));
    }

    public Usuario buscarUsuario(String nombreUsuario){
        for(Usuario usuario : usuarios){
            if(usuario.getNombreUsuario().equals(nombreUsuario)){
                return usuario;
            }
        }
        return null;
    }
}