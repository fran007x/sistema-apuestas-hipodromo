package ort.da.hipodromo.modelo.Usuario;

import java.time.LocalDateTime;

public class RegistroIngreso {
    private LocalDateTime fecha;
    private Jugador jugadorLogueado;
    
    public RegistroIngreso(Jugador jugadorLogueado) {
        this.jugadorLogueado = jugadorLogueado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Jugador getJugadorLogueado() {
        return jugadorLogueado;
    }
}
