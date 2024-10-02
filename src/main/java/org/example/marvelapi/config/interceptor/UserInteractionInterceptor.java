package org.example.marvelapi.config.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.marvelapi.exception.ApiErrorException;
import org.example.marvelapi.persistence.entity.UserInteractionLog;
import org.example.marvelapi.persistence.repository.UserInteractionLogRepository;
import org.example.marvelapi.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class UserInteractionInterceptor implements HandlerInterceptor {


    @Autowired
    private UserInteractionLogRepository userLogRepository;

    @Autowired
    @Lazy//Lazy para evitar la dependencia circular con el interceptor
    private AuthenticationService authenticationService;

    private final Logger logger = LoggerFactory.getLogger(UserInteractionInterceptor.class);



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("Inteceptor: " + this.getClass().getName());

        String requestURI = request.getRequestURI();
        logger.info("Se llamo a la request URL: " + requestURI);


        UserInteractionLog userLog = new UserInteractionLog();
        userLog.setHttpMethod(request.getMethod());
        userLog.setUrl(request.getRequestURL().toString());

        UserDetails user = authenticationService.getUserLoggedIn();
        userLog.setUsername(user.getUsername());
        userLog.setRemoteAddress(request.getRemoteAddr());
        userLog.setTimestamp(LocalDateTime.now());

        try {
            userLogRepository.save(userLog);
            return true;
        } catch (Exception exception) {
            throw new ApiErrorException("No se logró guardar el registro de interacción correctamente");
        }
    }
}

