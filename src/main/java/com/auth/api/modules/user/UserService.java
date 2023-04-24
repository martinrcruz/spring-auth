package com.auth.api.modules.user;

import com.auth.api.dto.UserDTO;
import com.auth.api.modules.role.Role;
import com.auth.api.modules.role.RoleRepository;
import com.auth.api.util.CreateUserRequest;
import com.auth.api.util.ResponseCode;
import com.auth.api.util.ApiResponse;
import com.auth.api.exceptions.CustomAuthException;
import com.auth.api.util.enums.UserRole;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Log4j2
@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        try {
            user = userRepository.getUserByUsername(username);
            if (user != null) {
                return user;
            }
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public ApiResponse loadUserById(Long id) throws CustomAuthException {
        UserDTO userDto = new UserDTO();
        try {
            User user = userRepository.getUserById(id);
            log.debug("getting user by Id");
            if (user != null) {
                userDto.setId(user.getId());
                userDto.setUsername(user.getUsername());
                userDto.setEmail(user.getEmail());
                userDto.setRole(user.getRole());
            }
        } catch (UsernameNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
        return new ApiResponse(userDto, ResponseCode.SUCCESSFUL.getCodError(), ResponseCode.SUCCESSFUL.getDescError());
    }

    public ApiResponse addUser(CreateUserRequest user) throws Exception {
        User auxUser;
        try {
            log.debug("addUser function");
            Role role = roleRepository.getRoleByName(UserRole.ROLE_USER);
            User newUser = new User();
            newUser.setEmail(user.getEmail());
            newUser.setUsername(user.getUsername());
            newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            newUser.setFirstName("test");
            newUser.setLastName("test");
            newUser.setRole(role);
            userRepository.save(newUser);
            log.debug("addUser with success!");

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return new ApiResponse(user, ResponseCode.SUCCESSFUL.getCodError(), ResponseCode.SUCCESSFUL.getDescError());
    }

    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.getUserByUsername(username));
    }
}