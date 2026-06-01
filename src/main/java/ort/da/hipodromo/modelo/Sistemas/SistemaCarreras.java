package ort.da.hipodromo.modelo.Sistemas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.Dtos.CarreraDisponibleDto;
import ort.da.hipodromo.modelo.Carrera.Caballo;
import ort.da.hipodromo.modelo.Carrera.Carrera;
import ort.da.hipodromo.modelo.Carrera.Jornada;
import ort.da.hipodromo.modelo.Carrera.Registro;
import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;

public class SistemaCarreras {
    private List<Jornada> jornadas;
    private List<Caballo> caballos;
    
    public SistemaCarreras() {
        this.jornadas = new ArrayList<>();
        this.caballos = new ArrayList<>();
    }

    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public List<Caballo> getCaballos() {
        return caballos;
    }

    public void agregarJornada(LocalDate fecha){
            if(buscarJornada(fecha) != null){
                throw new DatosInvalidosException("Ya hay una jornada para este dia.");
            }
            jornadas.add(new Jornada(fecha));
    }

    public Jornada buscarJornada(LocalDate fecha){
        for(Jornada jornada : jornadas){
            if(jornada.esDeFecha(fecha)){
                return jornada;
            }
        }
        return null;
    }

    public void agregarCaballo(String nombre){
        if(buscarCaballo(nombre) != null){
            throw new DatosInvalidosException("El caballo ya fue inscripto");
        }
        caballos.add(new Caballo(nombre));
    }

    public Caballo buscarCaballo(String nombre){
        for(Caballo caballo : caballos){
            if(caballo.getNombre().equals(nombre)){
                return caballo;
            }
        }
        return null;
    }

    
    
    public void agregarCarrera(LocalDate fechaJornada, Carrera carrera){
        Jornada jornada = buscarJornada(fechaJornada);

        if(jornada == null){
            throw new DatosInvalidosException("No existe una jornada para esa fecha");
        }
        jornada.agregarCarrera(carrera);
    }

    public Carrera buscarCarrera(LocalDate fechaJornada, int numeroCarrera){
        Jornada jornada = buscarJornada(fechaJornada);

        if(jornada == null){
            return null;
        }
        return jornada.buscarCarrera(numeroCarrera);
    }

    public Registro buscarRegistro(LocalDate fechaJornada,int numeroCarrera,int numeroRegistro){
        Carrera carrera = buscarCarrera(fechaJornada, numeroCarrera);

        if(carrera == null){
            return null;
        }
        return carrera.obtenerRegistro(numeroRegistro);
    }

    public List<CarreraDisponibleDto> carrerasDisponiblesParaApostar(){
        List<CarreraDisponibleDto> disponibles = new ArrayList<>();

        for(Jornada jornada : jornadas){
            disponibles.addAll(jornada.carrerasDisponiblesParaApostar());
        }
        return disponibles;
    }

    public Jornada jornadaActual(){
        LocalDate fechaHoy = LocalDate.now();
        Jornada actual = null;

        for(Jornada jornada : jornadas){
            if(jornada.esDeFecha(fechaHoy)){
                return jornada;
            }
            if(esAntes(jornada, fechaHoy)){
                if(actual == null || esDespues(jornada, actual.getFecha())){
                    actual = jornada;
                }
            }
        }
        return actual;
    }

    public Jornada jornadaSiguiente(LocalDate fechaActual){
        Jornada siguiente = null;

        for(Jornada jornada : jornadas){
            if(esDespues(jornada, fechaActual)){
                if(siguiente == null || esAntes(jornada, siguiente.getFecha())){
                    siguiente = jornada;
                }
            }
        }
        return siguiente;
    }

    public Jornada jornadaAnterior(LocalDate fechaActual){
        Jornada anterior = null;

        for(Jornada jornada : jornadas){
            if(esAntes(jornada, fechaActual)){
                if(anterior == null || esDespues(jornada, anterior.getFecha())){
                    anterior = jornada;
                }
            }
        }
        return anterior;
    }

    private boolean esAntes(Jornada jornada, LocalDate fecha){
        return jornada.getFecha().isBefore(fecha);
    }
    private boolean esDespues(Jornada jornada, LocalDate fecha){
        return jornada.getFecha().isAfter(fecha);
    }

    public void abrirCarrera(LocalDate fechaJornada, int numeroCarrera){
        Carrera carrera = buscarCarrera(fechaJornada, numeroCarrera);

        if(carrera == null){
            throw new DatosInvalidosException("No existe la carrera indicada");
        }
        carrera.abrir();
    }

    public void cerrarCarrera(LocalDate fechaJornada, int numeroCarrera, double comision){
        Carrera carrera = buscarCarrera(fechaJornada, numeroCarrera);

        if(carrera == null){
            throw new DatosInvalidosException("No existe la carrera indicada");
        }
        carrera.cerrar(comision);
    }

    public void finalizarCarrera(LocalDate fechaJornada, int numeroCarrera,int numeroRegistroGanador){
        Carrera carrera = buscarCarrera(fechaJornada, numeroCarrera);

        if(carrera == null){
            throw new DatosInvalidosException("No existe la carrera indicada");
        }

        Registro ganador = carrera.obtenerRegistro(numeroRegistroGanador);

        if(ganador == null){
            throw new DatosInvalidosException("Debe indicar el caballo ganador de la carrera");
        }
        carrera.finalizar(ganador);
    }
}