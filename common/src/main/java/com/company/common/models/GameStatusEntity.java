package com.company.common.models;

import com.company.common.models.enums.GameStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "game_status")
@Getter
@Setter
public class GameStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private GameStatus name;

    @OneToMany(mappedBy = "game_status")
    private List<GameEntity> games;

    public GameStatusEntity() {

    }

    public GameStatusEntity(GameStatus name) {
        this.name = name;
    }
}
