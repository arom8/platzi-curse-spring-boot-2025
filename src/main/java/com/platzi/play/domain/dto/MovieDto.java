package com.platzi.play.domain.dto;

import java.time.LocalDate;

public record MovieDto(
        Long id,
        String title,
        Integer duration,
        String genre,
        LocalDate releaseDate,
        Double rating,
        Boolean status
) {
}
