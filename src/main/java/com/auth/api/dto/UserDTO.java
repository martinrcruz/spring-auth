package com.auth.api.dto;

import com.auth.api.modules.role.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    Long id;
    String username;
    String email;
    Role role;
}
