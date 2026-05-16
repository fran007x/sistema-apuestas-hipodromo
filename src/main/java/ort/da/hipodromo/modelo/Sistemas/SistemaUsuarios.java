package ort.da.hipodromo.modelo.Sistemas;

import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Usuario.Usuario;

public class SistemaUsuarios {
    private List<Usuario> usuarios;

    public SistemaUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

}