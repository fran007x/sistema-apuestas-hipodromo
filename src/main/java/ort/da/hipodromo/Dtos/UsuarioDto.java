package ort.da.hipodromo.Dtos;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Usuario.Usuario;
import ort.da.hipodromo.modelo.Usuario.Jugador;

public class UsuarioDto {

    private String nombreUsuario;
    private String nombreCompleto;
    private Double saldo;

    public UsuarioDto(Usuario usuario) {

        this.nombreUsuario =
                usuario.getNombreUsuario();

        this.nombreCompleto =
                usuario.getNombreCompleto();

        if(usuario instanceof Jugador){
            this.saldo =
                    ((Jugador) usuario)
                    .getSaldo();
        }
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Double getSaldo() {
        return saldo;
    }

    public static List<UsuarioDto>fromList(List<Usuario> usuarios){

        List<UsuarioDto> result = new ArrayList<>();

        for(Usuario usuario : usuarios){
            result.add(new UsuarioDto(usuario)); 
        }
        return result;
    }

}
