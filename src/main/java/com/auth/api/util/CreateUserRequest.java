package com.auth.api.util;

import com.auth.api.modules.role.Role;
import lombok.Data;

import java.util.Set;

@Data
public class CreateUserRequest {
    String username;
    String password;
    String email;
}
