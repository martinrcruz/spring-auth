package com.auth.api.util;

import com.auth.api.modules.role.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserRegisterResponse {
    Long id;
    String username;
    String email;
    Role role;
    String createdAt;

}
