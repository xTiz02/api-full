package org.example.marvelapi.persistence.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.example.marvelapi.config.marvel.MarvelApiConfig;
import org.example.marvelapi.persistence.marvel.dto.CharacterDto;
import org.example.marvelapi.persistence.marvel.dto.MyPageable;
import org.example.marvelapi.persistence.marvel.mapper.CharacterMapper;
import org.example.marvelapi.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CharacterRepository {

    @Autowired
    private MarvelApiConfig marvelApiConfig;

    @Autowired
    private HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    @Value("${integration.marvel.character-path}")
    private String characterPath;

    @PostConstruct
    private void setPath() {
        characterPath = basePath.concat("/").concat("characters");
    }

    public List<CharacterDto> findAll(MyPageable pageable,
                                      String name,
                                      int[] comics,
                                      int[] series,
                                      HttpHeaders headers) {

        Map<String, String> fullQueryParams = this.getQueryParamsForFindAll(pageable, name, comics, series);

        if (ObjectUtils.isEmpty(headers)) {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        JsonNode jsonNode = httpClientService.doGet(characterPath, fullQueryParams, JsonNode.class, headers);

        return CharacterMapper.toList(jsonNode);
    }


    public CharacterDto.CharacterInfoDto findById(Long id, HttpHeaders headers) {
        var fullQueryParams = marvelApiConfig.getAuthQueryParams();
        if (ObjectUtils.isEmpty(headers)) {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }
        String fullPath = characterPath.concat("/").concat(Long.toString(id));
        JsonNode jsonNode = httpClientService.doGet(fullPath, fullQueryParams, JsonNode.class, headers);
        return CharacterMapper.toDtoList(jsonNode).get(0);
    }


    private Map<String, String> getQueryParamsForFindAll(MyPageable pageable, String name, int[] comics, int[] series) {
        Map<String, String> params = marvelApiConfig.getAuthQueryParams();
        params.put("limit", Long.toString(pageable.limit()));
        params.put("offset", Long.toString(pageable.offset()));
        if (StringUtils.hasText(name)) {
            params.put("name", name);
        }
        if (comics != null) {
            params.put("comics", arrayToString(comics));
        }
        if (series != null) {
            params.put("series", arrayToString(series));
        }
        return params;
    }

    private String arrayToString(int[] array) {
        return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(","));
    }
}
