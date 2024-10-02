package org.example.marvelapi.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.marvelapi.util.RoleEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
@Data
@AllArgsConstructor
@Document
public class Role implements GrantedAuthority {

    @Id
    private String id;
    private RoleEnum name;
    @DBRef(lazy = true)
    private List<Permission> permissions;

    @Override
    public String getAuthority() {
        if (name != null){
            return name.name();
        }
        return null;
    }
}
