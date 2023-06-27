package com.company.common.models;

import com.company.common.models.enums.Role;
import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    private UUID id;
    private String username;
    private float rating;
    private String password;
    private String salt;
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "creator")
    private List<RoomEntity> createdRooms;

    @ManyToMany
    @JoinTable(
            name = "user_room",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<RoomEntity> rooms;
    @OneToMany(mappedBy = "user")
    private List<MoveEntity> moves;
}
