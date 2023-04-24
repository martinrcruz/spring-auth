package com.auth.api.util;

import com.auth.api.modules.role.Role;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
public class UserAuthResponse {
    Long id;
    String username;
    String token;
    String email;
    Role role;
    Collection grants;
}
