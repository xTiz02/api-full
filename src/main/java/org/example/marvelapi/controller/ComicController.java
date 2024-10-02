package org.example.marvelapi.controller;

import org.example.marvelapi.persistence.marvel.dto.ComicDto;
import org.example.marvelapi.persistence.marvel.dto.MyPageable;
import org.example.marvelapi.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {
    @Autowired
    private ComicService comicService;

    @PreAuthorize("hasAuthority('comic:read-all')")
    @GetMapping
    public ResponseEntity<List<ComicDto>> getComics(
            @RequestParam(value = "characterId", required = false) Long characterId,
            @RequestParam(value = "limit", required = false, defaultValue = "10") long limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0") long offset)
    {
        MyPageable pageable = new MyPageable(limit, offset);
        return ResponseEntity.ok(comicService.findAll(pageable, characterId));

    }

    @PreAuthorize("hasAuthority('comic:read-by-id')")
    @GetMapping("/{comicId}")
    public  ResponseEntity<ComicDto> getComicById(
            @PathVariable(name = "comicId") Long comicId
    ) {
        return ResponseEntity.ok(comicService.findById(comicId));
    }
}
