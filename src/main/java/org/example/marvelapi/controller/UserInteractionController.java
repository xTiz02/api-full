package org.example.marvelapi.controller;

import org.example.marvelapi.persistence.entity.dto.GetUserInteractionLogDto;
import org.example.marvelapi.service.UserInteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-interactions")
public class UserInteractionController {

    @Autowired
    private UserInteractionLogService userInteractionLogService;


    @PreAuthorize("hasAuthority('user-interaction:read-all')")
    @GetMapping
    public ResponseEntity<Page<GetUserInteractionLogDto>> findAll(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "10") int limit
    ){
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findAll(pageable));
    }


    @PreAuthorize("hasAuthority('user-interaction:read-by-username') || @interactionLogValidator.validate(#username)")
    @GetMapping("/{username}")
    public ResponseEntity<Page<GetUserInteractionLogDto>> findByUsername(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit
    ){
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findByUsername(pageable, username));
    }


    private static Pageable buildPageable(int offset, int limit) {
        Pageable pageable;

        if(offset < 0 ){
            throw new IllegalArgumentException("El atributo offset no puede ser menor a cero");
        }

        if(limit <= 0){
            throw new IllegalArgumentException("El atributo limit no puede ser menor o igual a cero");
        }

        if(offset == 0) {
            pageable = PageRequest.of(0, limit);
        } else {
            pageable = PageRequest.of(offset / limit, limit);
        }

        return pageable;
    }
}
