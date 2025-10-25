package com.platzi.play.domain.dto;

import com.platzi.play.domain.Genre;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record MovieDto(
        Long id,
        @NotEmpty(message = "El título es obligatorio")
        String title,
        @Positive(message = "La duracion es obligatoria")
        Integer duration,
        @NotNull(message = "El género es obligatorio")
        Genre genre,
        @PastOrPresent(message = "La fecha de lanzamiento debe ser anterior a la fecha actual")
        LocalDate releaseDate,
        @Min(value = 0, message = "El rating no puede ser menor que 0")
        @Max(value = 5, message = "El rating no puede ser mayor que 5")
        Double rating,
        Boolean status
) {
}
