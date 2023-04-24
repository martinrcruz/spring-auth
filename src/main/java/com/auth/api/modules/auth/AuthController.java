package com.auth.api.modules.auth;

import com.auth.api.util.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ApiResponse register(@RequestBody CreateUserRequest user) throws Exception{
        log.info(AuthController.class + ".register -> endpoint called. Initializing...");
        try {
            log.info(AuthController.class + ".register -> Calling authService.createUser with: ");
            log.info(AuthController.class + ".register -> [User Data Request] ");
            log.info(AuthController.class + ".register -> Username       : " + user.getUsername());
            log.info(AuthController.class + ".register -> Email          : " + user.getEmail());
            log.info(AuthController.class + ".register -> First Name     : " + user.getFirstName());
            log.info(AuthController.class + ".register -> Last Name      : " + user.getLastName());
            return authService.createUser(user);
        } catch (Exception e) {
            log.error(AuthController.class + ".register -> Error: " + e.getLocalizedMessage());
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/login")
    public UserAuthResponse login(@RequestBody UserCredential user) throws Exception {
        log.info(AuthController.class + ".login -> endpoint called. Initializing...");
        try {
            log.info(AuthController.class + ".login -> Calling authService.loginUser with: ");
            log.info(AuthController.class + ".login -> [User Data Request] ");
            log.info(AuthController.class + ".login -> Username       : " + user.getUsername());
            log.info(AuthController.class + ".login -> Attempting to Auth . . .");
            return authService.loginUser(user);

        } catch (Exception e) {
            log.error(AuthController.class + ".login -> Error: " + e.getLocalizedMessage());
            throw new Exception(e.getMessage());
        }
    }
}
