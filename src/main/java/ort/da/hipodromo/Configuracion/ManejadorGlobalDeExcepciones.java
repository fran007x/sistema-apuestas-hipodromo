package ort.da.hipodromo.Configuracion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ort.da.hipodromo.modelo.Exceptions.CredencialesInvalidasException;
import ort.da.hipodromo.modelo.Exceptions.DatosInvalidosException;
import ort.da.hipodromo.modelo.Exceptions.EstadoCarreraInvalidoException;
import ort.da.hipodromo.modelo.Exceptions.MontoInvalidoException;
import ort.da.hipodromo.modelo.Exceptions.SaldoInsuficienteException;

@RestControllerAdvice
public class ManejadorGlobalDeExcepciones {

    @ExceptionHandler(DatosInvalidosException.class)
    public ResponseEntity<String> manejarDatosInvalidos(DatosInvalidosException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<String> manejarCredencialesInvalidas(CredencialesInvalidasException ex) {
        return ResponseEntity
                .status(401)
                .body(ex.getMessage());
    }

    @ExceptionHandler(MontoInvalidoException.class)
    public ResponseEntity<String> manejarMontoInvalido(MontoInvalidoException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> manejarSaldoInsuficiente(SaldoInsuficienteException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(EstadoCarreraInvalidoException.class)
    public ResponseEntity<String> manejarEstadoCarreraInvalido(EstadoCarreraInvalidoException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErrorGeneral(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body("Ocurrió un error inesperado.");
    }

}
