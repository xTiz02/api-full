package org.example.marvelapi.service.impl;

import org.example.marvelapi.exception.ApiErrorException;
import org.example.marvelapi.service.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
@Service
public class RestTemplateService implements HttpClientService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public <T> T doGet(String path, Map<String, String> queryParams, Class<T> responseType, HttpHeaders headers) {
        String url = buildFinalUrl(path, queryParams);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);
        if(response.getStatusCode().value()!= HttpStatus.OK.value()){
            String message = String.format("Error consumiendo endpoint [ {} - {} ], código de respuesta es:",
            HttpMethod.GET, url, response.getStatusCode());
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    @Override
    public <T, R> T doPost(String path, Map<String, String> queryParams, Class<T> responseType, R bodyRequest, HttpHeaders headers) {
        String url = buildFinalUrl(path, queryParams);

        HttpEntity<R> entity = new HttpEntity<>(bodyRequest,headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.POST, entity, responseType);
        if(response.getStatusCode().value()!= HttpStatus.OK.value() || response.getStatusCode().value()!= HttpStatus.CREATED.value()){
            String message = String.format("Error consumiendo endpoint [ {} - {} ], código de respuesta es:",
                    HttpMethod.POST, url, response.getStatusCode());
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    @Override
    public <T, R> T doPut(String path, Map<String, String> queryParams, Class<T> responseType, R bodyRequest, HttpHeaders headers) {
        String url = buildFinalUrl(path, queryParams);

        HttpEntity<R> entity = new HttpEntity<>(bodyRequest,headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.PUT, entity, responseType);
        if(response.getStatusCode().value()!= HttpStatus.OK.value()){
            String message = String.format("Error consumiendo endpoint [ {} - {} ], código de respuesta es:",
                    HttpMethod.PUT, url, response.getStatusCode());
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    @Override
    public <T> T doDelete(String path, Map<String, String> queryParams, Class<T> responseType, HttpHeaders headers) {
        String url = buildFinalUrl(path, queryParams);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.DELETE, entity, responseType);
        if(response.getStatusCode().value()!= HttpStatus.OK.value()){
            String message = String.format("Error consumiendo endpoint [ {} - {} ], código de respuesta es:",
                    HttpMethod.DELETE, url, response.getStatusCode());
            throw new ApiErrorException(message);
        }
        return response.getBody();
    }

    private String buildFinalUrl(String path, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(path);
        if (queryParams != null) {
            queryParams.forEach((k,v) -> {
                builder.queryParam(k, v);
            });
        }
        return builder.build().toUriString();
    }
}
