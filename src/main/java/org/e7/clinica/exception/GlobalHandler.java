package org.e7.clinica.exception;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalHandler {

    //Manejar excepciones de recurso no encontrado
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> manejarExcepcionesRecursoNoEncontrado(ResourceNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    // Manejar excepciones de BadRequest
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> manejarBadRequestException(BadRequestException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    //  Manejar excepciones generales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarTodasLasExcepciones(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
