package org.example.marvelapi.service;

import org.example.marvelapi.persistence.marvel.dto.CharacterDto;
import org.example.marvelapi.persistence.marvel.dto.MyPageable;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface CharacterService {
    List<CharacterDto> findAll(MyPageable pageable, String name, int[] comics, int[] series);

    List<CharacterDto> findAll(MyPageable pageable, String name, int[] stories, int[] series, HttpHeaders headers);

    CharacterDto.CharacterInfoDto findById(Long id);

    CharacterDto.CharacterInfoDto findById(Long id, HttpHeaders headers);

}
