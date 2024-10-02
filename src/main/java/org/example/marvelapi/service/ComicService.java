package org.example.marvelapi.service;

import org.example.marvelapi.persistence.marvel.dto.ComicDto;
import org.example.marvelapi.persistence.marvel.dto.MyPageable;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface ComicService {
    List<ComicDto> findAllCustom(MyPageable pageable, Long characterId, HttpHeaders headers);
    List<ComicDto> findAll(MyPageable pageable, Long characterId);
    ComicDto findById(Long id);
    ComicDto findByIdCustom(Long id, HttpHeaders headers);
}
