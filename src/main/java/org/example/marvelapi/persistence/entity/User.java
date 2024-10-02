package org.example.marvelapi.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@Document
public class User implements UserDetails {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    //@AccessType(AccessType.Type.PROPERTY) //indica que el campo no se mapea a la base de datos
    //@Field(name = "username", targetType = FieldType.STRING, order = 1)//indica que el tipo de dato string y el orden 1 que significa que es el primer campo

    private String username;
    private String password;
    //@Unwrapped(onEmpty = Unwrapped.OnEmpty.USE_NULL, prefix = "a_")//indica que si el campo esta vacio se debe usar null en vez de un objeto vacio
    //el role se guarda en campos y no como un objeto
    //el prefijo indica que se debe agregar a_ al inicio del nombre del campo
    //@Field("role_name)
    //@DocumentReference(lookup = "{ 'name' : ?#{#target} }") //aparecerá "role_name": "ROLE_USER"
    //@DocumentReference(lazy = true) //indica que se debe cargar el objeto de manera diferida (lazy)
    //@DocumentReference //muestra solo el id del objeto referenciado "role": "5f5e3b7b7b3b3b3b3b3b3b3b"
    @DBRef(lazy = true)
    private Role role;
    private boolean account_expired;
    private boolean account_locked;
    private boolean credentials_expired;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(role == null){
            return new ArrayList<>();
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getAuthority()));

        role.getPermissions().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getName())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !account_expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !account_locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentials_expired;
    }
}
