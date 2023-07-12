package com.company.common.models;

import com.company.common.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private float rating;
    private String password;
    private String salt;
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<MoveEntity> moves;

    @OneToMany(mappedBy = "winner")
    private List<GameEntity> games;
}
