package com.platzi.play.domain.exception;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException (){
        super("Película no encontrada");
    }
}
