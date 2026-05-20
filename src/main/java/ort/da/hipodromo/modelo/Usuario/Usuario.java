package ort.da.hipodromo.modelo.Usuario;

import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;

public abstract class Usuario {
    private String nombreUsuario;
    private String password;
    private String nombreCompleto;

    public Usuario(String nombreUsuario, String password, String nombreCompleto) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombreCompleto = nombreCompleto;   
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return "Usuario [nombreUsuario=" + nombreUsuario + ", password=" + password + ", nombreCompleto="
                + nombreCompleto + "]";
    }

    public void validar() throws DatosInvalidosException{
        validarCamposObligatorios();
        validarNombreUsuario();
        validarPassword();
        validarNombreCompleto();
    }

    private void validarCamposObligatorios() throws DatosInvalidosException{
        if(nombreUsuario == null || nombreUsuario.isBlank() 
        || password == null || password.isBlank() 
        || nombreCompleto == null || nombreCompleto.isBlank() ){
            throw new DatosInvalidosException("El nombre de usuario no puede ser nulo o vacío.");
        }
    }

    private void validarNombreUsuario() throws DatosInvalidosException{
        if(nombreUsuario.length() < 2 || nombreUsuario.length() > 12 ){
            throw new DatosInvalidosException("El nombre de usuario debe tener entre 2 y 12 caracteres");
        }
    }

    private void validarPassword() throws DatosInvalidosException{
        if(password.length() < 8){
            throw new DatosInvalidosException("La contraseña debe tener al menos 8 caracteres.");
        }
    }

    private void validarNombreCompleto() throws DatosInvalidosException{
        String[] partes = nombreCompleto.trim().split("\\s+");

        if(partes.length < 2){
        throw new DatosInvalidosException("Debe ingresar nombre y apellido.");
        }
    }

    public boolean coincidenCredenciales(String nombreUsuario, String password){
        return this.nombreUsuario.equals(nombreUsuario) && this.password.equals(password);
    }

}