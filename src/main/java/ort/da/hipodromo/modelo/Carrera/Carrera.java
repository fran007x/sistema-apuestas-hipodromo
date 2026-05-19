package ort.da.hipodromo.modelo.Carrera;

import java.time.LocalDateTime;
import java.util.List;

import ort.da.hipodromo.modelo.Apuesta.Apuesta;
import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;
import ort.da.hipodromo.modelo.Exceptions.EstadoCarreraInvalidoException;

public class Carrera {
    private int numero;
    private String nombre;
    private EstadoCarrera estado;;
    private List<Registro> registros;
    private Registro caballoGanador;
    private LocalDateTime horaFinalizacion;


    

    public Carrera(int numero,String nombre, List<Registro> registros) {
        this.numero = numero;
        this.nombre = nombre;
        this.estado = EstadoCarrera.Definida;
        this.registros = registros;
        this.caballoGanador = null;
        this.horaFinalizacion = null;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre(){
        return nombre;
    }

    public EstadoCarrera getEstado() {
        return estado;
    }

    public LocalDateTime getHoraFinalizacion() {
        return horaFinalizacion;
    }

    public void setHoraFinalizacion(LocalDateTime horaFinalizacion) {
        this.horaFinalizacion = horaFinalizacion;
    }

    public List<Registro> getRegistros() {
        return registros;
    }

    public Registro getCaballoGanador() {
        return caballoGanador;
    }

    public void setCaballoGanador(Registro caballoGanador) {
        this.caballoGanador = caballoGanador;
    }

    @Override
    public String toString() {
        return "Carrera [numero=" + numero + ", estado=" + estado + ", registros=" + registros + ", caballoGanador="
                + caballoGanador + ", horaFinalizacion=" + horaFinalizacion + "]";
    }

    public int cantidadRegistros(){
        return this.registros.size();
    }

    public boolean estaAbierta(){
        return this.getEstado().equals(EstadoCarrera.Abierta);
    }
    
    public boolean estaEstable(){
        return this.getEstado().equals(EstadoCarrera.Estable);
    }

    public boolean estaDefinida(){
        return this.getEstado().equals(EstadoCarrera.Definida);
    }

    public boolean estaCerrada(){
        return this.getEstado().equals(EstadoCarrera.Cerrada);
    }

    public boolean permiteApuestas(){
        return estaAbierta() || estaEstable();
    }


    public void abrir() throws EstadoCarreraInvalidoException{
        if (!estaDefinida()) {
            throw new EstadoCarreraInvalidoException("No se puede abrir esta carrera.");
        }
            estado = EstadoCarrera.Abierta; 
    }

    public void cerrar(double comision) throws EstadoCarreraInvalidoException{
        if (!estaEstable()) {
            throw new EstadoCarreraInvalidoException("No es posible cerrar esta carrera.");
        }
        guardarDividendosFinalesEnApuestas(comision);

        estado = EstadoCarrera.Cerrada;
    }

    public void finalizar(Registro ganador) throws EstadoCarreraInvalidoException, DatosInvalidosException{
        if (!estaCerrada()) {
            throw new EstadoCarreraInvalidoException("No se puede finalizar esta carrera.");
        }

        if (ganador == null) {
            throw new DatosInvalidosException("Debe indicar el caballo ganador de la carrera.");
        }

        if (!registros.contains(ganador)) {
            throw new DatosInvalidosException("El caballo ganador no pertenece a esta carrera.");
        }

        this.caballoGanador = ganador;
        this.horaFinalizacion = LocalDateTime.now();

        liquidarApuestas();

        estado = EstadoCarrera.Finalizada;
    }


    public boolean participantesTienenDividendosValidos(double comision){
        for(Registro registro : registros){
            if(!registro.dividendoValido(totalApostado(), comision)){
                return false;
            }
        }
        return true;
    }

    public void actualizarEstadoPorDividendos(double comision){
        if(!estaAbierta() && !estaEstable()){
            return;
        }

        if(participantesTienenDividendosValidos(comision)){
            estado = EstadoCarrera.Estable;
        }else{
            estado = EstadoCarrera.Abierta;
        }
    }

    public double totalApostado(){
        double totalApostado = 0;

        for(Registro registro : registros){
            totalApostado += registro.totalApostado();
        }
        return totalApostado;
    }

    public double totalPagado(){
        double total = 0;

        for(Registro registro : registros){
            total+= registro.totalPagado();
        }
        return total;
    }

    private void guardarDividendosFinalesEnApuestas(double comision){
        for(Registro registro : registros){
            double dividendoFinal = registro.calcularDividendo(totalApostado(), comision);
            registro.guardarDividendoFinalEnApuestas(dividendoFinal);
        }
    }

    private void liquidarApuestas(){
        for(Registro registro : registros){
            registro.liquidarApuestas(caballoGanador);
        }
    }

}