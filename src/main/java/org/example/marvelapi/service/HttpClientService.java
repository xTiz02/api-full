package org.example.marvelapi.service;

import org.springframework.http.HttpHeaders;
import java.util.Map;

public interface HttpClientService {

     <T> T doGet(String path, Map<String, String> queryParams, Class<T> responseType, HttpHeaders headers);

     <T,R> T doPost(String path, Map<String, String> queryParams,Class<T> responseType, R bodyRequest, HttpHeaders headers);

     <T,R> T doPut(String path, Map<String, String> queryParams,Class<T> responseType, R bodyRequest, HttpHeaders headers);

     <T> T doDelete(String path, Map<String, String> queryParams, Class<T> responseType, HttpHeaders headers);
}
