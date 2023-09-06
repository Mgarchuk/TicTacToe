package com.company.battle.repositories;

import com.company.common.models.GameEntity;
import com.company.common.models.enums.GameVisibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, UUID>, JpaSpecificationExecutor<GameEntity> {

    List<GameEntity> findByVisibility(GameVisibility visibility);
}
