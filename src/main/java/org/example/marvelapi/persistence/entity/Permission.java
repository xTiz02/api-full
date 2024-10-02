package org.example.marvelapi.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
@Data
@AllArgsConstructor
@Document
public class Permission {

    @MongoId
    private String id;

    private String name;
}
