package com.auth.api.modules.role;

import com.auth.api.util.enums.UserRole;
import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private UserRole name;
}
