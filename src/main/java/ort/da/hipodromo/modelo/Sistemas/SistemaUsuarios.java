package ort.da.hipodromo.modelo;

import java.util.ArrayList;
import java.util.List;

public class SistemaUsuarios {
    private List<Usuario> usuarios;

    public SistemaUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

}