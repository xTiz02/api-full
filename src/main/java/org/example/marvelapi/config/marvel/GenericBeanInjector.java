package org.example.marvelapi.config.marvel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GenericBeanInjector {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
