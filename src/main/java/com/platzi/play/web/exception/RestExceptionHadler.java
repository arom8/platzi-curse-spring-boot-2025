package com.platzi.play.web.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.platzi.play.domain.Genre;
import com.platzi.play.domain.exception.MovieAlreadyExistsException;
import com.platzi.play.domain.exception.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHadler {

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Error> handleException(MovieAlreadyExistsException ex) {
        Error error = new Error("movie-already-exists", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Error> handleException(MovieNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("movie-not-found", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new Error(error.getField(), error.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleException(HttpMessageNotReadableException e) {
        String type = "format-validation-error";
        String message = "Ha ocurrido un error en el formato de los datos enviados.";

        if (e.getCause() instanceof InvalidFormatException cause && cause.getTargetType() == Genre.class) {
            type = "genre-not-found";
            message = "El género enviado no es válido. Por favor, verifique los valores permitidos.";
        }

        if (e.getCause() instanceof InvalidFormatException cause && cause.getTargetType() == Integer.class) {
            type = "duration-invalid-format";
            message = "La duracion enviada no es válida. Por favor, especifique un valor valido.";
        }

        if (e.getCause() instanceof InvalidFormatException cause && cause.getTargetType() == Double.class) {
            type = "rating-invalid-format";
            message = "La clasificacion enviada no es válida. Por favor, especifique un valor valido.";
        }



        Error error = new Error(type, message);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception ex) {
        ex.printStackTrace();
        Error error = new Error("unknown-error", "Ocurrió un error inesperado");
        return ResponseEntity.internalServerError().body(error);
    }
}