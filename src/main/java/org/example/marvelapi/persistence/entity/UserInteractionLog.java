package org.example.marvelapi.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserInteractionLog {
    @MongoId
    private String id;

    private String url;

    private String httpMethod;

    private String username;

    private LocalDateTime timestamp;

    private String remoteAddress;

}
