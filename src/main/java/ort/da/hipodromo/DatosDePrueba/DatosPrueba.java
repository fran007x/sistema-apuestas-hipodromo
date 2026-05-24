package ort.da.hipodromo.DatosDePrueba;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ort.da.hipodromo.modelo.Fachada.Sistema;
import ort.da.hipodromo.modelo.Apuesta.*;
import ort.da.hipodromo.modelo.Carrera.*;


public class DatosPrueba {
    public static void precargar() {
        Sistema sistema = Sistema.getInstance();

        precargarUsuarios(sistema);
        precargarModalidades(sistema);
        precargarCaballos(sistema);
        precargarJornadasYCarreras(sistema);
    }

    private static void precargarUsuarios(Sistema sistema) {
        sistema.agregarAdministrador("a1", "a1", "Usuario Administrador");
        sistema.agregarAdministrador("a2", "a2", "Usuario Administrador2");

        sistema.agregarJugador("j1", "j1", "James Bond", 2000);
        sistema.agregarJugador("j2", "j2", "Tony Stark", 5000);

        for (int i = 3; i <= 25; i++) {
            sistema.agregarJugador("j" + i, "j" + i, "Jugador " + i, 200000);
        }
    }

    private static void precargarModalidades(Sistema sistema) {
        sistema.agregarModalidad(new Simple());
        sistema.agregarModalidad(new Triple());
        sistema.agregarModalidad(new Super());
    }

    private static void precargarCaballos(Sistema sistema) {
        sistema.agregarCaballo("Relampago");
        sistema.agregarCaballo("Trueno");
        sistema.agregarCaballo("Tormenta");
        sistema.agregarCaballo("Centella");
        sistema.agregarCaballo("Huracan");
        sistema.agregarCaballo("Furia");
    }

    private static void precargarJornadasYCarreras(Sistema sistema) {
        LocalDate hoy = LocalDate.now();
        LocalDate semanaAnterior = hoy.minusWeeks(1);
        LocalDate semanaPosterior = hoy.plusWeeks(1);

        sistema.agregarJornada(hoy);
        sistema.agregarJornada(semanaAnterior);
        sistema.agregarJornada(semanaPosterior);

        // 3 carreras de hoy, definidas y sin apuestas
        sistema.agregarCarrera(hoy, crearCarrera("Desafío del Sur", sistema, "Relampago", "Trueno"));
        sistema.agregarCarrera(hoy, crearCarrera("Clásico de la Costa", sistema, "Tormenta", "Centella"));
        sistema.agregarCarrera(hoy, crearCarrera("Copa Montevideo", sistema, "Huracan", "Furia"));

        // 1 carrera futura, definida
        sistema.agregarCarrera(semanaPosterior, crearCarrera("Gran Premio Aurora", sistema, "Relampago", "Tormenta"));

        // 2 carreras anteriores con apuestas y cerradas
        sistema.agregarCarrera(semanaAnterior, crearCarrera("Trofeo Eclipse", sistema, "Relampago", "Trueno"));
        sistema.agregarCarrera(semanaAnterior, crearCarrera("Gran Premio Estrella del Sur", sistema, "Tormenta", "Centella"));

        precargarApuestasYCerrarCarrera(sistema, semanaAnterior, 1);
        precargarApuestasYCerrarCarrera(sistema, semanaAnterior, 2);
    }

    private static Carrera crearCarrera(String nombre, Sistema sistema, String nombreCaballo1, String nombreCaballo2) {
        List<Registro> registros = new ArrayList<>();

        registros.add(new Registro(1, sistema.buscarCaballo(nombreCaballo1)));
        registros.add(new Registro(2, sistema.buscarCaballo(nombreCaballo2)));

        return new Carrera(nombre, registros);
    }

    private static void precargarApuestasYCerrarCarrera(Sistema sistema, LocalDate fecha, int numeroCarrera) {
        sistema.abrirCarrera(fecha, numeroCarrera);

        ModalidadApuesta simple = sistema.buscarModalidad("Simple");

        int jugador = 3;

        for (int i = 0; i < 10; i++) {
            ApuestaEnCurso apuesta = sistema.crearApuestaEnCurso(
                    "j" + jugador,
                    fecha,
                    numeroCarrera,
                    1,
                    simple.getNombre(),
                    1000 + (i * 100)
            );

            sistema.confirmarApuesta(apuesta, "j" + jugador);
            jugador++;
        }

        for (int i = 0; i < 10; i++) {
            ApuestaEnCurso apuesta = sistema.crearApuestaEnCurso(
                    "j" + jugador,
                    fecha,
                    numeroCarrera,
                    2,
                    simple.getNombre(),
                    1200 + (i * 100)
            );

            sistema.confirmarApuesta(apuesta, "j" + jugador);
            jugador++;
        }

        sistema.cerrarCarrera(fecha, numeroCarrera);
    }
}
