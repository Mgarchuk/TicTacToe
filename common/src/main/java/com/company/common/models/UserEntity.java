package com.company.common.models;

import com.company.common.models.enums.AuthProvider;
import com.company.common.models.enums.Role;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@Data
public class UserEntity {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    private String username;
    private float rating;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

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
