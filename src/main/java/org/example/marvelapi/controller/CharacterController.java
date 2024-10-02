package org.example.marvelapi.controller;

import org.example.marvelapi.persistence.marvel.dto.CharacterDto;
import org.example.marvelapi.persistence.marvel.dto.MyPageable;
import org.example.marvelapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PreAuthorize("hasAuthority('character:read-all')")
    @GetMapping
    public ResponseEntity<List<CharacterDto>> getCharacters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int[] comics,
            @RequestParam(required = false) int[] series,
            @RequestParam(defaultValue = "2") long limit,
            @RequestParam(defaultValue = "0") long offset
    ) {

        MyPageable pageable = new MyPageable(limit, offset);

        return ResponseEntity.ok(characterService.findAll(pageable, name, comics, series));
    }
    @PreAuthorize("hasAuthority('character:read-detail')")
    @GetMapping("/{characterId}")
    public ResponseEntity<CharacterDto.CharacterInfoDto> getCharacterById(
            @PathVariable(name = "characterId") Long characterId
    ) {
        return ResponseEntity.ok(characterService.findById(characterId));
    }
}
