package com.company.battle.repositories;

import com.company.common.models.MoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MoveRepository extends JpaRepository<MoveEntity, UUID> {

    List<MoveEntity> findByGameId(UUID gameId);
}
