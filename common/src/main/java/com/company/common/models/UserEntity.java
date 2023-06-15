package com.company.common.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private float rating;
    private String password;
    private String salt;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

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
