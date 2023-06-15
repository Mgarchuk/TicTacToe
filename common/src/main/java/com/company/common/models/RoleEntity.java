package com.company.common.models;

import com.company.common.models.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "role")
@Getter
@Setter
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Role name;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users;

    public RoleEntity() {

    }

    public RoleEntity(Role name) {
        this.name = name;
    }
}
