package org.example.marvelapi.persistence.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record GetUserInteractionLogDto(
        String id,
        String url,
        String httpMethod,
        String username,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS") //indica el formato de la fecha que se va a recibir
        LocalDateTime timestamp,
        String remoteAddress
) {
}
