package org.example.marvelapi.service.impl;

import org.example.marvelapi.persistence.marvel.dto.ComicDto;
import org.example.marvelapi.persistence.marvel.dto.MyPageable;
import org.example.marvelapi.persistence.marvel.repository.ComicRepository;
import org.example.marvelapi.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicRepository comicService;
    @Override
    public List<ComicDto> findAllCustom(MyPageable pageable, Long characterId, HttpHeaders headers) {

        return comicService.findAll(pageable, characterId, headers);
    }

    @Override
    public List<ComicDto> findAll(MyPageable pageable, Long characterId) {
        return comicService.findAll(pageable, characterId,null);
    }

    @Override
    public ComicDto findById(Long id) {
        return comicService.findById(id,null);
    }

    @Override
    public ComicDto findByIdCustom(Long id, HttpHeaders headers) {
        return comicService.findById(id, headers);
    }


}
