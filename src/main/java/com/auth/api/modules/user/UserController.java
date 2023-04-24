package com.auth.api.modules.user;

import com.auth.api.modules.role.Role;
import com.auth.api.modules.role.RoleRepository;
import com.auth.api.util.ApiResponse;
import com.auth.api.util.CreateUserRequest;
import com.auth.api.util.UserCredential;
import com.auth.api.util.enums.UserRole;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Log4j2
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/private")
    public ResponseEntity<String> privateEndpoint() {
        return ResponseEntity.ok("This is a private endpoint");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<String> adminEndpoint() {
        return ResponseEntity.ok("This is an admin endpoint");
    }

    @GetMapping("/soporte")
    @PreAuthorize("hasRole('SOPORTE')")
    public ResponseEntity<String> soporteEndpoint() {
        return ResponseEntity.ok("This is a soporte endpoint");
    }

    @GetMapping("/desarrollador")
    @PreAuthorize("hasRole('DESARROLLADOR')")
    public ResponseEntity<String> desarrolladorEndpoint() {
        return ResponseEntity.ok("This is a desarrollador endpoint");
    }
}