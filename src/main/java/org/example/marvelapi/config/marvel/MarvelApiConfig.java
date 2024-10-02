package org.example.marvelapi.config.marvel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelApiConfig {

    @Autowired
    @Qualifier("mb5Encoder")
    private PasswordEncoder mb5Encoder;

    @Value("${integration.marvel.public-key}")
    private String publicKey;

    @Value("${integration.marvel.private-key}")
    private String privateKey;
    private Long timestamp = new Date(System.currentTimeMillis()).getTime();

    public String getHash() {
        String hash = Long.toString(timestamp).concat(privateKey).concat(publicKey);
        return mb5Encoder.encode(hash);
    }
    public Map<String, String> getAuthQueryParams() {
        Map<String, String> params = new HashMap<>();
        params.put("apikey", publicKey);
        params.put("ts", Long.toString(timestamp));
        params.put("hash", getHash());
        return params;
    }
}
