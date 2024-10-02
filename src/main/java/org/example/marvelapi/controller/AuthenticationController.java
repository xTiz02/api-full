package org.example.marvelapi.controller;

import jakarta.validation.Valid;
import org.example.marvelapi.persistence.entity.dto.LoginRequest;
import org.example.marvelapi.persistence.entity.dto.LoginResponse;
import org.example.marvelapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody @Valid LoginRequest loginRequest
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
    }

    //@PreAuthorize("permitAll")
    @PostMapping("/logout")
    public void logout() {
        authenticationService.logout();
    }
}
