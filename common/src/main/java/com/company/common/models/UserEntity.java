package com.company.common.models;

import com.company.common.models.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private LocalDateTime creationDate;

    @Column(unique = true)
    private String username;
    private int rating;

    @Column(unique = true)
    private String email;
    private boolean isAlive;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<MoveEntity> moves;

    @ToString.Exclude
    @OneToMany(mappedBy = "winner")
    private List<GameEntity> games;
}
