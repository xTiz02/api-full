package org.example.marvelapi.persistence.entity.dto;

public record ApiErrorDto(
        String message,
        String backendMessage,
        String method,
        String url
) {
}
