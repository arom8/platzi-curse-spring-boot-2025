package com.platzi.play;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface PlatziPlayAiService {

    @UserMessage("""
            Genera un saludo de bienvenida a la plataforma de GestiÃ³n de PelÃ­culas PlatziPlay. 
            Usa menos de 120 caracteres y hazlo con el estilo de Platzi.
            """)
    String generateGreeting();
}
