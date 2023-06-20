package com.company.battle.repositories;

import com.company.common.models.MoveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoveRepository extends JpaRepository<MoveEntity, Integer> {
}
