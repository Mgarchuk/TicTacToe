package com.company.battle.repositories;

import com.company.common.models.GameEntity;
import com.company.common.models.enums.GameVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID> {

    List<GameEntity> findByVisibility(GameVisibility visibility);
}
