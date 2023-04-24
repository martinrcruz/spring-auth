package com.auth.api.modules.auth;

import com.auth.api.modules.role.Role;
import com.auth.api.modules.role.RoleRepository;
import com.auth.api.modules.user.User;
import com.auth.api.modules.user.UserRepository;
import com.auth.api.modules.user.UserService;
import com.auth.api.util.*;
import com.auth.api.util.enums.UserRole;
import com.auth.api.util.jwt.JwtUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Log4j2
@Service
public class AuthService {

    //const and env variables
    @Value("${authApi.defaultRole}")
    private UserRole defaultRole;

    @Autowired
    UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public UserAuthResponse loginUser(UserCredential credentials) throws Exception {
        try {
            log.info(AuthService.class + ".loginUser -> initializing authentication process on loginUser");
            log.info(AuthService.class + ".loginUser -> Credentials - Username: " + credentials.getUsername());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

            log.info(AuthService.class + ".loginUser -> Generating Token ...");
            String token = jwtUtil.generateToken(authentication.getName());
            log.info(AuthService.class + ".loginUser -> Token generated successfully!");
            log.info(AuthService.class + ".loginUser -> Token: " + token);

            log.info(AuthService.class + ".loginUser -> Calling User " + credentials.getUsername() +" to set response...");
            User user = userRepository.getUserByUsername(credentials.getUsername());
            log.info(AuthService.class + ".loginUser -> [LOGIN SUCCESSFULLY] ");
            log.info(AuthService.class + ".loginUser -> [User Data Response] ");
            log.info(AuthService.class + ".loginUser -> Id           :  " + user.getId());
            log.info(AuthService.class + ".loginUser -> Username     :  " + user.getUsername());
            log.info(AuthService.class + ".loginUser -> Email        :  " + user.getEmail());
            log.info(AuthService.class + ".loginUser -> Role         :  " + user.getRole());
            log.info(AuthService.class + ".loginUser -> Token        :  " + token);
            log.info(AuthService.class + ".loginUser -> Authorities  :  " + user.getAuthorities());
            UserAuthResponse response = new UserAuthResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setEmail(user.getEmail());
            response.setRole(user.getRole());
            response.setToken(token);
            response.setGrants(user.getAuthorities());
            return response;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ApiResponse createUser(CreateUserRequest user) throws Exception {
        try {
            log.info(AuthService.class + ".createUser -> initializing registration process on createUser");
            log.info(AuthService.class + ".createUser -> Default value on Role when creating user is: " + defaultRole);
            Role role = roleRepository.getRoleByName(defaultRole);
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setRole(role);
            User createdUser = userRepository.save(newUser);
            log.info(AuthService.class + ".createUser -> User created with success.");
            log.info(AuthService.class + ".createUser -> [User Data Response]");
            log.info(AuthService.class + ".createUser -> Id           :  " + createdUser.getId());
            log.info(AuthService.class + ".createUser -> Username     :  " + createdUser.getUsername());
            log.info(AuthService.class + ".createUser -> Email        :  " + createdUser.getEmail());
            log.info(AuthService.class + ".createUser -> Role         :  " + createdUser.getRole());
            log.info(AuthService.class + ".createUser -> Authorities  :  " + createdUser.getAuthorities());
            return new ApiResponse(newUser, ResponseCode.SUCCESSFUL.getCodError(), ResponseCode.SUCCESSFUL.getDescError());

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
