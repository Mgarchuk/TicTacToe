package com.company.common.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "room")
@Data
public class RoomEntity {

    @Id
    private UUID id;

    @Column(name = "room_visibility")
    private boolean roomVisibility;

    @OneToOne(mappedBy = "room", fetch = FetchType.LAZY)
    private GameEntity game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private UserEntity creator;

    @ManyToMany(mappedBy = "rooms")
    private List<UserEntity> users;

    private String link;
}
