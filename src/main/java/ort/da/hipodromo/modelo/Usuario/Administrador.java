package ort.da.hipodromo.modelo.Usuario;

import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;

public class Administrador extends Usuario {
    public Administrador(String nombreUsuario, String password, String nombreCompleto) {
        super(nombreUsuario, password, nombreCompleto);
    }

    @Override
    public void validar() throws DatosInvalidosException{
        super.validar();
}
}