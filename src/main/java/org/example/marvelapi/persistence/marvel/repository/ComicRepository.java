package org.example.marvelapi.persistence.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.example.marvelapi.config.marvel.MarvelApiConfig;
import org.example.marvelapi.persistence.marvel.dto.ComicDto;
import org.example.marvelapi.persistence.marvel.dto.MyPageable;
import org.example.marvelapi.persistence.marvel.mapper.ComicMapper;
import org.example.marvelapi.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Repository
public class ComicRepository {

    @Autowired
    private MarvelApiConfig marvelApiConfig;

    @Autowired
    private HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    @Value("${integration.marvel.comics-path}")
    private String comicsPath;

    @PostConstruct
    public void init(){
        this.comicsPath = basePath.concat(comicsPath);
    }

    public List<ComicDto> findAll(MyPageable pageable, Long characterId, HttpHeaders headers) {
        var fullQueryParams = this.getQueryParamsForFindAll(pageable,characterId);
        if(ObjectUtils.isEmpty(headers)){
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        JsonNode jsonNode = httpClientService.doGet(comicsPath,fullQueryParams,JsonNode.class, headers);
        return ComicMapper.toDtoList(jsonNode);

    }

    public ComicDto findById(Long id, HttpHeaders headers) {
        var fullQueryParams = marvelApiConfig.getAuthQueryParams();
        String fullPath = comicsPath.concat("/").concat(Long.toString(id));
        if(ObjectUtils.isEmpty(headers)){
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        JsonNode jsonNode = httpClientService.doGet(fullPath,fullQueryParams,JsonNode.class,headers);
        return ComicMapper.toDtoList(jsonNode).get(0);

    }
    public Map<String, String> getQueryParamsForFindAll(MyPageable pageable, Long characterId) {
        var params = marvelApiConfig.getAuthQueryParams();
        params.put("limit",String.valueOf(pageable.limit()));
        params.put("offset",String.valueOf(pageable.offset()));
        params.put("characters",String.valueOf(characterId));
        return params;
    }
}
