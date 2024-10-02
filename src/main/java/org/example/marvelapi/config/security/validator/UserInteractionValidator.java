package org.example.marvelapi.config.security.validator;

import org.example.marvelapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("interactionLogValidator")
public class UserInteractionValidator {
    @Autowired
    private AuthenticationService authenticationService;

    public boolean validate(String username){
        String userLoggedIn = authenticationService.getUserLoggedIn().getUsername();
        return userLoggedIn.equals(username);
    }
}
