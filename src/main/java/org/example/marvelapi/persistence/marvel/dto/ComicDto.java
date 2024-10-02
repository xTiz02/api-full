package org.example.marvelapi.persistence.marvel.dto;

public record ComicDto(
        Long id,
        String title,
        String description,
        String modified,
        String resourceURI,
        ThumbnailDto  thumbnailDto
) {

}
