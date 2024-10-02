package org.example.marvelapi.service.impl;

import org.example.marvelapi.persistence.marvel.dto.CharacterDto;
import org.example.marvelapi.persistence.marvel.dto.MyPageable;
import org.example.marvelapi.persistence.marvel.repository.CharacterRepository;
import org.example.marvelapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<CharacterDto> findAll(MyPageable pageable, String name, int[] stories, int[] series) {
        return characterRepository.findAll(pageable, name, stories, series,null);
    }

    @Override
    public List<CharacterDto> findAll(MyPageable pageable, String name, int[] stories, int[] series, HttpHeaders headers) {
        return characterRepository.findAll(pageable, name, stories, series,headers);
    }
    @Override
    public CharacterDto.CharacterInfoDto findById(Long id) {
        return characterRepository.findById(id,null);
    }

    @Override
    public CharacterDto.CharacterInfoDto findById(Long id, HttpHeaders headers) {
        return characterRepository.findById(id,headers);
    }

}
