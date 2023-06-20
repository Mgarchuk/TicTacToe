package com.company.common.models;

import com.company.common.models.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@Data
public class UserEntity {

    @Id
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
